package es.deusto.test;



import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.dao.*;
import es.deusto.server.data.*;
import junit.framework.JUnit4TestAdapter;


public class ContiPerfTest {
	IDB db =new DB();
	
	int count=50;
	int count2=50;
	int count3=50;
	
	String a ="" + count ;
	String b ="" + count +1 ;
	String c ="" + count +2;
	
	Random rand = new Random();
	final static Logger logger = LoggerFactory.getLogger(ContiPerfTest.class);
	
	@Rule public  ContiPerfRule rule = new ContiPerfRule();
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ContiPerfTest.class);
	}
	
	
	@Test
	@PerfTest(invocations = 50)
	public void storeCar() {
		logger.info("ContiPerf storeCar");
		
		Car b =new Car(154,"Blue", "Citroen", "c1", "deportivo", "gps",10.3);
		
		db.addCarToDb(b);
		count++;
	}

	
	@Test
	@PerfTest(invocations = 10)
	//@Required(average = 50, throughput =10, max =100)
	public void registerNewClientTest() {
		
		String idEmail ="" + count3 ;
			logger.info("Test 1 - Register new client");
			db.registerClient(idEmail, "ipina",false);
			logger.info("Client email" + idEmail);
			
			count3++;
		
		
		/*
		 * Very simple test, inserting a valid new client
		 */
	
		assertTrue( true );
	}
	@Test
	@PerfTest(duration = 10000)
	@Required(average = 100, throughput =10)
	public void storeCarDuration() {
		logger.info("ContiPerf  storeCarDuration");
		
		Car b =new Car(155,"black", "Citroen", "c1", "turismo", "no accesories",10.3);
		
		db.addCarToDb(b);
		count++;
	}
	
	@Test
	@PerfTest(threads = 40, duration = 100)
	public void showCarThreads() {
		logger.info("ContiPerf showCarThreads");
		
		
		
		db.showCarBymat(50);
		
	}
	
	
	
	
	@Test
	@PerfTest(threads = 10, duration = 100)
	public void showCarThreads2() {
		logger.info("ContiPerf store Car");
		
		
		
		db.showCarBymat(123);
		count2++;
	}

	

}


