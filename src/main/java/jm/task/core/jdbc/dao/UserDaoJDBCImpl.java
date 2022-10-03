package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = null;


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS `Users` ( `id` bigint NOT NULL AUTO_INCREMENT, `name` varchar(45) NOT NULL, `lastName` varchar(45) NOT NULL,  `age` tinyint NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Exception!");
            }
        }


    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE IF EXISTS Users;";

        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Exception!");
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {


        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users (name, lastName, age) VALUES( ?, ?, ?);";

        try {
            connection = Util.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Exception!");
            }
        }
    }

    public void removeUserById(long id) {
        Statement statement = null;
        String sql = "DELETE FROM Users WHERE ID =" + id;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Exception!");
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM Users";
        Statement statement = null;
        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            connection.commit();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Exception!");
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        String sql = "TRUNCATE Users";
        try {

            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback Exception!");
            }
        }
    }
}
