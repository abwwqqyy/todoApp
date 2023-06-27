package com.example.todoapp.controller;

import com.example.todoapp.dao.TodoDao;
import com.example.todoapp.dao.TodoDaoImpl;
import com.example.todoapp.model.Todo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/")
public class TodoController extends HttpServlet {

    private TodoDao todoDao;

    public void init(){
        todoDao = new TodoDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTodo(request, response);
                    break;
                case "/delete":
                    deleteTodo(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateTodo(request, response);
                    break;
                case "/list":
                    listTodo(request, response);
                    break;
                case "/logout":
                    logout(request,response);
                    break;
                default:
//                    RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath());
//                    dispatcher.forward(request, response);
                    response.sendRedirect(request.getContextPath() + "/login/login.jsp");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login/login.jsp");
    }

    private void listTodo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        List<Todo> listTodo = todoDao.selectAllTodos(username);
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession(false); // false will give null
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String username = (String) session.getAttribute("username");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);

        todoDao.updateTodo(updateTodo);

        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo existingTodo = todoDao.selectTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoDao.deleteTodo(id);
        response.sendRedirect("list");
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession(false); // false will give null
        String title = request.getParameter("title");
        String username = (String) session.getAttribute("username");
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
        todoDao.insertTodo(newTodo);
        response.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }

}