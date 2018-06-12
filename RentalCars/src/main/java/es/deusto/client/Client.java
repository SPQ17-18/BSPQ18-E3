package es.deusto.client;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.gui.LogIn;
import es.deusto.server.data.Car;
import es.deusto.server.remote.IRemote;



	public class Client {

		final static  Logger logger = LoggerFactory.getLogger(Client.class);
		
		private static String[] logInMenu = {"Log In", "Sign Up"};
		private static String[] showBooksMenu = {"Search", "Show all the books", "Log Out"};
		private static String[] searchMenu = {"ISBN", "Title", "Go back"};
		private static String[] bookSelectionMenu = {"Buy", "Write a review","Go back"};
		private static String[] reviewMenu = {"Rank", "Comment", "Go back"};
		private static String emailUser;
		private static String bookSelectionTitle;
		
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
			//User user = null;
			 
			do{
				displayMenu(logInMenu);
				input = System.console().readLine();
				switch(input){
				case("1"):
					//Log in
					 logIn(server);
					//if(log != true){
						//log = logIn(server);
						//menuShowBooks(server);
					
					menuShowBooks(server);
					break;
				case("2"):
					//Sing Up = Create a new user
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
		
		public static void menuShowBooks(IRemote server){
			displaySubMenu(showBooksMenu);
			String input = "";
			input = System.console().readLine();
			switch(input){
			case("1"):
				//Search
				menuSearch(server);
				break;
			case("2"):
				showBooks(server);
				//Select one
				menuBook(server);
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
				//ISBN
				searchISBN(server);
				menuShowBooks(server);
				break;
			case("2"):
				//Title
				searchTitle(server);
				menuShowBooks(server);
				break;
			case("3"):
				//Go back
				menuShowBooks(server);
				break;
			default:
				logger.info("Not valid");
				break;
			}
		}
		
		public static void menuBook(IRemote server){
			String input = "";
			displaySubMenu(bookSelectionMenu);
			input = System.console().readLine();
			switch(input){
			case("1"):
				//Buy
				buyBook(server);
				menuShowBooks(server);
				break;
			case("2"):
				//Write a review
				menuReviews(server);
				break;
			case("3"):
				//Go back
				menuShowBooks(server);
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
				menuBook(server);
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
			emailUser = input;
			
			logger.info("Password:");
			input = System.console().readLine();
			password = input;		
			
			try {
				server.registerClient(emailUser, password, false);
				
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
		
		public static void searchISBN(IRemote server){
			int input;
			Car b = null;
			logger.info("ISBN:");
			input = Integer.parseInt(System.console().readLine());
			
			try {
				b = server.getCarBymat(input);
				logger.info(b.toString());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
		
		public static void searchTitle(IRemote server){
			String input= "";
			Car b = null;
			logger.info("Title:");
			input = System.console().readLine();
			
			try {
				b = server.getCarByBrand(input);
				logger.info(b.toString());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		public static void showBooks(IRemote server){
			List<Car> books = null;
			int input;
			
			try {
				books = server.showCarsInStore();
			} catch (RemoteException e) {
				logger.info(e.getMessage());
			}

			for(int i = 0; i<books.size(); i++){
				Car b = books.get(i);
				logger.info((i+1) + ".-" + b.toString());
			}
			logger.info("Choose a book to visualize: ");
			input =  Integer.parseInt(System.console().readLine());
			showBook(server, input - 1);
		}
		
		public static void showBook(IRemote server, int numBook){
			List<Car> books = null;
			
			try {
				books = server.showCarsInStore();
			} catch (RemoteException e) {
				logger.error(e.getMessage());
			}
			Car b = books.get(numBook);
			bookSelectionTitle = b.getBrand();
			logger.info(b.toString());
		}
		
		public static void buyBook(IRemote server){
			boolean buyOk = false;
			
			try {
				buyOk = server.rentCar(emailUser, bookSelectionTitle);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			logger.info(""+buyOk);
			
			if(buyOk == true){
				logger.info("You have bought "+bookSelectionTitle);
			}else{
				logger.info("Not pssible. Try later!");
			}
		}
		/*
		//Adpatarlo para que coja las reviews del libro que esta sellecionado y llamarlo en showBook
		public static void showReviews(IRemote server){
			List<Review> reviews = null;
			try {
				reviews = server.getAllReviews();
			} catch (RemoteException e) {
				logger.info(e.getMessage());
			}
		}
		*/
		public static void main(String[] args) {
			if (args.length != 3) {
				logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
				System.exit(0);
			}
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			LogIn logIn;
			try{
				String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
				IRemote server = (IRemote) java.rmi.Naming.lookup(name);
				//Menu
				//mainMenu(server);
				logIn = new LogIn(server);
			}catch (Exception e) {
				logger.error("RMI Example exception: " + e.getMessage());
				e.printStackTrace();
			}		
		}
	}