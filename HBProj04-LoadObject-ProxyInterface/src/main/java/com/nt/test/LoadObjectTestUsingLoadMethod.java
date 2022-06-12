package com.nt.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.IProduct;
import com.nt.entity.Product;

public class LoadObjectTestUsingLoadMethod {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		try (factory; session) {
			//here 1002, 1003 are the pid's copied from the Product table.
			IProduct prod = session.load(Product.class, 1003);
			System.out.println(prod.getClass() + " " + prod.getClass().getSuperclass() + " "
					+ Arrays.toString(prod.getClass().getInterfaces()));

			System.out.println(prod.getPid() + " " + prod.getPname() + " " + prod.getPrice() + " " + prod.getQty());
		} catch (ObjectNotFoundException notFoundException) {
			System.err.println("**************No Record found with the given PID*************");
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}

}
