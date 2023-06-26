package com.example.todoapp.dao;

import com.example.todoapp.model.Login;
import com.example.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
    public boolean validate(Login login) throws ClassNotFoundException{
        boolean status = false;
        String stmt = "SELECT * FROM  users where username = ? and password = ?";
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(stmt)
        ){
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        }catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
}
