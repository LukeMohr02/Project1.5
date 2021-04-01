package com.MohrShaji.servlet;

import com.MohrShaji.Application.ManageUser;
import com.MohrShaji.controller.UserController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/get")
public class UserGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserController uc = new UserController(new ManageUser());
        uc.getUser(request, response);
    }
}
