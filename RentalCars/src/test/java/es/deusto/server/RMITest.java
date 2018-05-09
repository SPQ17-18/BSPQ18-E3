package es.deusto.server;


import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.AfterClass;
import org.junit.After;
//import org.junit.Ignore;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

import es.deusto.server.remote.IRemote;

import es.deusto.server.remote.Remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;



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
					System.out.println("BeforeClass: RMI registry ready.");
				} catch (Exception e) {
					System.out.println("Exception starting RMI registry:");
					e.printStackTrace();
				}	
			}
		}
		
		rmiRegistryThread = new Thread(new RMIRegistryRunnable());
		rmiRegistryThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		class RMIServerRunnable implements Runnable {

			public void run() {
				System.out.println("This is a test to check how mvn test executes this test without external interaction; JVM properties by program");
				System.out.println("**************: " + cwd);
				System.setProperty("java.rmi.server.codebase", "file:" + cwd);
				System.setProperty("java.security.policy", "target\\test-classes\\security\\java.policy");

				if (System.getSecurityManager() == null) {
					System.setSecurityManager(new SecurityManager());
				}

				String name = "//127.0.0.1:1099/MessengerRMIDAO";
				System.out.println("BeforeClass - Setting the server ready TestServer name: " + name);

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
		System.out.println("BeforeTest - Setting the client ready for calling TestServer name: " + name);
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
			System.out.println("Test 1 - Register new user");
			remote.registerClient("user1prueba", "user1prueba", false);
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
			System.out.println("Test 2 - Register existing user. Change password");
			remote.registerClient("smith", "smith",false);
			// Silly way of testing the password testing
			remote.registerClient("smith", "doe",false);
			
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
	
	@Test public void carTestValidation() {
		logger.info("Test 3 - Game Test ");
		Car c = new Car(1,"prueba","pruabe"," ");
		Car carTest = null;
		try{	
			carTest = remote.carTest();		
		} catch (RemoteException e){
			logger.error(" # RemoteException: " + e.getMessage());
			logger.trace(e.getMessage());
			e.printStackTrace();	
		}
	
		assertEquals(c.toString(), carTest.toString());
		
	}
	
	@Test public void showCarsInStoreTest() {
		try{
			logger.info("Test 4 - showCarsInStore");
			Car c1 =new Car(2,"adf","cochee","coche1");
			remote.addCar(c1);
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
			logger.info("Test 5 - showclients");
			
			remote.registerClient("HL2","j",false);
			remote.getAllClients();
			
		}
		catch (Exception re){
			logger.error(" RemoteException: " );
			logger.trace(re.getMessage());
			re.printStackTrace();
			
		}
		
		assertTrue( true );

	}
	
	
	@Test public void getThings(){
		boolean a = true;
		
		try {
			logger.info("Test 6 - showClients");
			
			Client cl1;
			Rent r1 = new Rent( 01);
			Car c1 =new Car(123,"Mercedes","AMG GT-R","deportivo");
			Car c2=null;
			remote.registerClient("Javier","javier",false);
			cl1=remote.getClient("Javier");
			
			remote.addCar(c1);
			
			
			
			c2 =remote.getCarByMat(c1.getMat());
			
			remote.addRent(c2, r1, cl1);
			remote.getRent(r1.getid_rent());
			remote.getAllRents();
			remote.rentCar(cl1.getEmail(), c2.getBrand());
			
			
		}
			catch (Exception re){
				logger.error(" RemoteException: " );
				logger.trace(re.getMessage());
				re.printStackTrace();
				a=false;
				
		}
		assertTrue(a);
	}
	@Test(expected=RemoteException.class)
	public void showUserFailTest() throws RemoteException{
			
			logger.info("Test 7 - showClientsFail");
			
			
			remote.getClient(null);
			
			
	
		
				
	}
	
	
	
	

	@AfterClass static public void tearDown() {
		try	{
			rmiServerThread.join();
			rmiRegistryThread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		

	} 
}
