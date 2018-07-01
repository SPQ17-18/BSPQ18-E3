package es.deusto.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;




public interface IRemote extends Remote{

	 /**
	  * creates a new client
	 * @param email 
	 * @param password
	 * @param role (true admin; false user)
	 * @return true or false to tell if it worked
	 * @throws RemoteException
	 */
	boolean registerClient(String email, String password,boolean role) throws RemoteException;
	
	 /**
	  * adds a car to the database
	 * @param car
	 * @return true or false to tell if it worked 
	 * @throws RemoteException
	 */
	boolean addCar(Car car) throws RemoteException;
	

	/**
	 * gives you a all the cars in the database
	 * @return a list<Car> with all the cars in the database
	 * @throws RemoteException
	 */
	List<Car> showCarsInStore() throws RemoteException;
	
	
	/**
	 * gives you a all the clients in the database
	 * @return a list<Client> with all the clients in the database
	 * @throws RemoteException
	 */
	List<Client> getAllClients() throws RemoteException;
	
	/**
	 * gives you a all the Rent in the database
	 * @return a list<Rent> with all the cars in the database
	 * @throws RemoteException
	 */
	List<Rent> getAllRents() throws RemoteException;
	
	/**
	 * Test 
	 * @return the test car
	 * @throws RemoteException
	 */
	Car carTest()throws RemoteException;
	
	/**
	 * Get a car by its mat,brand or model
	 * @param mat unique car ID
	 * @return a Car with the selected mat
	 * @throws RemoteException
	 */
	Car getCarBymat	(int mat)throws RemoteException;
	Car getCarByBrand	(String brand)throws RemoteException;
	Car getCarByModel(String model) throws RemoteException;
	
	/**
	 * Get a Rent by its ID
	 * @param id_rent unique number
	 * @return a Rent
	 * @throws RemoteException
	 */
	Rent getRent(int id_rent)throws RemoteException;
	
	/**
	 * Get a Client by its Email
	 * @param email
	 * @return a Client
	 * @throws RemoteException
	 */
	Client getClient(String email)throws RemoteException;
	
	
	 /**
	  * Get all the Rents a Client Made
	 * @param email of the user
	 * @return a List<Rent> 
	 * @throws RemoteException
	 */
	List<Rent> getClientRents(String email)  throws RemoteException;
	
	 /**
	  * Get all the Rents of a Car
	 * @param colour of the car
	 * @return List<Rent>
	 * @throws RemoteException
	 */
	List<Rent> getCarRents(String brand)  throws RemoteException;
		
	 /**
	  * Eliminates a Rent
	 * @param id_rent
	 * @throws RemoteException
	 */
	void deleteRent(int id_rent) throws RemoteException;
	
	 /**
	  * Eliminates a Car
	 * @param mat
	 * @throws RemoteException
	 */
	void deleteCar(int mat) throws RemoteException;
	
	 /**
	  * Creates new car in the database
	 * @param mat unique identifier
	 * @param colour of the car
	 * @param brand of car
	 * @param model 
	 * @param type
	 * @param price
	 * @param accesories
	 * @param img
	 * @return true of false to tell if it worked
	 * @throws RemoteException
	 */
	boolean addCar(int mat, String colour, String brand, String model, String type, double price, String accesories, String img) throws RemoteException;
	boolean addRent1(int id_rent, int mat, String email)throws RemoteException;
	
	
}

	
