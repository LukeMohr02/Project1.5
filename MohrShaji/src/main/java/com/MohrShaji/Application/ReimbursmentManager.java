package com.MohrShaji.Application;


import java.sql.Timestamp;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import com.MohrShaji.Util.HibernateUtil;
import com.MohrShaji.model.Reimbursement;
import com.MohrShaji.model.User;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class ReimbursmentManager {
    static StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    static Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
    static SessionFactory factory = meta.getSessionFactoryBuilder().build();

    public static void main(String[] args) {

    }


    public void createReimbursment(int id, float amount, Timestamp submitted, Timestamp resolved, String description, int author,
                                   int resolver, int status_id, int type_id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();


        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setId(id);
        reimbursement.setAmount(amount);
        reimbursement.setSubmitted(submitted);
        reimbursement.setResolved(resolved);
        reimbursement.setDescription(description);
        reimbursement.setAuthor(author);
        reimbursement.setResolver(resolver);
        reimbursement.setStatus_id(status_id);
        reimbursement.setType_id(type_id);
        session.save(reimbursement);
        tx.commit();
        System.out.println("Reimbursement saved");
        session.close();


    }


    public void listReimbursment() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        tx = session.beginTransaction();
        List reimbursement = session.createQuery("from Reimbursement").list();
        for (Iterator iterator = reimbursement.iterator();
             iterator.hasNext(); ) {
            Reimbursement re = (Reimbursement) iterator.next();
            System.out.println("Reimbursement ID: " + re.getId());
            System.out.println("Amount: " + re.getAmount());
        }
        tx.commit();


        session.close();

    }

    public void updateReimbursement(Integer id, int amount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Reimbursement reimbursement = (Reimbursement) session.get(Reimbursement.class, id);
            reimbursement.setAmount(amount);
            session.update(reimbursement);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }


    public void deleteReimbursement(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Reimbursement reimbursement = (Reimbursement) session.get(Reimbursement.class, id);
            session.delete(reimbursement);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Reimbursement getById(int id) {
        Session session = null;
        Reimbursement reimbursement = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            reimbursement = session.get(Reimbursement.class, id);
            Hibernate.initialize(reimbursement);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        System.out.println(reimbursement);
        return reimbursement;

    }


    public void getByUserId(int author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;


        tx = session.beginTransaction();
        List reimbursement = session.createQuery("from Reimbursement where author = :author").setParameter("author",author).list();

        for (Iterator iterator = reimbursement.iterator();
             iterator.hasNext(); ) {
            Reimbursement re = (Reimbursement) iterator.next();
            System.out.println("Reimbursement ID: " + re.getId());
            System.out.println("Amount: " + re.getAmount());
        }
        tx.commit();


        session.close();
    }
}
