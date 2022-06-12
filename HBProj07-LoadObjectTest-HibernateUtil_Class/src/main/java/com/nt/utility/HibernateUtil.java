package com.nt.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;

	//Static block will be executed as soon as the HibernateUtil class is loaded into JVM memory for the first time.
	static {
		System.out.println("HibernateUtil:: static block");
		Configuration cfg = new Configuration();
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
	}

	//static factory method for SessionFactory object
	public static SessionFactory getSessionFactory() {
		return factory;
	}

	//Static factory method for Session object
	public static Session getSession() {
		Session session = null;
		if (factory != null)
			session = factory.openSession();
		return session;
	}

}
