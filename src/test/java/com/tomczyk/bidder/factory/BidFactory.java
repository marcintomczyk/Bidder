package com.tomczyk.bidder.factory;

import java.util.List;

import com.tomczyk.bidder.model.Bid;
import java.math.BigDecimal;

public interface BidFactory {

	/*
	 * Using these method we can create a bid with a required id
	 * which is important in case we need to persist
	 * a new bid with a new id (but we have to know if this id is
	 * not present in db)
	 * 
	 * TODO: create a some kind of unique id generator
	 * 
	 */
	public Bid getBidWithProvidedId(Long bidId, BigDecimal bidCost);
	public List<Bid> getBidsAsLinkedList();
	
}
