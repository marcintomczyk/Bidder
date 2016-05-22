package com.tomczyk.bidder.action.exception;

/**
 * Returned when there is a PersistenceException
 * (the user provided the id which exists in the database)
 *
 */
public class DuplicatedBidException extends BidderException {

	private static final long serialVersionUID = -9108103269577948117L;

	public DuplicatedBidException(String message) {
        super(message);
    }
	
}
