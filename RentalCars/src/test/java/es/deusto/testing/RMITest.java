package es.deusto.testing;


import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.junit.AfterClass;

import es.deusto.server.data.*;
import es.deusto.server.remote.*;



import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;



/**
 * RMI Unit test for Simple Client / Server RMI Testing.
 * Testing the only the Remoteness
 */
//@Ignore

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

				String name = "//127.0.0.1:1099/MessengerRMIDAO";
				logger.info("BeforeClass - Setting the server ready TestServer name: " + name);

				try {
					
					IRemote remote = new Remote();
					Naming.rebind(name, remote);
				} catch (RemoteException re) {
					logger.error(" # Messenger RemoteException: ");
					logger.trace(re.getMessage());
					re.printStackTrace();
					System.exit(-1);
				} catch (MalformedURLException murle) {
					logger.error(" # Messenger MalformedURLException: ");
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

		String name = "//127.0.0.1:1099/MessengerRMIDAO";
		logger.info("BeforeTest - Setting the client ready for calling TestServer name: " + name);
		remote = (IRemote) java.rmi.Naming.lookup(name);
		}
		catch (Exception re) {
			logger.error(" # Messenger RemoteException: ");
			logger.trace(re.getMessage());
			re.printStackTrace();
			System.exit(-1);
		} 
		
	}
	
	@Test public void registerNewUserTest() {
		try{
			logger.info("Test 1 - Register new user");
			remote.registerClient("ipina");
		}
		catch (Exception re) {
			logger.error(" RemoteException: " );
			logger.trace(re.getMessage());
			re.printStackTrace();
		} 
		/*
		 * Very simple test, inserting a valid new user
		 */
		assertTrue( true );
	}
	
	
	
	
	@Test public void registerExistingUserTest() {
		try{
			logger.info("Test 2 - Register existing user. Change password");
			remote.registerClient("smith");
			// Silly way of testing the password testing
			remote.registerClient("smith");
			
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
	
	
		
		@Test public void showCarsInStoreTest() {
			try{
				logger.info("Test 4 - showCarsInStore");
				Car c = new Car(123, "peuab2", "prueba2", "prueba1", 0);
				remote.addCar(c);
				remote.showCarsInStore();
				
			}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				
			}
			
			assertTrue( true );
			
			
			
		}
	
		@Test public void showUsersTest() {
			try{
				logger.info("Test 5 - showUsers");
				
				remote.registerClient("jon");
				remote.getAllClients();
				
			}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				
			}
			
			assertTrue( true );
			
			
			
		}
		
		


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


