package es.deusto.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Client;
import es.deusto.server.dao.DAO;
import es.deusto.server.dao.DB;
import es.deusto.server.dao.IDB;
import es.deusto.server.data.Car;
import es.deusto.server.data.Rent;
import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;



public class Server{

	final static  Logger logger = LoggerFactory.getLogger(Server.class);
	public static void main(String[] args) {

		
		
		if (args.length != 3) {
			logger.info("[S] How to invoke: java [policy] [codebase] Server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IRemote objServer = new Remote();
			Naming.rebind(name, objServer);

	

			Rent r1 = new Rent("Rent 1", 6.6);
			Rent r2 = new Rent("Rent 2",8.6);
			Rent r3 = new Rent("Rent 3",7.6);
			Rent r4 = new Rent("Rent 4",3.6);
			Rent r5 = new Rent("Rent 5",5.6);
			
			Car c1=new Car(123, "Red", "Brand1", "Model1", "Type1", "Accesories1", 45.62);
			Car c2=new Car(124, "Blue", "Brand2", "Model2", "Type2", "Accesories2", 75.42);
			Car c3=new Car(125, "White", "Brand1", "Model2", "Type3", "Accesories3", 25.62);
			Car c4=new Car(126, "Blue", "Brand3", "Model1", "Type2", "Accesories3", 96.20);
			Car c5=new Car(127, "Black", "Brand2", "Model3", "Type3", "Accesories4", 100.62);

			IDB db = new DB();
		
			db.addCarToDb(c1);
			db.addCarToDb(c2);
			db.addCarToDb(c3);
			db.addCarToDb(c4);
			db.addCarToDb(c5);

			db.registerClient("janire", "spq", false);
			db.registerClient("spq", "spq", false);
			db.registerClient("jon", "spq", false);
			db.registerClient("gorka", "spq", false);
			db.registerClient("nacho", "spq", true);
			db.registerClient("javier", "spq", true);
			db.registerClient("admin", "spq", true);
			
			Client a1 =db.showClient("jon");						
			Client a2 =db.showClient("janire");
			Client a3 =db.showClient("gorka");
			Client a4 =db.showClient("nacho");
			Client a5=db.showClient("javier");
			Client ad=db.showClient("admin");
			
			
			
			db.addRent(c1, r1, a1);	
			db.addRent(c2, r2, a2);
			db.addRent(c5, r5, ad);
			
			db.addRent(c3, r3, a2);
			db.addRent(c3, r4, a3);
			db.addRent(c2, r5, a4);
			
			db.registerRent(r1);
			
			
			
	
			db.rentCar("jon", "Brand1");
			double carAverage=db.averageRatingByCar(c2.getBrand());
			
			logger.info("car average: "+carAverage);
			
			double clientAverage=db.averageRatingByClient(a2.getEmail());

			logger.info("client average: "+clientAverage);

		
			
		
			
			logger.info("[S] Server '" + name + "' active and waiting...");
			InputStreamReader inputStreamReader = new InputStreamReader ( System.in );
			BufferedReader stdin = new BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();


		} catch (Exception e) {
			logger.error("[S] Server exception: ");
			logger.trace(e.getMessage());
			e.printStackTrace();
		}

	}
}
