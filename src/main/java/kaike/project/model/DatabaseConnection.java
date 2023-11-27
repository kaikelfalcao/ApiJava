package kaike.project.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static Connection conn;

    private DatabaseConnection() {
        String url = System.getenv("DATABASE_URL");
        String username = System.getenv("DATABASE_USER");
        String password = System.getenv("DATABASE_PASSWORD");
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
