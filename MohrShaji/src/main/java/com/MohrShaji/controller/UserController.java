package com.MohrShaji.controller;

import com.MohrShaji.Application.ManageUser;
import com.MohrShaji.model.User;
import com.MohrShaji.server.Request;
import com.MohrShaji.server.Response;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserController {

//    ReimbursementService rs = new ReimbursementService();
    ManageUser mu;
//    Request request;
//    Response response;

    public UserController() {

    }

    public UserController(ManageUser mu) {
        this.mu = mu;
    }

    public void getAllUsers(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<User> users = mu.listUsers();

        response.getWriter().println("List of all users:\n\n");

        for (User u : users) {
            response.getWriter().println(new Gson().toJson(u));
        }
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String roleId = request.getParameter("role_id");
        int roleIdInt = 0;

        if (id == null) {
            id = "0";
        }

        int idInt = Integer.parseInt(id);

        if (roleId != null) {
            try {
                roleIdInt = Integer.parseInt(roleId);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid 'role_id', please enter a valid integer role_id");
            }
        }

        response.getWriter().println("Created new user:\n\n" +
            new Gson().toJson(
            mu.createUser(idInt, username, password, firstName, lastName, email, roleIdInt)));
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String userId = request.getParameter("id");
        String username = request.getParameter("username");
        int userIdInt;

        try {
            userIdInt = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid 'id' input, please enter an integer.\n");
            return;
        }

        if (username == null) {
            response.getWriter().write("Please enter a 'username'.");
            return;
        }

//        try {
            //TODO: this
//            mu.getByUserId(userIdInt);
//        }
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String userId = request.getParameter("id");
        int userIdInt;

        try {
            userIdInt = Integer.parseInt(userId);
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid 'id', please enter a valid integer id");
            return;
        }

        try {
            response.getWriter().println("Deleted user:\n\n" +
                new Gson().toJson(
                mu.deleteUser(userIdInt)));
        } catch(IllegalArgumentException e) {
            response.getWriter().println("Invalid 'id', please enter a valid integer id");
        }
    }

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String userId = request.getParameter("id");
        String username = request.getParameter("username");

        if (userId != null) {
            try {
                int userIdInt = Integer.parseInt(userId);

                response.getWriter().println("Found user:\n\n" +
                    new Gson().toJson(
                    mu.getByUserId(userIdInt)));
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid 'id' input, please enter an integer.\n");
            }
        } else {
            if (username == null) {
                response.getWriter().write("Please specify either an 'id' or a 'username'.");
            } else {
                System.out.println(username);
                response.getWriter().println("Found user:\n\n" +
                    new Gson().toJson(
                    mu.getByUsername(username)));
            }
        }
    }
}
