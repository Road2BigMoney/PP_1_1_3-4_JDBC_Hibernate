package jm.task.core.jdbc;

import com.mysql.cj.MysqlConnection;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Робот","Деревянный", (byte) 17);
        userDaoJDBC.saveUser("Робот","Оловянный", (byte) 18);
        userDaoJDBC.saveUser("Робот","Стеклянный", (byte) 29);
        userDaoJDBC.saveUser("Робот","Классический", (byte) 100);
        List<User> users = userDaoJDBC.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();

    }


}
