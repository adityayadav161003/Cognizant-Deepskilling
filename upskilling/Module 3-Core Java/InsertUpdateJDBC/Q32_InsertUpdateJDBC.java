import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Student DAO (Data Access Object) Class
class StudentDAO {
    private final String dbUrl;

    public StudentDAO(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(dbUrl);
    }

    // Initialize database structure
    public void initDatabase() throws Exception {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS students;");
            stmt.execute("CREATE TABLE IF NOT EXISTS students ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "grade TEXT"
                    + ");");
        }
    }

    // Method to insert a new student using PreparedStatement
    public int insertStudent(String name, String grade) {
        String insertSql = "INSERT INTO students (name, grade) VALUES (?, ?);";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, grade);
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return generated ID
                }
            }
        } catch (Exception e) {
            System.err.println("Error inserting student: " + e.getMessage());
        }
        return -1;
    }

    // Method to update student details using PreparedStatement
    public boolean updateStudent(int id, String newName, String newGrade) {
        String updateSql = "UPDATE students SET name = ?, grade = ? WHERE id = ?;";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            
            pstmt.setString(1, newName);
            pstmt.setString(2, newGrade);
            pstmt.setInt(3, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
        return false;
    }

    // Method to print all students
    public void printAllStudents() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students;")) {
            
            System.out.println("Current Students Database Entries:");
            while (rs.next()) {
                System.out.println("  ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Grade: " + rs.getString("grade"));
            }
        } catch (Exception e) {
            System.err.println("Error reading students: " + e.getMessage());
        }
    }
}

public class Q32_InsertUpdateJDBC {
    public static void main(String[] args) {
        String dbUrl = "jdbc:sqlite:students.db";
        System.out.println("=== JDBC Insert and Update Operations Demo ===");

        try {
            Class.forName("org.sqlite.JDBC");
            StudentDAO dao = new StudentDAO(dbUrl);
            dao.initDatabase();

            // 1. Insert new student records
            System.out.println("Inserting new students...");
            int id1 = dao.insertStudent("David", "B");
            int id2 = dao.insertStudent("Emma", "A");
            dao.printAllStudents();

            // 2. Update student details
            System.out.println("\nUpdating student with ID " + id1 + " to 'David Smith' and grade 'A'...");
            boolean success = dao.updateStudent(id1, "David Smith", "A");
            if (success) {
                System.out.println("Update successful!");
            } else {
                System.out.println("Update failed.");
            }

            dao.printAllStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
