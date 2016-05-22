package com.tomczyk.bidder.base;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.internal.SessionImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.tomczyk.bidder.factory.BidFactory;
import com.tomczyk.bidder.factory.BidFactorySimpleImpl;
import com.tomczyk.bidder.model.Bid;

/**
 * Base class for other tests
 */
public class BidderBase {

	private static final Logger log = Logger.getLogger(BidderBase.class);

	protected static BidFactory bidFactory;

	/*
	 * yes, it is true that this is not used in every test, but in practice it
	 * is very useful to have it here, rather than instantiate this list, only
	 * in, for example 30 places instead of 100 places
	 * 
	 * this is nothing else like reference bids collection and is very helpful
	 * in more complex tests
	 */
	protected static List<Bid> allBids;

	protected static EntityManagerFactory emf;

	protected static EntityManager em;

	/*
	 * we want to invoke all tests which are in one class using the same
	 * transaction in case of any problems there will be rollback for everything
	 * 
	 * this is why the EntityManager is a static
	 */
	protected static EntityTransaction et;

	protected static IDatabaseConnection dbUnitConnection;
	private static IDataSet bidsDataSet;

	//private static Connection derbyConnection;
	
	@BeforeClass
	public static void setUpPredefinedData() throws SQLException,
			DatabaseUnitException, UnsupportedEncodingException {

		emf = Persistence.createEntityManagerFactory("bidderLocalPU");
		em = emf.createEntityManager();

		dbUnitConnection = new DatabaseConnection(
				((SessionImpl) (em.getDelegate())).connection());

		/*
		 *  altering the 'site' table, because site class has GenerationType.IDENTITY.
		 *  This is a problem in a scenario when we load some predefined data
		 *  and persist some new sites (through the test).
		 *  Invoking a test forces derby to generate id which are the same
		 *  as generated ones thus the error "the statement was aborted - possible duplicated keys..." etc.
		 */
		
		/*derbyConnection = dbUnitConnection.getConnection();
		ij.runScript(derbyConnection,
				BidderBase.class.getResourceAsStream("/sql/bidderSchema.sql"),
				"UTF-8", System.out, "UTF-8");*/
		
		// Loads the data set from a file named students-datasets.xml
		bidsDataSet = new FlatXmlDataSetBuilder().build(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("bids-datasets.xml"));

		bidFactory = new BidFactorySimpleImpl();
		allBids = bidFactory.getBidsAsLinkedList();

	}

	@Before
	public void setUpEntityManager() throws DatabaseUnitException,
			SQLException, UnsupportedEncodingException {

		/*
		 * set a database to a known state before each test the rule we follow
		 * is: tests should not rely on the success of previous ones
		 */

		log.info("Preparing the database");

		/*
		 * CLEAN_INSERT deletes existing data and inserts new ones
		 */
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, bidsDataSet);

		log.info("Starting a new transaction for the test");
		et = em.getTransaction();

	}

	@AfterClass
	public static void tearDownPredefinedData() throws SQLException {

		//derbyConnection.close();
		dbUnitConnection.close();
		
		em.close();
		emf.close();
		
		bidFactory = null;
		allBids = null;
	}

}