package com.tomczyk.bidder.mockito.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tomczyk.bidder.base.BidderBase;
import com.tomczyk.bidder.model.Bid;

@RunWith(MockitoJUnitRunner.class)
public class ConstructingBidsViaMockedEntityManagerTest extends BidderBase {

	@Mock
	private EntityManager em;

	@Mock
	private EntityTransaction et;
	
	@Mock
	private Query getAllBidsQuery;
	
	private String queryString = "SELECT b FROM Bid b";
	
	@Test
	public void canABidBePersisted() throws Exception {
		
		Bid newBid = bidFactory.getBidWithProvidedId((long) 101, new BigDecimal("1001.49"));

		em.persist(newBid);
		verify(em, times(1)).persist(newBid);
	}
	
	@Test
	public void bidWithDuplicateKeyCannotBePersisted() throws Exception {
		
		Bid newBid = bidFactory.getBidWithProvidedId((long) 101, new BigDecimal("1001.49"));
		
		when(em.getTransaction()).thenReturn(et);
			
		doThrow(new PersistenceException()).when(et).commit();
		
		try {
			em.persist(newBid);
			et.commit();
			} catch(PersistenceException pex) {
			  //you can assert the exception here - if it makes sense
				
				assertTrue(pex instanceof PersistenceException);
			} 
		
		verify(em, times(1)).persist(newBid);
		verify(et, times(1)).commit();
	}
	

	@Test
	public void canBidsBeFetched() throws Exception {

		/*
		 * creating the 'environment' (we simulate behaviors)
		 */
		when(em.createQuery(queryString)).thenReturn(getAllBidsQuery);

		when(getAllBidsQuery.getResultList()).thenReturn(allBids);

		/*
		 * calling the main method we want to test
		 */
		// List result = service.findAll();
		@SuppressWarnings("rawtypes")
		List result = (List) em.createQuery(queryString).getResultList();

		assertEquals(allBids, result);
		assertEquals(allBids.size(), result.size());
		verify(em).createQuery(queryString);

		verify(em, times(1)).createQuery(queryString);
	}

}
