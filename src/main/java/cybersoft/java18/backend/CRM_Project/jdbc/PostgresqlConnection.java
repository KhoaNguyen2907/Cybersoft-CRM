package cybersoft.java18.backend.CRM_Project.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {
    private static final String URL = "jdbc:postgresql://ec2-18-215-41-121.compute-1.amazonaws.com:5432/d350iivb17v9sv";
    private static final String USERNAME = "qdgeudcxwvnwth";
    private static final String PASSWORD = "cee78e60af0f3ed78efb67c14f8e9e66547636158a687e6defceb2d4ece0de6b";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
