package com.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MyHibernateUtil {
	
	private static SessionFactory sf = null;

	private MyHibernateUtil(){
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.applySettings(configuration.getProperties()).build();
		sf = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getMyHibernateUtilInstance(){
		if(sf == null){
			new MyHibernateUtil();
		}
		return sf;
	}
	
	public static void closeSessionFactory(){
		if(sf != null){
			sf.close();
		}
	}
	
}
