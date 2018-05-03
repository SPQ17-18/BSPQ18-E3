package es.deusto.client;

import java.rmi.RemoteException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.gui.LogIn;
import es.deusto.server.data.Car;
import es.deusto.server.remote.IRemote;

	public class ClientServer {

		final static  Logger logger = LoggerFactory.getLogger(Client.class);
		
		private static String[] logInMenu = {"Log In", "Sign Up"};
		private static String[] showCarsMenu = {"Search", "Show all the cars", "Log Out"};
		private static String[] searchMenu = {"ID", "Brand", "Go back"};
		private static String[] carSelectionMenu = {"Buy", "Write a review","Go back"};
		private static String[] reviewMenu = {"Rank", "Comment", "Go back"};
		private static String emailClientServer;
		private static String carsSelectionBrand;
		
		public static void displayMenu(String[] options){
			logger.info("Menu");
			logger.info("----");
			for(int i = 0; i<options.length; i++){
				logger.info((i+1) + ".- " + options[i]);
			}
			logger.info("Insert a number to select an action:");
			logger.info("(To close the application, write 'exit')");
		}
		
		public static void displaySubMenu(String[] options){
			logger.info("Menu");
			logger.info("----");
			for(int i = 0; i<options.length; i++){
				logger.info((i+1) + ".- " + options[i]);
			}
			logger.info("Insert a number to select an action:");
		}
		
		public static void mainMenu(IRemote server){
			String input= "";
			
			 
			do{
				displayMenu(logInMenu);
				input = System.console().readLine();
				switch(input){
				case("1"):
					//Log in
					 logIn(server);
					//if(log != true){
						//log = logIn(server);
						//menuShowcars(server);
					
					menuShowcars(server);
					break;
				case("2"):
					//Sing Up = Create a new Client
					signUp(server);
					break;
				case("exit"):
					break;
				default:
					logger.info("Not valid");
					break;
				}
			}while(!input.equals("exit"));

		}
		
		public static void menuShowcars(IRemote server){
			displaySubMenu(showCarsMenu);
			String input = "";
			input = System.console().readLine();
			switch(input){
			case("1"):
				//Search
				menuSearch(server);
				break;
			case("2"):
				showcars(server);
				//Select one
				menuCar(server);
				break;
			case("3"):
				//Log Out
				mainMenu(server);
				break;
			default:
				logger.info("Not valid");
				break;
			}
		}
		
		public static void menuSearch(IRemote server){
			String input = "";
			displaySubMenu(searchMenu);
			input = System.console().readLine();
			switch (input) {
			case("1"):
				//ID
				searchID(server);
				menuShowcars(server);
				break;
			case("2"):
				//Brand
				searchBrand(server);
				menuShowcars(server);
				break;
			case("3"):
				//Go back
				menuShowcars(server);
				break;
			default:
				logger.info("Not valid");
				break;
			}
		}
		
		public static void menuCar(IRemote server){
			String input = "";
			displaySubMenu(carSelectionMenu);
			input = System.console().readLine();
			switch(input){
			case("1"):
				//Buy
				rentCar(server);
				menuShowcars(server);
				break;
			case("2"):
				//Write a review
				menuReviews(server);
				break;
			case("3"):
				//Go back
				menuShowcars(server);
				break;
			default:
				logger.info("Not valid");
				break;
			}
		}
		
		public static void menuReviews(IRemote server){
			String input = "";
			displaySubMenu(reviewMenu);
			input = System.console().readLine();
			switch (input) {
			case("1"):
				//Rank
				//TODO
				break;
			case("2"):
				//Comment
				//TODO
				break;
			case("3"):
				//Go back
				menuCar(server);
				break;
			default:
				logger.info("Not valid");
				break;
			}
		}
		
		
		public static void logIn(IRemote server){
			
			String password;
			
			String input= "";
					
			logger.info("Email:");
			input = System.console().readLine();
			emailClientServer = input;
			
			logger.info("Password:");
			input = System.console().readLine();
			password = input;		
			
			try {
				server.registerClient(emailClientServer, password, false);
				
			} catch (RemoteException e) {
				logger.info(e.getMessage());
			}
			
		}
		
		public static void signUp(IRemote server){
			String email;
			String password1;
			String password2;
			
			String input= "";
					
			logger.info("Email:");
			input = System.console().readLine();
			email = input;
			
			do{
				logger.info("Password:");
				input = System.console().readLine();
				password1 = input;		
				
				logger.info("Repeat password:");
				input = System.console().readLine();
				password2 = input;	
				
				if(!password1.equals(password2)){
					logger.info("Incorrect. Try again!");
				}
			}while(!password1.equals(password2));
			try {
				server.registerClient(email, password1, false);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			logger.info("You have been register. Now Log In.");
		}
		
		public static void searchID(IRemote server){
			int input;
			Car c = null;
			logger.info("ID:");
			input = Integer.parseInt(System.console().readLine());
			
			try {
				c = server.getCarByMat(input);
				logger.info(c.toString());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
		
		public static void searchBrand(IRemote server){
			String input= "";
			Car c = null;
			logger.info("Brand:");
			input = System.console().readLine();
			
			try {
				c = server.getCarByBrand(input);
				logger.info(c.toString());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		public static void showcars(IRemote server){
			List<Car> cars = null;
			int input;
			
			try {
				cars = server.showCarsInStore();
			} catch (RemoteException e) {
				logger.info(e.getMessage());
			}

			for(int i = 0; i<cars.size(); i++){
				Car b = cars.get(i);
				logger.info((i+1) + ".-" + b.toString());
			}
			logger.info("Choose a Car to visualize: ");
			input =  Integer.parseInt(System.console().readLine());
			showCar(server, input - 1);
		}
		
		public static void showCar(IRemote server, int numCar){
			List<Car> cars = null;
			
			try {
				cars = server.showCarsInStore();
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			Car b = cars.get(numCar);
			carsSelectionBrand = b.getBrand();
			logger.info(b.toString());
		}
		
		public static void rentCar(IRemote server){
			boolean rentOk = false;
			
			try {
				rentOk = server.rentCar(emailClientServer, carsSelectionBrand);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			logger.info(""+rentOk);
			
			if(rentOk == true){
				logger.info("You have rent "+carsSelectionBrand);
			}else{
				logger.info("Error.Try later!");
			}
		}
		
		public static void main(String[] args) {
			if (args.length != 3) {
				logger.info("Use: java [policy] [codebase] ClientServer.ClientServer [host] [port] [server]");
				System.exit(0);
			}
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			LogIn logIn;
			try{
				String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
				IRemote server = (IRemote) java.rmi.Naming.lookup(name);
				
				logIn = new LogIn();
			}catch (Exception e) {
				logger.error("RMI Example exception: " + e.getMessage());
				e.printStackTrace();
			}		
		}
	}