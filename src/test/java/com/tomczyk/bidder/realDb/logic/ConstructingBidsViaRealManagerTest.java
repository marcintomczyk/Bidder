package com.tomczyk.bidder.realDb.logic;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tomczyk.bidder.action.exception.BidderException;
import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.factory.BidFactory;
import com.tomczyk.bidder.factory.BidFactorySimpleImpl;
import com.tomczyk.bidder.logic.BidManager;
import com.tomczyk.bidder.logic.BidManagerImpl;
import com.tomczyk.bidder.model.Bid;

/**
 * Testing persisting real data with a real database using the BidderManager
 */
public class ConstructingBidsViaRealManagerTest extends BidderBase {

	private static BidFactory bidFactory;
	
	private static BidManager bidManager;
	
	@BeforeClass
	public static void setupBidManager() throws SQLException {

		bidFactory = new BidFactorySimpleImpl();
		bidManager = new BidManagerImpl();
		
	}

	
	@Test
	public void canBidBeConstructed() throws SQLException, BidderException {
		
		Bid bid = bidFactory
				.getBidWithProvidedId((long) 3012, new BigDecimal("1099.99"));
		
		bidManager.save(bid);
		
		/*
		 * without it the entityManager is closed, this is because
		 * save method finished and we are starting a completely new method
		 */
		bidManager = new BidManagerImpl();
		
		Bid foundBid = bidManager.getById((long) 7);

		assertEquals(new Long(7), foundBid.getId());
		
	}

}
