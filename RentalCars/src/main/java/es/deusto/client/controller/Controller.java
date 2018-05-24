package es.deusto.client.controller;

import java.rmi.RemoteException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import es.deusto.client.gui.LogIn;


public class Controller {
	private final static Logger logger = Logger.getLogger(Controller.class.getName());
	private static final Controller cont= new Controller();
	 
		private static RMIServiceLocator rsl;
	
	public Controller() {
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
		
	 public boolean login(String nick, String password) {
		 boolean login = false;
			String st=nick+"#"+password;
			
			try {
				login = rsl.getService().login(st);
			} catch (Exception e) {
				(logger).addAppender(new ConsoleAppender(new PatternLayout(),"A problem occured in the log in."));
				// e.printStackTrace();
			}
			
			return login;
		}	
}