package com.ajax.base.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final HibernateUtil instance = new HibernateUtil();
	private SessionFactory sessionFactory;

	private HibernateUtil() {
		Configuration conf = new Configuration().configure();
		sessionFactory = conf.buildSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session openSession() {
		return sessionFactory.openSession();
	}

	public static HibernateUtil getInstance() {
		return instance;
	}
}
