package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

//Using Try with Resource introduced in Java 9
public class Approach_1_MergeObjectTest {

	public static void main(String[] args) {
		//Get SessionFactory object
		SessionFactory factory = HibernateUtil.getSessionFactory();

		//Get Session object
		Session session = HibernateUtil.getSession();

		Transaction tx = null;
		try (factory; session) {//Introduced from java 9
			tx = session.beginTransaction();

			//Prepare entity object
			Product prod = new Product();
			prod.setPid(1009);
			prod.setPname("cattle");
			prod.setPrice(100.0f);
			prod.setQty(1.0f);

			Product prod1 = session.merge(prod);
			tx.commit();

			System.out.println("Given obejct  data " + prod + " hashCode::" + prod.hashCode());
			System.out.println("Recived obejct  data " + prod1 + " hashCode::" + prod1.hashCode());
			System.out.println("Object is saved  or updated");

		} // The SessionFactory, Session objs will be closed here automatically
		catch (HibernateException he) {
			he.printStackTrace();
			if (tx != null && tx.getRollbackOnly() && tx.getStatus() != null) {
				tx.rollback();
			}

		}
	}

}
