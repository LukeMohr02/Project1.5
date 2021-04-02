package com.MohrShaji;

import com.MohrShaji.application.UserManager;
import com.MohrShaji.application.ReimbursementManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Driver {
    static UserManager mu = new UserManager();
    static ReimbursementManager rm = new ReimbursementManager();
    public static void main(String[] args) {


       /*  mu.createUser(24,"noel","pasword","nshaj","shaji","nshaji916@gmail.com",1);
        mu.listUsers();*/
      //  mu.deleteUser(24);

        rm.createReimbursement(0, 100, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "description", 25, 25, 1, 1);
    }
}
