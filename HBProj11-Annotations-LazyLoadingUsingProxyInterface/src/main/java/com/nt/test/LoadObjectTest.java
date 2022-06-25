package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.Movie;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = HibernateUtil.getSession();
		try (factory; session) {
			Movie movie = session.get(Movie.class, 1101);
			if (movie != null)
				System.out.println(movie);
			else
				System.out.println("Movie not found with given Id");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
