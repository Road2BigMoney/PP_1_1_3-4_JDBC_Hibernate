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
    private static volatile Connection connection;
    public static Connection getConnection() {

        Connection localConnection = connection;

        try {
            if (localConnection == null) {
                synchronized (Connection.class) {
                    localConnection = connection;
                    if (localConnection == null) {
                        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                        connection.setAutoCommit(false);
                        localConnection = connection;

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ОШИБКА СОЕДИНЕНИЯ!");
        }

        return localConnection;
    }
}
