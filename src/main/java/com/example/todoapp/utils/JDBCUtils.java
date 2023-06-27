package com.example.todoapp.utils;

import java.sql.*;
import java.time.LocalDate;

public class JDBCUtils {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/todoapp";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "admin";

    public static Connection getConnection(){
        Connection connection = null;
        try{
//            Class.forName("com.mysql.jdbc.driver"); // not working
//            Class.forName("com.mysql.cj.jdbc.driver"); // not working
            DriverManager.registerDriver (new com.mysql.cj.jdbc.Driver()); // need this for tomcat server :(
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//            System.out.println("Conn: " + connection);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (SQLException e){
//            System.out.println("Conn failed" );
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex){
        for (Throwable e : ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQL State: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while(t != null){ // get root cause
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static Date getSQLDate(LocalDate date){
        return Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate){
        return sqlDate.toLocalDate();
    }

    public static void main(String[] args) {
        try {
            Statement stmt = getConnection().createStatement();
            String query = "select * from users";
            //checks whether query returns the result or not
            boolean rs = stmt.execute(query);
            System.out.println("Return value :: " +rs);
            ResultSet rs1 = stmt.executeQuery(query);
            while(rs1.next()) {
                System.out.println("Id :: " +rs1.getInt(1));
                System.out.println("First Name :: " +rs1.getString("first_name"));
                System.out.println("Last Name :: " +rs1.getString("last_name"));
                System.out.println("First Name :: " +rs1.getString(4));
                System.out.println("First Name :: " +rs1.getString(5));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
