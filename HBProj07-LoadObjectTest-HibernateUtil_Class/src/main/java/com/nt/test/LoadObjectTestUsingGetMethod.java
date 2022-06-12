package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

//Using Try with Resource introduced in Java 9
public class LoadObjectTestUsingGetMethod {

	public static void main(String[] args) throws InterruptedException {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();

		//Get Session object
		Session session = HibernateUtil.getSession();

		try (factory; session) {//Introduced from java 9
			Product prod = session.get(Product.class, 1002);
			if (prod == null)
				System.out.println("No record found with the given PID");
			else {
				System.out.println("Record found");
				System.out.println(prod);
			}

		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			he.printStackTrace();

		}
	}

}
