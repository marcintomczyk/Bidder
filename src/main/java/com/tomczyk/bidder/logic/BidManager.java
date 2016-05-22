package com.tomczyk.bidder.logic;

import java.util.List;

import com.tomczyk.bidder.action.exception.DuplicatedBidException;
import com.tomczyk.bidder.model.Bid;

public interface BidManager {

	public List<Bid> findAll();

    public void save(Bid bid) throws DuplicatedBidException;
    
    public Bid getById(Long id);
	
}
