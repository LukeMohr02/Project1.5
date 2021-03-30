package com.MohrShaji.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.MohrShaji.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/*
 * Purpose of this Dao is to send/retrieve info about a reimbursement
 * to/from the database. It then returns the composed Reimbursement Object.
 */
public class UserDao implements GenericDao <User> {
	private static SessionFactory factory;
	private static final Logger LOGGER = Logger.getLogger(UserDao.class);

	private User objectConstructor(ResultSet rs) throws SQLException {
		return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
	}
	
	@Override
	public List<User> getList() {
		Session session = factory.openSession();
		Transaction tx = null;


		try{
			tx = session.beginTransaction();
			List users = session.createQuery("from hibernateuser").list();
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
		return null;
	}

	@Override
	public User getById(int id) {

		return null;
	}
	
	@Override
	public List<User> getByUserId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getByUsername(String username) {

		return null;
	}

	@Override
	public void insert(User t) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userID = null;

		try {
			tx = session.beginTransaction();
			User user = new User();
			userID = (Integer) session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(User t) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			//User user = (User) session.get(User.class,0);
			session.delete(t);
			tx.commit();
		}catch (HibernateException e){
			if(tx!=null)tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
}
