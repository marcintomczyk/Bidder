package com.tomczyk.bidder.realDb.model;

import java.math.BigDecimal;
import org.junit.Test;

import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.model.Bid;

/**
 * Testing real operation with real database using directly EntityManager
 */
public class ConstructingBidsViaRealEntityManagerExceptionsTest extends
		BidderBase {

	// @Test(expected = PersistenceException.class)
	public void cannotCreateABidwithExistingId() {
		/*
		 * we defined bid with this id in a predefined dataset so
		 * RollbackException should be thrown
		 */
		Bid bid = bidFactory.getBidWithProvidedId((long) 5, new BigDecimal("55.59"));

		et.begin();

		em.persist(bid);
		et.commit();
		em.close();
	}

	/*
	 * Very important test, because of the requirement that id must be passed
	 * (in json format) from the view to the action, so this means that in fact
	 * a user defines the key, not a database itself.
	 * 
	 * javax.persistence.PersistenceException:
	 */
	@Test(expected = javax.persistence.PersistenceException.class)
	public void cannotPersistABidWithoutId() {

		Bid bidWithoutId = new Bid();

		et.begin();

		em.persist(bidWithoutId);
		et.commit();

	}

}