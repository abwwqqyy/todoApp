package com.example.todoapp.controller;

import com.example.todoapp.HelloServlet;
import com.example.todoapp.dao.LoginDao;
import com.example.todoapp.model.Login;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private LoginDao loginDao;

    public void init(){
        loginDao = new LoginDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        try{
            if(loginDao.validate(login)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
                dispatcher.forward(request, response);
            } else{
                HttpSession session = request.getSession();
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}