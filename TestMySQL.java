import java.sql.*;

public class TestMySQL {
    public static void main(String[] args) {
        System.out.println("=== Testing MySQL Connection ===\n");

        // Try different passwords if you're not sure
        String[] passwords = {"", "root", "password", "123456", "mysql"};

        for (String pwd : passwords) {
            System.out.println("Trying password: '" + (pwd.isEmpty() ? "[empty]" : pwd) + "'");

            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/student_db",
                        "root",
                        pwd
                );

                System.out.println("✓ SUCCESS! Connected with password: '" + pwd + "'");

                // Test query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM students");
                if (rs.next()) {
                    System.out.println("Total students in database: " + rs.getInt("count"));
                }

                conn.close();
                System.exit(0);

            } catch (SQLException e) {
                System.out.println("✗ Failed: " + e.getMessage());
            }
        }

        System.out.println("\n✗ All password attempts failed!");
        System.out.println("\nYour MySQL password is the one you just used to login.");
        System.out.println("Update DatabaseConfig.java with the correct password.");
    }
}