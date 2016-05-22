package com.tomczyk.bidder.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * This is a singleton. It is responsible for:
 * - creating the EntityManagerFactory if it does not exist
 * - closing the EntityManagerFactory
 * - returning existing instance of the EntityManagerFactory
 * 
 * @see PersistenceAppListener
 */
public class PersistenceManager {

	private static final Logger log = Logger.getLogger(PersistenceManager.class);

	private static final PersistenceManager singleton = new PersistenceManager();

	protected EntityManagerFactory emf;

	public static PersistenceManager getInstance() {
		return singleton;
	}

	private PersistenceManager() {}

	public EntityManagerFactory getEntityManagerFactory() {
		if (emf == null)
			createEntityManagerFactory();
		return emf;
	}

	public void closeEntityManagerFactory() {

		if (emf != null) {
			emf.close();
			emf = null;
		
			if (log.isDebugEnabled()) {
				log.debug("EntityManager closed at: " + new java.util.Date());
			}

		}
	}

	protected void createEntityManagerFactory() {

		this.emf = Persistence.createEntityManagerFactory("bidderLocalPU");
		
		if (log.isDebugEnabled()) {
			log.debug("EntityManager created at: " + new java.util.Date());
		}
	}
}
