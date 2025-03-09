import java.sql.*;

public class StudentController {

    private static final String url = "jdbc:mysql://localhost:3306/studentDB";
    private static final String username = "root";  // Use your MySQL username
    private static final String password = "password";  // Use your MySQL password

    // CRUD Operations

    // Method to create student record
    public static boolean createStudent(Student student) {
        String query = "INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, student.getStudentID());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getDepartment());
            stmt.setDouble(4, student.getMarks());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to read student records
    public static void readStudents() {
        String query = "SELECT * FROM Student";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("StudentID: " + rs.getInt("StudentID") +
                        ", Name: " + rs.getString("Name") +
                        ", Department: " + rs.getString("Department") +
                        ", Marks: " + rs.getDouble("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update student record
    public static boolean updateStudent(Student student) {
        String query = "UPDATE Student SET Name = ?, Department = ?, Marks = ? WHERE StudentID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setDouble(3, student.getMarks());
            stmt.setInt(4, student.getStudentID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete student record
    public static boolean deleteStudent(int studentID) {
        String query = "DELETE FROM Student WHERE StudentID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentID);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
