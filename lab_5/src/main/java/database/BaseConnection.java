package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseConnection {
    final String url = "jdbc:mysql://localhost:3306/lab5db";
    final String user = "root";
    final String pass = "78u78u9U!";

    private Connection connection;
    public Connection open() throws SQLException {
        return (connection = DriverManager.getConnection(url,user,pass));
    }
    public void close() throws SQLException {
        connection.close();
    }

}
