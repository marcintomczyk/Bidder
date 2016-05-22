package com.tomczyk.bidder.persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * We need to be sure that EntityManagerFactory is closed - this is why
 * we use application listener
 * 
 * - we use lazy loading strategy for the creation of the EntityManagerFactory,
 * so you will not find here code responsible for creating the EntityManagerFactory
 *
 * @see PersistenceManager
 * @see BidManagerImpl
 */
public class PersistenceAppListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent evt) {}

	public void contextDestroyed(ServletContextEvent evt) {
		PersistenceManager.getInstance().closeEntityManagerFactory();
	}

}
