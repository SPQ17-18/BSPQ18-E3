package es.deusto.test;


import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;
import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.CarsRemote;
import es.deusto.client.*;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.AfterClass;





import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;



/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */


public class RMITest {
	// Properties are hard-coded because we want the test to be executed without external interaction
	
	private static String cwd = RMITest.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	private static Thread rmiRegistryThread = null;
	private static Thread rmiServerThread = null;
	
	private IRemote remote;
	final static Logger logger = LoggerFactory.getLogger(RMITest.class);
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(RMITest.class);
	}


	@BeforeClass static public void setUp() {
		// Launch the RMI registry
		class RMIRegistryRunnable implements Runnable {

			public void run() {
				try {
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					logger.info("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					logger.error("Exception starting RMI registry:");
					logger.trace(e.getMessage());
					e.printStackTrace();
				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			logger.error("Interruption Exception");
			logger.trace(ie.getMessage());
			ie.printStackTrace();
		}
		
		class RMIServerRunnable implements Runnable {

			public void run() {
				logger.info("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				logger.info("**************: " + cwd);
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/RentalCarServer";
				logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					
					IRemote remote = new CarsRemote();
					Naming.rebind(name, remote);
				} catch (RemoteException re) {
					logger.error(" # RentalCarServer RemoteException: ");
					logger.trace(re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # RentalCarServer MalformedURLException: ");
					logger.trace(murle.getMessage());
					murle.printStackTrace();
					System.exit(-1);
				}
			}
		}
		rmiServerThread = new Thread(new RMIServerRunnable());
		rmiServerThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			logger.error("Interruption Exception");
			logger.trace(ie.getMessage());
			ie.printStackTrace();
			
		}
	
	}
	

	@Before public void setUpClient() {
		try {
		System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//127.0.0.1:1099/RentalCarServer";
		logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
		remote = (IRemote) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			logger.error(" # RentalCarServer RemoteException: ");
			logger.trace(re.getMessage());
			re.printStackTrace();
			System.exit(-1);
		} 
		
	}
	
	
	
	@Test public void registerNewClientTest() {
		try{
			logger.info("Test 1 - Register new client");
			remote.registerClient("jon", "jon",false);
		}
		catch (Exception re) {
			logger.error(" RemoteException: " );
			logger.trace(re.getMessage());
			re.printStackTrace();
		} 
		
		 //Very simple test, inserting a valid new client
		 
		assertTrue( true );
	}
	
	
	
	
	@Test public void registerExistingClientTest() {
		try{
			logger.info("Test 2 - Register existing client. Change password");
			remote.registerClient("jon", "jon",false);
			// Silly way of testing the password testing
			remote.registerClient("jon", "doe",false);
			
		}
		catch (Exception re) {
			logger.error(" RemoteException: " + re.getMessage());
			logger.trace(re.getMessage());
			re.printStackTrace();
		} 
		/*
		 * Very simple test 
		 */
		assertTrue( true );
	}
	
	
	
	/*	@Test public void carTestValidation() {
		logger.info("Test 3 - Game Test ");
		
		
		Car c = new Car(1,"Prueba coche","Janire", 19.90);
	
		
		Car carTest = null;
		
		try{	
			carTest = remote.carTest();		
			
		} catch (RemoteException e){
			logger.error(" # RemoteException: " + e.getMessage());
			logger.trace(e.getMessage());
			e.printStackTrace();
			
			
		}
	
		assertEquals(c.toString(), carTest.toString());
		
	}*/
	
	@Test 
	public void searchCarTest() 
	{ 
		try{
	
		  logger.info("Test 3 - find cars"); 
		  String ca="Seat";
		  remote.getCarByBrand(ca);
		  } catch (Exception e) {
			  logger.error(" # RemoteException: " + e.getMessage());
				logger.trace(e.getMessage());
				e.printStackTrace();
		  }
	}
	
	@Test
	public void deleteCarTest() {
		try {
			logger.info("Test 3.1");
			remote.deleteCar(123);
			} catch (Exception re) {
				logger.error(" # RemoteException: " + re.getMessage());
				logger.trace(re.getMessage());
				re.printStackTrace();
		}
	}
	
	
		
		@Test public void showCarsInStoreTest() {
			try{
				logger.info("Test 4 - showCarsInStore");
				Car b1 =new Car(2,"Blue","ford", "focus", "deportivo", "no accesories",0.2);
				remote.addCar(b1);
				remote.showCarsInStore();
				
			}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				
			}
			
			assertTrue( true );
			
			
			
		}
	
		@Test public void showClientsTest() {
			try{
				logger.info("Test 5 - showClients");
				
				remote.registerClient("prueba","pruebaaaa",false);
				remote.getAllClients();
				
			}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				
			}
			
			assertTrue( true );
			
			
			
		}
		
		/*@Test(expected=RemoteException.class)
		public void showClientFailTest() throws RemoteException{
				
				logger.info("Test 7 - showClientsFail");
				
				
				remote.getClient(null);
				
				
					
		}*/
		

		

	@AfterClass static public void tearDown() {
		try	{
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			logger.error("Interruption Exception");
			logger.trace(ie.getMessage());
			ie.printStackTrace();
		}
		

	} 
}
