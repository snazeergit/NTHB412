package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

//This application throws org.hibernate.NonUniqueObjectException
public class Approach_2_MergeObjectTest_Problem {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();

		//Get Session object
		Session session = HibernateUtil.getSession();

		Transaction tx = null;
		boolean flag = false;
		try {//Introduced from java 9
			tx = session.beginTransaction();

			Product prod1 = session.get(Product.class, 10);//ID must be available in DB table
			if (prod1 != null) {
				//Prepare entity object
				Product prod2 = new Product();
				prod2.setPid(10);
				prod2.setPname("Hayabusa");
				prod2.setPrice(100.0f);
				prod2.setQty(1.0f);

				//The below two methods will tries to put another object(1003) same Product class in L1 Cache
				//so NonUniqueObjectException will be raised
				//session.save(prod2);
				session.update(prod2);

				System.out.println("Object is updated");
				flag = true;
			}

		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} finally {
			if (flag)
				tx.commit();
			else
				tx.rollback();
		}
	}
}
