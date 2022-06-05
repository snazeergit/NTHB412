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
		Configuration oracfg = new Configuration();
		Configuration mysqlcfg = new Configuration();
		
		//Specify the hibernate cfg file name and location
		oracfg.configure("com/nt/cfgs/oracle.cfg.xml");
		mysqlcfg.configure("com/nt/cfgs/mysql.cfg.xml");
		
		//Build SessionFactory having all services specified in cfg file and mapping file
		SessionFactory orafactory = oracfg.buildSessionFactory();
		SessionFactory mysqlfactory = mysqlcfg.buildSessionFactory();
		
		//Create Session object
		Session orasession = orafactory.openSession();
		Session mysqlsession = mysqlfactory.openSession();
		Transaction tx1 = null;
		Transaction tx2 = null;
		try {
			
			//Begin Transaction
			tx1 = orasession.beginTransaction();//internally calls con.setAutoCommit(false) to disable autoCommit mode on Db S/W
			tx2=mysqlsession.beginTransaction();
			//Prepare entity object
			Product prod = new Product();
			prod.setPid(1004);
			prod.setPname("table");
			prod.setPrice(5683.5f);
			prod.setQty(1.0f);
			
			//Save object
			orasession.save(prod);// Gives persistence instruction to hibernate to save object(insert object data as the record)
			mysqlsession.save(prod);
			
			tx1.commit();//internally calls con.commit() method to make insertion execution result permanent
			tx2.commit();
			System.out.println("Object is saved-(Record is inserted)");
		} catch (HibernateException he) {
			he.printStackTrace();
			tx1.rollback();//internally calls con.rollback90 method to rollback the result of query execution.
			tx2.rollback();
			System.err.println("Object is not saved-(Record is not inserted)");
		}
	}

}
