package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class LoadObjectTestUsingGetMethod {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		try (factory; session) {
			//here 1002, 1003 are the pid's copied from the Product table.
			Product prod = session.get(Product.class, 1002);
			if (prod == null)
				System.out.println("No product with with provided PID");
			else
				System.out.println(prod);
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}

}
