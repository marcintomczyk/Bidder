package com.tomczyk.bidder.action.exception;

/*
 * Base class for the application
 * 
 * I do not want it to be instantiated, we need to define more detailed exception
 * rather than general one.
 */
public abstract class BidderException extends Exception {

	private static final long serialVersionUID = -2047730285392726766L;

	public BidderException(String message) {
	        super(message);
	    }
	
}
