package com.iFundi.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.iFundi.models.Customer;

public class SessionUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		// TODO Auto-generated method stub
		try {
			Configuration config=new Configuration();
			config.addClass(Customer.class);
			return config
					.buildSessionFactory(new StandardServiceRegistryBuilder().build());
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("error building sessions");
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
