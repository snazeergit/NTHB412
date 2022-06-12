package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

//Using Try with Resource introduced in Java 9
public class ObjectReloadTest {

	public static void main(String[] args) throws InterruptedException {
		//Bootstarp / Activate the hibernate
		Configuration cfg = new Configuration();

		//specify the  hibernate cfg file name and location
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");

		//build SessionFactory having all services specified in cfg file and  mapping file
		SessionFactory factory = cfg.buildSessionFactory();

		// create Session obj 
		Session session = factory.openSession();

		try (factory; session) {//Introduced from java 9

			Product prod = session.get(Product.class, 1008);
			if (prod == null) {
				System.out.println("Record not found");
			} else {
				System.out.println("Before refreshed: " + prod);
				System.out.println("Sleeping for 30 seconds for you to modify the 1008 record in db table");
				Thread.sleep(30000);
				session.refresh(prod);
				System.out.println("After refreshed: " + prod);
				System.out.println("Object is refreshed");
			}
		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			he.printStackTrace();
			System.out.println("Object is not refreshed");
		}
	}

}
