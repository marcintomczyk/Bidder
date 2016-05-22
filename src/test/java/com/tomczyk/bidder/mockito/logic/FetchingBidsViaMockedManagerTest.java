package com.tomczyk.bidder.mockito.logic;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.logic.BidManagerImpl;

@RunWith(MockitoJUnitRunner.class)
public class FetchingBidsViaMockedManagerTest extends BidderBase {

	@Mock
	private BidManagerImpl bidManager;
	
	@Test
	public void canABidManagerFetchBids() throws Exception {
		
		when(bidManager.findAll()).thenReturn(allBids);
		
		/*
		 * remember: the list 'allBids' was created via factory
		 * and by default it's size is 2
		 */
		
		bidManager.findAll();
		
		assertEquals(2, allBids.size());
		
		verify(bidManager, times(1)).findAll();
	}
	
}
