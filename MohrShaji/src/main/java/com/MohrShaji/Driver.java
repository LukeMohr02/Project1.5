package com.MohrShaji;

import com.MohrShaji.Application.ManageUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Driver {
    static ManageUser mu = new ManageUser();
    public static void main(String[] args) {


       /*  mu.createUser(24,"noel","pasword","nshaj","shaji","nshaji916@gmail.com",1);
        mu.listUsers();*/
       mu.listUsers();
      //  mu.deleteUser(24);
    }
}
