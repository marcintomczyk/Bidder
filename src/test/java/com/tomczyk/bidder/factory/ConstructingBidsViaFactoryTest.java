package com.tomczyk.bidder.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tomczyk.bidder.model.Bid;
import com.tomczyk.bidder.model.Site;

/**
 * Testing factory operations
 */
public class ConstructingBidsViaFactoryTest {

	private static BidFactory bidFactory;
	
	@BeforeClass
	public static void setUp() {
		bidFactory = new BidFactorySimpleImpl();
	}
	
	@Test
	public void canABidBeCreated() {
		
		Bid createdBid = bidFactory.getBidWithProvidedId((long) 91, new BigDecimal("91.99"));
		
		assertNotNull(createdBid);
		assertEquals(new Long(91), createdBid.getId());
		
		assertEquals(0, createdBid.getCost().compareTo(new BigDecimal("91.99")));
		
	}
	
	@Test
	public void doesABidHaveASite() {
		
		Bid createdBid = bidFactory.getBidWithProvidedId((long) 91, new BigDecimal("91.91"));
		
		assertNotNull(createdBid.getSites());
		assertFalse(createdBid.getSites().isEmpty());
		
		//must be in the collection if previous assert is ok
		Site firstSite = createdBid.getSites().get(0);
		assertEquals(createdBid, firstSite.getBid());
			
	}
	
	@AfterClass
	public static void tearDown() {
		
		bidFactory = null;
		
	}
	
}
