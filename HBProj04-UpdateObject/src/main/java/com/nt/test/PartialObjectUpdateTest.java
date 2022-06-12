package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

//Using Try with Resource introduced in Java 9
public class PartialObjectUpdateTest {

	public static void main(String[] args) {
		//Bootstarp / Activate the hibernate
		Configuration cfg = new Configuration();

		//specify the  hibernate cfg file name and location
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");

		//build SessionFactory having all services specified in cfg file and  mapping file
		SessionFactory factory = cfg.buildSessionFactory();

		// create Session obj 
		Session session = factory.openSession();
		Transaction tx = null;
		try (factory; session) {//Introduced from java 9

			Product prod = session.get(Product.class, 1008);
			if (prod == null) {
				System.out.println("Record not found");
			} else {
				System.out.println("Before partial update : " + prod);

				tx = session.beginTransaction();
				prod.setPname("Calc");
				tx.commit();
				System.out.println("After partial upate: " + prod);
				System.out.println("Object is partially updated");
			}
		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getRollbackOnly() && tx.getStatus() != null) {
				//Roll back the current resource transaction. 
				tx.rollback();
				System.out.println("Object is not updated");
			}
		}
	}
}
