package es.deusto.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  

import java.rmi.RemoteException;

import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;  
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;  
import org.mockito.runners.MockitoJUnitRunner; 

import org.junit.Before;
import org.junit.Test;
//import org.junit.Ignore;




import es.deusto.server.dao.*;
import es.deusto.server.data.*;
import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;

import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RunWith(MockitoJUnitRunner.class)  
public class DAOMockTest {
	
	DB db;
	final static  Logger logger = LoggerFactory.getLogger(DAOMockTest.class);
	static int iteration = 0;
	
	@Mock
	IDAO dao;
		
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOMockTest.class);
	}

	
	@Before
	public void setUp() throws Exception {
		logger.info("Entering setUp: {}", iteration++);
		db = new DB(dao);
		logger.info("Leaving setUp");

	}

	@Test
	//@Ignore
	public void testRegisterUserCorrectly() {
		logger.info("Starting testRegisterUserCorrectly() ");
		// Stubbing - return a given value when a specific method is called
		when( dao.retrieveClient("cortazar") ).thenReturn( null );
		Client cl = new Client ("cortazar", "cortazar",false);
		db.registerClient(cl);
		
		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<Client> userCaptor = ArgumentCaptor.forClass( Client.class );
		
		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeClient(userCaptor.capture());
		Client newClient = userCaptor.getValue();
		logger.info("Registering mock new Client: " + newClient.getEmail());
	
		assertEquals( "cortazar", newClient.getEmail());
		logger.debug("Finishing testRegisterUserCorrectly() ");
	}
	
	@Test
	public void testRegisterUserAlreadyExists() {
		Client cl = new Client("cortazar","cortazar",false);
		Client cl1 = new Client("cortazar","dipina",false);
		when( dao.retrieveClient("cortazar") ).thenReturn(cl);
		// When the user exist, we update the password
		db.registerClient(cl1);
		
		
		ArgumentCaptor<Client> userCaptor = ArgumentCaptor.forClass( Client.class );
		verify (dao).updateClient(userCaptor.capture());
		Client newClient = userCaptor.getValue();
		logger.info("Changing password of mock Client: " + newClient.getPassword());
		assertEquals( "dipina", newClient.getPassword());
		
	}
	@Test
	public void testAddBookValid() throws RemoteException {
		// Setting up the test data
		
		Car c =new Car(2,"maria","pabloaut",0.2);
		
		//Stubbing
		when( dao.retrieveCar (2) ).thenReturn(null);
		
		//Calling the method under test
		
		db.addCarToDb(c);
		
		// Verifying the outcome
		ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass( Car.class );
		verify (dao).storeCar(carCaptor.capture());
		Car newCar = carCaptor.getValue();
		
		assertEquals( c.toString(), newCar.toString());
		
	}
	@Test(expected=RemoteException.class)
	public void testAddBookInvalidRemote() throws RemoteException {
		// Setting up the test data
		
		Car c =null;
		
		//Stubbing
		//when( dao.retrieveBook (a) ).thenReturn(null);
		
		logger.error("Invalid book remote, testing exception");
		IRemote remote = new Remote();
		
		//Calling the method under test
		remote.addCar(c);
	}

	/**
	@Test(expected=AssertionError.class)
	public void testAddBookInvalidDB() throws RemoteException {
		// Setting up the test data
		
		Book b =null;
		
		//Stubbing
		//when( dao.retrieveBook (a) ).thenReturn(null);
		
		logger.info("Invalid book remote, testing exception");
		
		
		//Calling the method under test
		db.addBookToDb(b);
	}
**/

}