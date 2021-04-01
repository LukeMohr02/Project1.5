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
//        this.request = request;
//        this.response = response;
    }

//    public void getUser(Request request, Response response) {
//        response.setContentType("application/json");
//        User user = new User();
//
//    }


    public void getAllUsers(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<User> users = mu.listUsers();

        System.out.println("List of all users:\n\n");

        for (User u : users) {
            response.getWriter().println(new Gson().toJson(u));
        }
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String roleId = request.getParameter("role_id");

        if (id == null) {
            id = "0";
        }

        int idInt = Integer.parseInt(id);
        int roleIdInt = Integer.parseInt(roleId);

        response.getWriter().println("Created new user:\n\n" +
                new Gson().toJson(
                mu.createUser(idInt, username, password, firstName, lastName, email, roleIdInt)));
    }

    public void updateUser() {

    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        response.getWriter().println("Created new user:\n\n" +
                new Gson().toJson(
                mu.deleteUser(userId)));
    }

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("id");

        if (userId != null) {
            try {
                int userIdInt = Integer.parseInt(userId);

                //TODO: get user by id here
                System.out.println(userIdInt);
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid 'id' input, please enter an integer.");
            }
        }

        String username = request.getParameter("username");

        //TODO: get user by username here
        System.out.println(username);

    }
}
