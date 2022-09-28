package jm.task.core.jdbc;

import com.mysql.cj.MysqlConnection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Katata2022ta";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/katascheme";
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        Connection connection;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("СОЕДИНЕНО");
            }
            connection.close();
            if (connection.isClosed()) {
                System.out.println("ОТСОЕДИНЕНО");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
