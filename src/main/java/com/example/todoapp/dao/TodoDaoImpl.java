package com.example.todoapp.dao;

import com.example.todoapp.model.Todo;
import com.example.todoapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao{

    private static final String INSERT_TODOS_SQL = "INSERT INTO todos " +
            "(title, username, description, target_date, is_done) VALUES " + " (?,?,?,?,?);";
    private static final String SELECT_TODO_BY_ID = "SELECT id,title,username,description,target_date,is_done FROM todos WHERE id = ?";
    private static final String SELECT_ALL_TODOS_OF_USER = "SELECT * FROM todos WHERE username = ?";
    private static final String DELETE_TODO_BY_ID = "DELETE FROM todos WHERE id = ?;";
    private static final String UPDATE_TODO = "UPDATE todos SET title = ?, username= ?, description =?, target_date =?, is_done = ? WHERE id = ?;";

    public TodoDaoImpl(){

    }

    @Override
    public void insertTodo(Todo todo) throws SQLException {
        System.out.println(INSERT_TODOS_SQL);
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODOS_SQL);
        ){
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getUsername());
            preparedStatement.setString(3, todo.getDescription());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            preparedStatement.setBoolean(5, todo.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
    }

    @Override
    public Todo selectTodo(long todoId) {
        Todo todo = null;
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID);
        ){
            preparedStatement.setLong(1, todoId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todo = new Todo(id, title, username, description, targetDate, isDone);
            }
        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return todo;
    }

    @Override
    public List<Todo> selectAllTodos(String user) {
        List<Todo> todos = new ArrayList<>();
        try(Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS_OF_USER);
        ){
            preparedStatement.setString(1, user);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String username = rs.getString("username");
                String description = rs.getString("description");
                LocalDate targetDate = rs.getDate("target_date").toLocalDate();
                boolean isDone = rs.getBoolean("is_done");
                todos.add(new Todo(id, title, username, description, targetDate, isDone));
            }
        } catch (SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return todos;
    }

    @Override
    public boolean deleteTodo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO_BY_ID);
        ) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO);
        ) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getUsername());
            preparedStatement.setString(3, todo.getDescription());
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(todo.getTargetDate()));
            preparedStatement.setBoolean(5, todo.getStatus());
            preparedStatement.setLong(6, todo.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
