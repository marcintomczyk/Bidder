package com.tomczyk.bidder.realDb.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.logic.BidManager;
import com.tomczyk.bidder.logic.BidManagerImpl;
import com.tomczyk.bidder.model.Bid;

/**
 * Testing fetching (real data) operation with a real database using the BidderManager
 * 
 * Test excluded from the suite and from the maven build. Hibernate tries to remove fk constraint
 * 
 * TODO: check: why is it happening ?
 * 
 */
public class FetchingBidsViaRealManager extends BidderBase {

	private BidManager bidManager;
	
	/*
	 * We make some assumptions:
	 * - there are real data in the database
	 * 
	 * This is the simplest possible test as for something more complex
	 * there is a need to prepare db with some specific test data
	 */
	@Test
	public void canBidsBeFetched() {
		
		bidManager = new BidManagerImpl();
		
		List<Bid> allRealBids = bidManager.findAll();
		
		assertFalse(allRealBids.isEmpty());
	}
	
	@Test
	public void canASpecificBidBeFetched() throws SQLException {
		
		bidManager = new BidManagerImpl();
		
		Bid foundBid = bidManager.getById((long) 7);

		assertEquals(new Long(7), foundBid.getId());
		
	}
	
}
