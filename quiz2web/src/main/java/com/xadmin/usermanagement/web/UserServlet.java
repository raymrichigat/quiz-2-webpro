package com.xadmin.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import com.xadmin.usermanagement.dao.UserDao;
import com.xadmin.usermanagement.bean.User;


public class UserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private UserDao userDAO;

    public void init() {
        userDAO = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                case "/deleteAll":
                    deleteAllUsers(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < User > listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	String nrp = request.getParameter("nrp");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String alamat = request.getParameter("alamat");
        String jurusan = request.getParameter("jurusan");
        String gender = request.getParameter("gender");
       
        User newUser = new User(nrp, name, email, alamat, jurusan, gender);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nrp = request.getParameter("nrp");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String alamat = request.getParameter("alamat");
        String jurusan = request.getParameter("jurusan");
        String gender = request.getParameter("gender");

        User update = new User(id, nrp, name, email, alamat, jurusan, gender);
        System.out.println("Updating User: " + update);
        userDAO.updateUser(update);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
    
    private void deleteAllUsers(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException {
    		    userDAO.deleteAllUsers(); // Call the DAO method to delete all users
    		    response.sendRedirect("list"); // Redirect to the list view after deletion
    		}
}
