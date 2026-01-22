package util;

import model.DatabaseConfig;
import java.sql.*;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            // Always return a fresh connection or check if current is valid
            if (connection == null || connection.isClosed()) {
                // Load driver
                Class.forName(DatabaseConfig.DRIVER);

                // Create new connection
                connection = DriverManager.getConnection(
                        DatabaseConfig.URL + "?autoReconnect=true&useSSL=false",
                        DatabaseConfig.USERNAME,
                        DatabaseConfig.PASSWORD
                );

                System.out.println("✓ Database connection established");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    // Test if connection is valid
    public static boolean isConnectionValid() {
        if (connection == null) return false;

        try {
            return !connection.isClosed() && connection.isValid(2);
        } catch (SQLException e) {
            return false;
        }
    }
}