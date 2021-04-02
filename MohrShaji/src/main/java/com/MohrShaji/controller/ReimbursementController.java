package com.MohrShaji.controller;

import com.MohrShaji.model.Reimbursement;
import com.MohrShaji.application.ReimbursementManager;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ReimbursementController {

    ReimbursementManager rs;

    public ReimbursementController() {

    }

    public ReimbursementController(ReimbursementManager rs) {
        this.rs = rs;
    }

    public void getAllReimbursements(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<Reimbursement> reimbursements = rs.listReimbursement();

        response.getWriter().println("List of all reimbursements:\n\n");

        for (Reimbursement u : reimbursements) {
            response.getWriter().println(new Gson().toJson(u));
        }
    }

    public void createReimbursement(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException {
        response.setContentType("application/json");

        String id = request.getParameter("id");
        String amount = request.getParameter("amount");
        String submitted = request.getParameter("submitted");
        String resolved = request.getParameter("resolved");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String resolver = request.getParameter("resolver");
        String statusId = request.getParameter("status_id");
        String typeId = request.getParameter("type_id");

        int idInt;
        float amountFloat = 0;
        Timestamp submittedTS = Timestamp.valueOf(LocalDateTime.now());
        Timestamp resolvedTS = Timestamp.valueOf(LocalDateTime.now());
        int authorInt = 0;
        int resolverInt = 0;
        int statusIdInt = 0;
        int typeIdInt = 0;

        if (id == null) {
            id = "0";
        }

        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid 'id' input, please enter an integer.\n");
            return;
        }

        if (amount != null) {
            try {
                amountFloat = Float.parseFloat(amount);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid 'amount', please enter a float.");
                return;
            }
        }

        if (submitted != null) {
            try {
                submittedTS = Timestamp.valueOf(submitted);
            } catch (IllegalArgumentException e) {
                response.getWriter().println("Invalid 'submitted' timestamp, please enter a valid timestamp string.");
                return;
            }
        }

        if (resolved != null) {
            try {
                resolvedTS = Timestamp.valueOf(resolved);
            } catch (IllegalArgumentException e) {
                response.getWriter().println("Invalid 'resolved' timestamp, please enter a valid timestamp string.");
                return;
            }
        }

        if (author != null) {
            try {
                authorInt = Integer.parseInt(author);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid 'author', please enter an integer.");
                return;
            }
        }

        if (resolver != null) {
            try {
                resolverInt = Integer.parseInt(resolver);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid 'resolver', please enter an integer.");
                return;
            }
        }

        if (statusId != null) {
            try {
                statusIdInt = Integer.parseInt(statusId);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid 'status_id', please enter an integer.");
                return;
            }
        }

        if (typeId != null) {
            try {
                typeIdInt = Integer.parseInt(typeId);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid 'type_id', please enter an integer.");
                return;
            }
        }

        response.getWriter().println("Created new reimbursement:\n\n" +
            new Gson().toJson(
            rs.createReimbursement(idInt, amountFloat, submittedTS, resolvedTS, description, authorInt, resolverInt, statusIdInt, typeIdInt)));
    }

    public void updateReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        String amount = request.getParameter("amount");
        int idInt;
        float amountFloat;

        if (id == null) {
            response.getWriter().write("Please enter an 'id' as an integer.");
            return;
        }

        if (amount == null) {
            response.getWriter().write("Please enter an 'amount' as a float.");
            return;
        }

        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid 'id' input, please enter an integer.\n");
            return;
        }

        try {
            amountFloat = Float.parseFloat(amount);
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid 'amount' input, please enter a float.\n");
            return;
        }


        rs.updateReimbursement(idInt, amountFloat);
    }

    public void deleteReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        int idInt;

        try {
            idInt = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid 'id', please enter a valid integer id");
            return;
        }

        try {
            response.getWriter().println("Deleted reimbursement:\n\n" +
                new Gson().toJson(
                rs.deleteReimbursement(idInt)));
        } catch(IllegalArgumentException e) {
            response.getWriter().println("Invalid 'id', please enter a valid integer id");
        }
    }

    public void getReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String id = request.getParameter("id");
        String userId = request.getParameter("user_id");

        int idInt;
        int userIdInt;

        if (id != null) {

            try {
                idInt = Integer.parseInt(id);

                response.getWriter().println("Found reimbursement:\n\n" +
                    new Gson().toJson(
                    rs.getById(idInt)));
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid 'id' input, please enter an integer.\n");
            }

        } else if (userId != null) {

            try {
                userIdInt = Integer.parseInt(userId);

                response.getWriter().println("Found reimbursement:\n\n" +
                    new Gson().toJson(
                    rs.getByUserId(userIdInt)));
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid 'user_id' input, please enter an integer.\n");
            }

        } else {
            response.getWriter().write("Please specify either an 'id' or a 'user_id'.");
        }
    }
}
