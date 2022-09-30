package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Katata2022ta";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/katascheme";
    public static Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("СОЕДИНЕНИЕ С БД УСТАНОВЛЕНО.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ОШИБКА СОЕДИНЕНИЯ!");
        }
        return connection;
    }
}
