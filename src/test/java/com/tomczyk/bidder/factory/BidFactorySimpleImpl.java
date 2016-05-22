package com.tomczyk.bidder.factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.tomczyk.bidder.model.Bid;
import com.tomczyk.bidder.model.Site;

/**
 * Creates some predefined data used in the tests
 */
public class BidFactorySimpleImpl implements BidFactory {

	/**
	 * returns Bid with 2 sample sites
	 */
	@Override
	public Bid getBidWithProvidedId(Long bidId, BigDecimal bidCost) {
		
		Bid bid = new Bid();
		bid.setId(bidId);
		bid.setCost(bidCost);
		
		Site onet_site = new Site();
		onet_site.setUrl("www.onet.pl");
		onet_site.setBid(bid);
		
		
		Site wp_site = new Site();
		wp_site.setUrl("www.wp.pl");
		wp_site.setBid(bid);
		
		List<Site> allSites = new ArrayList<Site>();
		allSites.add(onet_site);
		allSites.add(wp_site);
		
		bid.setSites(allSites);
		
		return bid;
		
		}

	@Override
	public List<Bid> getBidsAsLinkedList() {
		
		List<Bid> allBids = new LinkedList<Bid>();

		Bid bid1 = new Bid();
		bid1.setCost(new BigDecimal("1.19"));

		Bid bid2 = new Bid();
		bid2.setCost(new BigDecimal("29.99"));

		allBids.add(bid1);
		allBids.add(bid2);
		
		return allBids;
	}

}
