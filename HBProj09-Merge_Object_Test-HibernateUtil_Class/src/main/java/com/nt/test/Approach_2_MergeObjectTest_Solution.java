package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

//Using Try with Resource introduced in Java 9
public class Approach_2_MergeObjectTest_Solution {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();

		//Get Session object
		Session session = HibernateUtil.getSession();

		Transaction tx = null;
		boolean flag=false;
		try {
			tx = session.beginTransaction();

			Product prod1 = session.get(Product.class, 11);
			if (prod1 != null) {
				System.out.println(prod1);
				//Prepare entity object
				Product prod2 = new Product();
				prod2.setPid(10);
				prod2.setPname("Hayabusa");
				prod2.setPrice(100.0f);
				prod2.setQty(1.0f);
				System.out.println(prod1.hashCode() + "  " + prod2.hashCode());
				Product prod3 = session.merge(prod2);
				System.out.println(prod3);
				System.out.println(prod1.hashCode() + "  " + prod2.hashCode() + "  " + prod3.hashCode());
				System.out.println("Object is merged");
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