package com.tomczyk.bidder.logic;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tomczyk.bidder.action.exception.DuplicatedBidException;
import com.tomczyk.bidder.model.Bid;
import com.tomczyk.bidder.persistence.PersistenceAppListener;
import com.tomczyk.bidder.persistence.PersistenceManager;

/**
 * Class is responsible for persisting a bid in a database.
 * 
 * To be honest, there is nothing special here, but notice: - how an
 * EntityManagerFactory is obtained - how an EntityManager is created
 * 
 * Remember: - EntityManagerFactory is heavy so we should not create it with
 * every request etc. (this is why we have PersistenceManager as a Singleton and
 * PersistenceAppListener) - EntityManager is lightweight so we can create it
 * when we need it (this is what is happening here)
 * 
 * @see PersistenceManager
 * @see PersistenceAppListener
 */
public class BidManagerImpl implements BidManager {

	private static final Logger log = Logger.getLogger(BidManagerImpl.class);

	private EntityManagerFactory emf;
	private EntityManager em;

	/**
	 * 1. We get the EntityManagerFactory if it is available, otherwise it will be created 
	 * 2. We create the EntityManager
	 */
	public BidManagerImpl() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bid> findAll() {

		try {
			Query query = em.createQuery("select b FROM Bid b");
			return query.getResultList();
		} finally {
			em.close();
		}
		
	}

	@Override
	public void save(Bid bid) throws DuplicatedBidException {
		
		try {
			EntityTransaction et = em.getTransaction();
			try {
				
				et.begin();
				em.persist(bid);
				et.commit();
				
			} catch(PersistenceException pex) {
				log.error("exception.getMessage: " + pex.getMessage());
				log.error("exception.getClass: " + pex.getClass());
				
				throw new DuplicatedBidException("There is a bid with this id in the database already !!!");
			}
			
			finally {
				if (et.isActive())
					et.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public Bid getById(Long id) {
		Bid foundBid = em.find(Bid.class, id);
		return foundBid;
	}

}