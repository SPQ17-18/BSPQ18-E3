package es.deusto.client.controller;

import java.util.logging.Logger;



import es.deusto.server.remote.IRemote;

public class RMIServiceLocator {
	private IRemote remote;
	
	private final static Logger logger = Logger.getLogger(Controller.class.getName());
	
	
	public RMIServiceLocator(){ 
	    
    }
	
	  
	
	  public void setService(String args0, String args1, String args2) {
			try {

				String service = "//" + args0 + ":" + args1 + "/" + args2;

    			
    			
    			 remote = (IRemote) java.rmi.Naming.lookup(service);
    				System.out.println("* Server '" + service + "' active and waiting...");
    			} catch (Exception e) {
    				System.err.println("- Exception running the server: " + e.getMessage());
    				e.printStackTrace();
    			}
    }
  

		public IRemote getService() {
			return remote;
		}

	}
