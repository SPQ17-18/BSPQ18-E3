package es.deusto.client.controller;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import es.deusto.client.controller.RMIServiceLocator;
import es.deusto.client.gui.InitialWindow;
import es.deusto.server.data.Client;
import es.deusto.server.data.Car;
import es.deusto.server.data.Rent;

public class Controller {
	
	private RMIServiceLocator rsl;

	/**
	 * Constructor de CinePlusController
	 * @param rmi - RMIServiceLocator
	 */
	public Controller(RMIServiceLocator rmi) {
		this.rsl = rmi;
	}
	
	
	
	
	
	public boolean RegistrarClient(String user, String email, String name, String address, String password,
			String age, boolean admin) throws RemoteException {
		return rsl.getService().registrarClient(user, email, name, address, password, age, admin);
	}
	
	
	public boolean RegistreClient(String user, String email, String name, String address, String password,
			String age, float money, boolean admin) throws RemoteException {
		return rsl.getService().registreClient(user, email, name, address, password, age, money, admin);
	}
	
	
	public boolean Login(String user, String password) throws RemoteException {
		return rsl.getService().clientRegistrado(user, password);
	}
	
	
	public List<String> getModel() throws RemoteException {
		return rsl.getService().Model();
	}
	
	
	public List<String> getBrand() throws RemoteException {
		return rsl.getService().Brand();
	}
	
	
	public boolean Rent(Rent rent) throws RemoteException {
		return rsl.getService().Rent(rent);
	}
	
	
	public List<Car> Search(String brand, String model,String type) throws RemoteException {
		return rsl.getService().Search(brand, model, type);
	}
	
	public List<Car> getRent(String email) throws RemoteException {
		return rsl.getService().getRent(email);
	}
	
	
	public Client returnClient(String email)throws RemoteException {
		return rsl.getService().returnClient(email);
	}
	
	
	public void updateClient(Client cli)throws RemoteException {
		rsl.getService().updateClient(cli);
	}
	
	
	public void deleteClient(Client cli)throws RemoteException {
		 rsl.getService().deleteClient(cli);
	}
	public void exit() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
			System.exit(0);
		}
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
					try {
						RMIServiceLocator rmi=new RMIServiceLocator();
						rmi.setService(args);
						Controller con=new Controller(rmi);
						InitialWindow.frame = new InitialWindow(rmi,con);
						InitialWindow.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
}
					
	
	
	
}
