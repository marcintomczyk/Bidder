package com.tomczyk.bidder.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.tomczyk.bidder.action.exception.DuplicatedBidException;
import com.tomczyk.bidder.logic.BidManagerImpl;
import com.tomczyk.bidder.model.Bid;
import com.tomczyk.bidder.model.Site;

public class CreateBidAction extends ActionSupport {

	private static final long serialVersionUID = 3630857201207677371L;
	
	private static final Logger log = Logger.getLogger(CreateBidAction.class);
	
	private Map<String, String> data = new LinkedHashMap<String, String>();

	private BidManagerImpl bidManager;

	public CreateBidAction() {
		bidManager = new BidManagerImpl();
	}

	// all struts logic here
	public String execute() {
		
		if(log.isDebugEnabled()) {
			log.debug("executing createBidAction");
			log.debug("json data: " + data);
		}
		
		Bid bid = new Bid();
		/*
		 * conversion from string to numeric values
		 * Validation(to be sure user can only input numeric values) is on the page
		 * 
		 */
		bid.setId(Long.valueOf(data.get("bid_id")));
		
		BigDecimal amount = new BigDecimal(data.get("cost").toString());
		
		bid.setCost(amount);

		if(log.isDebugEnabled()){
			log.debug("urls: " + data.get("urls"));	
		}
		
		
		List<Site> allSites = new ArrayList<Site>();

		/*
		 * extracting urls
		 * TODO: move to separate static class (SiteUtil for example) for later reuse
		 * 
		 * now, we get no benefits in case of moving to a separate class
		 */
		String urls = (String) data.get("urls");
		
		String[] tokens = urls.split(",");
        
		//creating a separate site for every url
		for(String url : tokens) {         
            Site site = new Site();
			site.setUrl(url);
			site.setBid(bid);
			allSites.add(site);
        }
		
		bid.setSites(allSites);
		
		try {
			bidManager.save(bid);
		} catch (DuplicatedBidException dex) {
			log.error("BidderException: " + dex.getMessage());
			addActionError(dex.getMessage());
			return INPUT;
		}

		return SUCCESS;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}