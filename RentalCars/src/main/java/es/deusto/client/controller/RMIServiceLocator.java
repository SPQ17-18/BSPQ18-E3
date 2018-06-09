package es.deusto.client.controller;

import es.deusto.server.remote.ICarsRemote;

public class RMIServiceLocator {
	private ICarsRemote icr;

	public RMIServiceLocator(){ 
    }
	
	
	public ICarsRemote getService() {
		return icr;
	}
	
	
	public void setService(String[] args) {
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			icr = (ICarsRemote) java.rmi.Naming.lookup(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
