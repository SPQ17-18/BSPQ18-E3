package es.deusto.server;

import java.rmi.Naming;

import es.deusto.server.remote.IMessenger;
import es.deusto.server.remote.Messenger;



import java.rmi.Naming;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DB;
import db.IDB;
import server.data.*;
import server.remote.*;


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

	

			Rent r1 = new Rent(01);
			Rent r2 = new Rent(02);
			Rent r3 = new Rent(03);
			Rent r4 = new Rent(04);
			Rent r5 = new Rent(05);
			
			Car c1 =new Car(1,"Black","AMG GT-R","deportivo","", "Mercedes");
			Car c2 =new Car(2,"White","AMG GT-C","deportivo","", "Mercedes");
			Car c3 =new Car(6,"Red","AMG GT-S","deportivo","", "Mercedes");
			Car c4 =new Car(3,"Blue","Leon SC","compacto","", "SEAT");
			Car c5= new Car(4,"Red","Ateka","crossover","", "SEAT");
			Car c6= new Car(5,"White","Transit","comercial","", "FORD");
			Car c7 =new Car(6,"Red","Mustang","turismo","", "FORD");

			IDB db = new DB();

			db.addCarToDb(c4);
			db.addCarToDb(c1);
			db.addCarToDb(c2);
			db.addCarToDb(c3);

			db.registerClient("gorka", "qwerty", false);
			db.registerClient("nacho", "qwerty", false);
			db.registerClient("javier", "qwerty", false);
			db.registerClient("jon", "qwerty", false);
			db.registerClient("janire", "qwerty", true);
	
			Client a1 =db.showClient("gorka");						
			Client a2 =db.showClient("nacho");
			Client a3 =db.showClient("javier");
			Client a4 =db.showClient("jon");
			Client a5=db.showClient("janire");
			
			
			db.addRent(c1, r1, a1);	
			db.addRent(c2, r2, a2);
			
			db.addRent(c3, r3, a3);
			db.addRent(c3, r4, a4);
			db.addRent(c2, r5, a5);
			
			
			
			
			
			
	
			db.buyCar("jon", "Car4");
			double CarAverage=db.averageRatingByCar(c2.getBrand());
			
			logger.info("Car avergae"+CarAverage);
			
			double userAverage=db.averageRatingByClient(a2.getEmail());

			logger.info("client avergae"+userAverage);

		
			

		
			
			logger.info("[S] Server '" + name + "' active and waiting...");
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			@SuppressWarnings("unused")
			String line  = stdin.readLine();






		} catch (Exception e) {
			logger.error("[S] Server exception: ");
			logger.trace(e.getMessage());
			e.printStackTrace();
		}

	}
}


