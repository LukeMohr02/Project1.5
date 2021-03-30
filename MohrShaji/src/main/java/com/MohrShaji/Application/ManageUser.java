package com.MohrShaji.Application;


import java.util.List;
import java.util.Date;
import java.util.Iterator;

import com.MohrShaji.Util.HibernateUtil;
import com.MohrShaji.model.User;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ManageUser {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Throwable t) {
            System.err.println("Error! Could not create Session Factory" + t);
            throw new ExceptionInInitializerError(t);
        }

        ManageUser mu = new ManageUser();


    }

    public void createUser(int user_id, String username, String password, String firstname, String lastname, String email, int role_id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();

        User user = new User();
        user.setUser_id(user_id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setRole_id(role_id);

        session.save(user);
        session.getTransaction().commit();

/*
        try {
            tx = session.beginTransaction();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setRole_id(role_id);
            userID = (Integer) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;*/
    }



    public void listUsers(){
        Session session =HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        try{
            tx = session.beginTransaction();
            List users = session.createQuery("from User").list();
            for (Iterator iterator = users.iterator();
            iterator.hasNext();){
                User user = (User) iterator.next();
                System.out.println("First name: " + user.getFirstname());
                System.out.println("Last name: " + user.getLastname());
            }
            tx.commit();
            }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateUser(Integer userID,String username){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
         tx = session.beginTransaction();
         User user = (User)session.get(User.class,userID);
         user.setUsername(username);
         session.update(user);
         tx.commit();
        }catch (HibernateException e){
           if(tx!=null)tx.rollback();
        }finally {
            session.close();
        }
    }



    public void deleteUser(Integer userID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class,userID);
            session.delete(user);
            tx.commit();
        }catch (HibernateException e){
           if(tx!=null)tx.rollback();
           e.printStackTrace();
        }finally {
            session.close();
        }

    }


}
