package com.tomczyk.bidder.mockito.logic;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.logic.BidManagerImpl;
import com.tomczyk.bidder.model.Bid;

@RunWith(MockitoJUnitRunner.class)
public class ConstructingBidsViaMockedManagerTest extends BidderBase {

	@Mock
	private BidManagerImpl bidManager;

	/*
	 * We are testing save method in the bidManager
	 * This test is not intended to test persist method of the entitymanager etc.
	 * We are testing only(!) bidManager's 'save' behavior
	 */
	@Test
	public void canABidManagerSaveABid() throws Exception {
		
		Bid newBid = bidFactory.getBidWithProvidedId((long) 101, new BigDecimal("1001.09"));
		
		bidManager.save(newBid);
		
		/*
		 * just checking if save method was invoked, testing of the behavior,
		 * not the state
		 */
		verify(bidManager, times(1)).save(newBid);
	}
			
}
