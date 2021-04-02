//package com.MohrShaji.servlet;
//
//import com.MohrShaji.Application.ManageUser;
//import com.MohrShaji.controller.UserController;
//import com.MohrShaji.model.Reimbursement;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/reimbursement/*")
//public class ReimbursementServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ReimbursementController rc = new ReimbursementController(new ManageUser());
//        String urlEnd = request.getRequestURI().replaceFirst(".*/reimbursement/", "");
//
//        switch(urlEnd) {
//            case "create":
//                uc.createUser(request, response);
//                break;
//            case "delete":
//                uc.deleteUser(request, response);
//                break;
//            case "get":
//                uc.getUser(request, response);
//                break;
//            case "list":
//                uc.getAllUsers(response);
//                break;
//            case "update":
//                uc.updateUser(request, response);
//                break;
//            default:
//                break;
//        }
//    }
//}
