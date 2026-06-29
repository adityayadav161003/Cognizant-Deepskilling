import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Q31_BasicJDBCConnection {
    public static void main(String[] args) {
        String dbUrl = "jdbc:sqlite:students.db";
        
        System.out.println("=== Basic JDBC Connection Demo (SQLite) ===");
        
        try {
            // 1. Load the SQLite JDBC Driver (optional but recommended in older specifications)
            Class.forName("org.sqlite.JDBC");
            
            // 2. Establish connection to the SQLite database
            // (If the file students.db does not exist, SQLite creates it automatically)
            try (Connection conn = DriverManager.getConnection(dbUrl);
                 Statement stmt = conn.createStatement()) {
                
                System.out.println("Successfully connected to the database.");
                
                // 3. Create the students table (drop first if exists to reset auto-increment)
                stmt.execute("DROP TABLE IF EXISTS students;");
                String createTableSql = "CREATE TABLE IF NOT EXISTS students ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "name TEXT NOT NULL,"
                        + "grade TEXT"
                        + ");";
                stmt.execute(createTableSql);
                System.out.println("Created table 'students'.");
                
                // Insert some sample records
                stmt.execute("INSERT INTO students (name, grade) VALUES ('Alice', 'A');");
                stmt.execute("INSERT INTO students (name, grade) VALUES ('Bob', 'B');");
                stmt.execute("INSERT INTO students (name, grade) VALUES ('Charlie', 'C');");
                System.out.println("Inserted sample student records.");
                
                // 4. Execute a SELECT query
                String selectSql = "SELECT * FROM students;";
                System.out.println("\nExecuting SELECT query and retrieving results:");
                try (ResultSet rs = stmt.executeQuery(selectSql)) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String grade = rs.getString("grade");
                        System.out.println("Student ID: " + id + ", Name: " + name + ", Grade: " + grade);
                    }
                }
                
            }
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found. Please make sure the driver JAR is added to the classpath.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Database operation failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
