package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

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
		boolean flag = false;
		try {
			// begin Transaction
			tx = session.beginTransaction();

			//Prepare Entity Object
			Product prod = new Product();
			prod.setPid(100);
			prod.setPname("Mixer");
			prod.setPrice(4500.0f);
			prod.setQty(1.0f);

			//Save obj
			Integer idVal = (Integer) session.save(prod);
			System.out.println("The generated id value : " + idVal);
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		} finally {

			if (flag) {
				//Commit the current resource transaction
				tx.commit();
				System.out.println("Object is saved");
			} else {
				//Roll back the current resource transaction. 
				tx.rollback();
				System.out.println("Object is not saved");
			}
			try {
				if (session != null) {
					session.close();
				}
			} catch (HibernateException he) {
				he.printStackTrace();
			}
			try {
				if (factory != null) {
					factory.close();
				}
			} catch (HibernateException he) {
				he.printStackTrace();
			}

		}

	}

}
