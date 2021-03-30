package com.MohrShaji.Application;


import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import com.MohrShaji.model.Reimbursement;
import com.MohrShaji.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReimbursmentManager {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().addAnnotatedClass(Reimbursement.class).buildSessionFactory();
        } catch (Throwable t) {
            System.err.println("Error! Could not create Session Factory" + t);
            throw new ExceptionInInitializerError(t);
        }

        ReimbursmentManager rm = new ReimbursmentManager();


    }

    public Integer createReimbursment(int id, float amount, Timestamp submitted, Timestamp resolved, String description, int author ,
                                      int resolver, int status_id, int type_id) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer userID = null;

        try {
            tx = session.beginTransaction();
            Reimbursement reimbursement = new Reimbursement();
            reimbursement.setAmount(amount);
            reimbursement.setSubmitted(submitted);
            reimbursement.setResolved(resolved);
            reimbursement.setDescription(description);
            reimbursement.setAuthor(author);
            reimbursement.setResolver(resolver);
            reimbursement.setStatus_id(status_id);
            reimbursement.setType_id(type_id);
            id = (Integer) session.save(reimbursement);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userID;
    }


/*
    public void listReimbursment(){
        Session session = factory.openSession();
        Transaction tx = null;


        try{
            tx = session.beginTransaction();
            List users = session.createQuery("from employee").list();
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

    public void updateReimbursement(Integer userID,String username){
        Session session = factory.openSession();
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



    public void deleteReimbursement(Integer userID){
        Session session = factory.openSession();
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
*/

}
