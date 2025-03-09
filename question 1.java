import java.sql.*;

public class EmployeeFetcher {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/company";
        String username = "root";  // Use your MySQL username
        String password = "password";  // Use your MySQL password

        // SQL query to fetch data
        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try {
            // Load and register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Execute the query and store the result in a ResultSet
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                // Display the records
                System.out.println("EmpID: " + empID + ", Name: " + name + ", Salary: " + salary);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
