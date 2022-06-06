package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

//Using Try with Resource introduced in Java 9
public class SaveObjectTestTWR2 {

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

			// begin Transaction
			tx = session.beginTransaction();

			//prepare entity object
			Product prod = new Product();
			prod.setPid(100);
			prod.setPname("Mixer");
			//prod.setPrice(4500.0f);
			//prod.setQty(1.0f);

			//SAve Obj
			Integer idVal = (Integer) session.save(prod);
			System.out.println("The generated id value : " + idVal);

			//Commit the current resource transaction
			tx.commit();
			System.out.println("Object is saved");
		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getRollbackOnly() && tx.getStatus() != null) {
				//Roll back the current resource transaction. 
				tx.rollback();
				System.out.println("Object is not saved");
			}
		}

	}

}
