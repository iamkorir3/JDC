package util;

import model.DatabaseConfig;
import java.sql.*;


// connecting to database
public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
         
            if (connection == null || connection.isClosed()) {
               
                Class.forName(DatabaseConfig.DRIVER);

           
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
                System.err.println("Error while closing connection: " + e.getMessage());
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
