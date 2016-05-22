package com.tomczyk.bidder.realDb.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.model.Bid;

/**
 * Testing real operation with real database using directly EntityManager
 */
public class ConstructingBidsViaRealEntityManagerTest extends BidderBase {

	@Test
	public void canBidBeConstructed() {
		
		/*
		 * we did not define bid with this id in a dataset so this will be ok
		 */
		Bid bid = bidFactory.getBidWithProvidedId((long) 110, new BigDecimal("0.99"));
		
		BigDecimal actualCost = bid.getCost();
		BigDecimal expectedCost = new BigDecimal("0.99");

		/*
		 * do not use equals like assertEquals(actualCost, expectedCost) as in this case
		 * 118.00 <> 118.0
		 * 
		 * use compareTo instead
		 */
		assertEquals(0, actualCost.compareTo(expectedCost));
		
		et.begin();
		em.persist(bid);
		et.commit();

		Long id = bid.getId();
		et.begin();
		Bid foundBid = em.find(Bid.class, id);
		et.commit();

		assertEquals(0, expectedCost.compareTo(foundBid.getCost()));
		assertEquals(0, (bid.getCost()).compareTo(foundBid.getCost()));
		
	}

}