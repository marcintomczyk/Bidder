package com.tomczyk.bidder.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tomczyk.bidder.factory.ConstructingBidsViaFactoryTest;
import com.tomczyk.bidder.mockito.logic.ConstructingBidsViaMockedManagerTest;
import com.tomczyk.bidder.mockito.logic.FetchingBidsViaMockedManagerTest;
import com.tomczyk.bidder.mockito.persistence.ConstructingBidsViaMockedEntityManagerTest;
import com.tomczyk.bidder.realDb.logic.ConstructingBidsViaRealManagerTest;
import com.tomczyk.bidder.realDb.model.ConstructingBidsViaRealEntityManagerExceptionsTest;
import com.tomczyk.bidder.realDb.model.ConstructingBidsViaRealEntityManagerTest;

@RunWith(Suite.class)
@SuiteClasses({ConstructingBidsViaFactoryTest.class,
		ConstructingBidsViaMockedManagerTest.class,
		FetchingBidsViaMockedManagerTest.class,
		ConstructingBidsViaMockedEntityManagerTest.class,
		ConstructingBidsViaRealEntityManagerTest.class,
		ConstructingBidsViaRealEntityManagerExceptionsTest.class,
		ConstructingBidsViaRealManagerTest.class
		})
public class BidderAllTests {

}
