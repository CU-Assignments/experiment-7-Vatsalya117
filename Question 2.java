import java.sql.*;
import java.util.Scanner;

public class ProductManager {

    // Database connection details
    private static final String url = "jdbc:mysql://localhost:3306/store";
    private static final String username = "root";  // Use your MySQL username
    private static final String password = "password";  // Use your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu-driven approach
        while (true) {
            System.out.println("\nProduct Management System");
            System.out.println("1. Create Product");
            System.out.println("2. Read Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createProduct(scanner);
                    break;
                case 2:
                    readProducts();
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to create a new product
    private static void createProduct(Scanner scanner) {
        System.out.print("Enter ProductID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter ProductName: ");
        String productName = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        String insertQuery = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setInt(1, productId);
            stmt.setString(2, productName);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " product(s) inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to read and display products
    private static void readProducts() {
        String selectQuery = "SELECT * FROM Product";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {

            while (rs.next()) {
                System.out.println("ProductID: " + rs.getInt("ProductID") +
                        ", ProductName: " + rs.getString("ProductName") +
                        ", Price: " + rs.getDouble("Price") +
                        ", Quantity: " + rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a product
    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter ProductID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter new ProductName: ");
        String productName = scanner.nextLine();
        System.out.print("Enter new Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new Quantity: ");
        int quantity = scanner.nextInt();

        String updateQuery = "UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? WHERE ProductID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, productName);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, productId);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " product(s) updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a product
    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter ProductID to delete: ");
        int productId = scanner.nextInt();

        String deleteQuery = "DELETE FROM Product WHERE ProductID = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, productId);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " product(s) deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
