package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

import jakarta.transaction.Transaction;

//Using Try with Resource introduced in Java 9
public class LoadAndDeleteObjectTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		//Bootstarp / Activate the hibernate
		Configuration cfg = new Configuration();

		//specify the  hibernate cfg file name and location
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");

		//build SessionFactory having all services specified in cfg file and  mapping file
		SessionFactory factory = cfg.buildSessionFactory();

		// create Session obj 
		Session session = factory.openSession();
		org.hibernate.Transaction tx = null;
		try (factory; session) {//Introduced from java 9
			tx = session.beginTransaction();
			Product prod = session.get(Product.class, 1006);
			if (prod == null) {
				System.out.println("Record not found");
			} else {
				session.delete(prod);
				tx.commit();
				System.out.println("Record is deleted");
			}
		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getRollbackOnly() && tx.getStatus() != null) {
				//Roll back the current resource transaction. 
				tx.rollback();
				System.out.println("Record is not deleted");
			}
		}
	}

}
