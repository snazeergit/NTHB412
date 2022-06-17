package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
		//Bootstrap/Activate Hibernate
		Configuration cfg = new Configuration();
		
		//Specify the hibernate cfg file name and location
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		
		//Build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory factory = cfg.buildSessionFactory();
		
		//Create Session object
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			
			//Begin Transaction
			tx = session.beginTransaction();//internally calls con.setAutoCommit(false) to disable autoCommit mode on Db S/W
			
			//Prepare entity object
			Product prod = new Product();
			prod.setPid(1002);
			prod.setPname("tablespoon");
			prod.setPrice(568.5f);
			prod.setQty(1.0f);
			
			//Save(-) method without generator in mapping file returns the Pid value 1002(Primary Key) as a serialized object
			//Save(-) method with generator in mapping file returns the Max value in Pid column+1 (Primary Key column) as a serialized object
			//Max value in Pid is 1009 then it will return 1010
			Integer idVal =(Integer) session.save(prod);// Gives persistence instruction to hibernate to save object(insert object data as the record)
			tx.commit();//internally calls con.commit() method to make insertion execution result permanent
			System.out.println("Object is saved-(Record is inserted)"+idVal);
		} catch (HibernateException he) {
			he.printStackTrace();
			tx.rollback();//internally calls con.rollback90 method to rollback the result of query execution.
			System.err.println("Object is not saved-(Record is not inserted)");
		}
	}

}
