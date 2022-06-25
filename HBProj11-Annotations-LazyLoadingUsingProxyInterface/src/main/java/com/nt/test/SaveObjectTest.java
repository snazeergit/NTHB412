package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.Movie;
import com.nt.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		//get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();
		//get Session object
		Session ses = HibernateUtil.getSession();
		Transaction tx = null;
		try (ses; factory) {
			//begin Tx
			tx = ses.beginTransaction();
			//prepare object
			Movie movie = new Movie();
			movie.setMId(1201);
			movie.setMName("Pupsa");
			//save object
			ses.save(movie);
			tx.commit();
			System.out.println("Object is saved");
		} //try
		catch (Exception e) {
			if (tx != null && tx.getStatus() != null && tx.getRollbackOnly()) {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			e.printStackTrace();
		}

	}

}
