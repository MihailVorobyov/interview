package ru.vorobev.interview.homework_05.services;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryService {
	
	public static SessionFactory getSessionFactory() {
		return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
}
