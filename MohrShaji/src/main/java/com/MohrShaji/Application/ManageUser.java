package com.MohrShaji.Application;


import java.util.Arrays;
import java.util.List;
import java.util.Iterator;

import com.MohrShaji.Util.HibernateUtil;
import com.MohrShaji.model.Reimbursement;
import com.MohrShaji.model.User;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class ManageUser {

    static StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    static Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    static SessionFactory factory = meta.getSessionFactoryBuilder().build();

    public static void main(String[] args) {

    }

    public void createUser(int user_id, String username, String password, String firstname, String lastname, String email, int role_id) {


        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Reimbursement re = new Reimbursement();
        re.setResolver(user_id);
        User user = new User();
        user.setUser_id(user_id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setRole_id(role_id);



        session.save(user);
        t.commit();
        System.out.println("Saved Entity");
        session.close();

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


    public List<User> listUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        tx = session.beginTransaction();
        List users = session.createQuery("from User").list();
        for (Iterator iterator = users.iterator();
             iterator.hasNext(); ) {
            User user = (User) iterator.next();
            System.out.println("First name: " + user.getFirstname());
            System.out.println("Last name: " + user.getLastname());
            System.out.println("----------");
        }
        tx.commit();


        session.close();

        return null;
    }

    public void updateUser(Integer userID, String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, userID);
            user.setUsername(username);
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }


    public void deleteUser(Integer userID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, userID);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public User getByUserId(int id) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = session.get(User.class, id);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        System.out.println(user);
        return user;

    }

    public User getByUsername(String username) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            org.hibernate.query.Query query= session.createQuery("from User where username=:username");
            query.setParameter("username", username);
            User user1 = (User) query.uniqueResult();
            System.out.println(user1);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }
}