package es.deusto.server.remote;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.deusto.server.data.*;

import es.deusto.server.dao.*;


public class Remote extends UnicastRemoteObject implements IRemote {

	private IDAO dao;
	
	private static final long serialVersionUID = 1L;
	final static  Logger logger = LoggerFactory.getLogger(Remote.class);

	public Remote() throws RemoteException {
		super();

	}
	
	@Override
	public boolean registerClient(String Client) throws RemoteException {
		return false;
		
	}

	@Override
	public boolean login(String nick) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.isCorrect(nick);
	}
	@Override
	public boolean addCar(Car car) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Car> showCarsInStore() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClients() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rent> getAllRents() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car carTest() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car getCarByMat(int mat) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car getCarByBrand(String brand) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rent getRent(int id_rent) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClient(String email) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean rentCar(String email, String brand) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRent(Car c, Rent r, Client cli) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Rent> getClientsRents(String email) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rent> getCarRents(String brand) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRent(int id_rent) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCar(int mat) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addCar(int mat, String colour, String model, String type, String brand, String accesories,
			double price) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	



	

	

}