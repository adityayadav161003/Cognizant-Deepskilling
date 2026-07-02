import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Q33_TransactionHandlingJDBC {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public static void main(String[] args) {
        System.out.println("=== JDBC Transaction Handling Demo (Money Transfer) ===");

        try {
            Class.forName("org.sqlite.JDBC");
            initDatabase();
            
            System.out.println("\nInitial Balances:");
            printBalances();

            // Case 1: Successful transfer
            System.out.println("\nAttempting to transfer 200 from Account 1 (Alice) to Account 2 (Bob)...");
            performTransfer(1, 2, 200.0);
            printBalances();

            // Case 2: Failed transfer (Insufficient balance)
            System.out.println("\nAttempting to transfer 1000 (Insufficient balance) from Account 2 (Bob) to Account 1 (Alice)...");
            performTransfer(2, 1, 1000.0);
            printBalances();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initDatabase() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INTEGER PRIMARY KEY,"
                    + "owner TEXT NOT NULL,"
                    + "balance REAL NOT NULL"
                    + ");");
            
            stmt.execute("DELETE FROM accounts;");
            stmt.execute("INSERT INTO accounts (id, owner, balance) VALUES (1, 'Alice', 1000.0);");
            stmt.execute("INSERT INTO accounts (id, owner, balance) VALUES (2, 'Bob', 500.0);");
            System.out.println("Accounts table initialized with Alice (1000) and Bob (500).");
        }
    }

    private static void performTransfer(int fromAccountId, int toAccountId, double amount) {
        String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?;";
        String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?;";
        String checkBalanceSql = "SELECT balance FROM accounts WHERE id = ?;";

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement debitStmt = null;
        PreparedStatement creditStmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL);
            
            // Disable auto-commit to begin the transaction manually
            conn.setAutoCommit(false);

            // 1. Verify sufficient funds
            checkStmt = conn.prepareStatement(checkBalanceSql);
            checkStmt.setInt(1, fromAccountId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    double currentBalance = rs.getDouble("balance");
                    if (currentBalance < amount) {
                        throw new Exception("Insufficient balance. Transfer aborted.");
                    }
                } else {
                    throw new Exception("Source account not found.");
                }
            }

            // 2. Perform Debit operation
            debitStmt = conn.prepareStatement(debitSql);
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            debitStmt.executeUpdate();
            System.out.println("Debit completed successfully.");

            // 3. Perform Credit operation
            creditStmt = conn.prepareStatement(creditSql);
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);
            creditStmt.executeUpdate();
            System.out.println("Credit completed successfully.");

            // Commit transaction if both succeeded
            conn.commit();
            System.out.println("Transaction COMMITTED successfully.");

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            if (conn != null) {
                try {
                    // Rollback transaction on failure
                    conn.rollback();
                    System.out.println("Transaction ROLLED BACK successfully.");
                } catch (Exception ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (checkStmt != null) checkStmt.close();
                if (debitStmt != null) debitStmt.close();
                if (creditStmt != null) creditStmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printBalances() throws Exception {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts;")) {
            while (rs.next()) {
                System.out.println("  Account " + rs.getInt("id") + " (" + rs.getString("owner") + "): Balance = " + rs.getDouble("balance"));
            }
        }
    }
}
