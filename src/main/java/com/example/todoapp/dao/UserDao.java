package com.example.todoapp.dao;

import com.example.todoapp.model.User;
import com.example.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    public int registerUser(User user){
        String INERT_USERS_SQL = "INSERT INTO users" +
                "(first_name, last_name, username, password) VALUES" +
                "(?,?,?,?);";
        int result = 0;
        try (Connection connection = JDBCUtils.getConnection();
             // create a statement
             PreparedStatement preparedStatement = connection.prepareStatement(INERT_USERS_SQL)
        ){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());

            System.out.println(preparedStatement);
            // execute
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}
