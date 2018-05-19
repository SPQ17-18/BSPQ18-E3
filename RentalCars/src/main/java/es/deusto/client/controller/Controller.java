package es.deusto.client.controller;

import java.rmi.RemoteException;
import java.util.logging.Logger;

import es.deusto.client.gui.VentanaLogin;

public class Controller {
	private final static Logger logger = Logger.getLogger(Controller.class.getName());
	private static final Controller cont= new Controller();
	 
		private static RMIServiceLocator rsl;
	
	public Controller(String[] args) throws RemoteException {
		rsl = new RMIServiceLocator();
		rsl.setService(args[0], args[1], args[2]);
		new VentanaLogin(this);
	}

	public Controller(){
		
	}
	public static Controller getController() {
		return cont;
	}
	
	
	public static boolean setController(String[] args) throws RemoteException {
		rsl = new RMIServiceLocator();
		rsl.setService(args[0], args[1], args[2]);
		return true;
	}
	
	
	
	//Login
		
	 public boolean login(String nick, String contrasenia) {
	    	
		 try {
	    		
	    		return rsl.getService().logIn(nick);
	            
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	    	return false;
	    }


		public static void exit() {
	    	System.exit(0);
	    }
	
	
}
