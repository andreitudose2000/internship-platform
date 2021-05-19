package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DbConnection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/internship_platform";
    private static final String DATABASE_USER_NAME = "andreitudose";
    private static final String DATABASE_PASSWORD = "andreitudose";
    private Connection connection = null;

    private static final DbConnection INSTANCE = new DbConnection();

    private DbConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = getConnection();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER_NAME,DATABASE_PASSWORD);
    }

    public static DbConnection getInstance() {
        return INSTANCE;
    }

    public Connection getDBConnection() {
        return this.connection;
    }
}