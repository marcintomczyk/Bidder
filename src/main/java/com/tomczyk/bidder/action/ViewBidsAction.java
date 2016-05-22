package com.tomczyk.bidder.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.tomczyk.bidder.logic.BidManager;
import com.tomczyk.bidder.logic.BidManagerImpl;
import com.tomczyk.bidder.model.Bid;

/**
 * Class is responsible for fetching bids from database
 */
public class ViewBidsAction extends ActionSupport {

	private static final long serialVersionUID = 1679613738579649653L;

	private static final Logger log = Logger.getLogger(BidManagerImpl.class);
	
	private BidManager bidManager;
	
	private List<Bid> allBids;

	public ViewBidsAction() {
		bidManager = new BidManagerImpl();
	}
	
	public String execute() {
		
		if(log.isDebugEnabled()){
			log.debug("executing viewBidsAction");
			log.debug("getting data from db...");	
		}
		
		allBids = bidManager.findAll();

		return SUCCESS;
	}

	public List<Bid> getAllBids() {
		return allBids;
	}

	public void setAllBids(List<Bid> allBids) {
		this.allBids = allBids;
	}
	
}
