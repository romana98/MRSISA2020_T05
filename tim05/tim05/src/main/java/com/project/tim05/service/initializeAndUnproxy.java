package com.project.tim05.service;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

public class initializeAndUnproxy {
	
	@SuppressWarnings("unchecked")
	public static <T> T initAndUnproxy(T entity) {
	    if (entity == null) {
	        return null;
	    }

	    Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
	    return entity;
	}

}
