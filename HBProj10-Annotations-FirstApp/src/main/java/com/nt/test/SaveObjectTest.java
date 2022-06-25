package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.Movie;
import com.nt.utility.HibernateUtil;

public class SaveObjectTest {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try (factory; session) {
			tx = session.beginTransaction();
			Movie movie = new Movie(1101, "RRR", "NTR-Charan", 400.0f);
			session.save(movie);
			tx.commit();
			System.out.println("Movie record saved to database");
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.getRollbackOnly() && tx.getStatus() != null) {
				tx.rollback();
			}
		}
	}
}
