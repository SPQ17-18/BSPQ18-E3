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
	 * @param role (true admin; false client)
	 * @return true or false to tell if it worked
	 * @throws RemoteException
	 */
	boolean registerClient(String email, String password,boolean role) throws RemoteException;
	
	 /**
	  * adds a car to database
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
	 * gives you a all the Rents in the database
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
	 * Get a car by its mat
	 * @param unique car mat
	 * @return a Car with the selected mat
	 * @throws RemoteException
	 */
	Car getCarByMat	(int mat)throws RemoteException;
	
	/**
	 * Get a Car by its ID
	 * @param brand of the car
	 * @return a car with the selected brand
	 * @throws RemoteException
	 */
	Car getCarByBrand	(String brand)throws RemoteException;
	
	
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
	 * @return a User
	 * @throws RemoteException
	 */
	Client getClient(String email)throws RemoteException;
	
	
	 /**
	  * Rent a car
	 * @param email of the client
	 * @param brand of the car
	 * @return true of false to tell if it worked
	 * @throws RemoteException
	 */
	boolean rentCar(String email, String brand) throws RemoteException;
	
	 /**
	  * add a new rent to a car
	 * @param b the car to add a rent
	 * @param r the Rent
	 * @param u the client who made the rent
	 * @return true of false to tell if it worked
	 * @throws RemoteException
	 */
	boolean addRent(Car c, Rent r, Client cli) throws RemoteException;
	
	 /**
	  * Get all the Rents a Client Made
	 * @param email of the client
	 * @return a List<Rent> 
	 * @throws RemoteException
	 */
	List<Rent> getClientsRents(String email)  throws RemoteException;
	
	 /**
	  * Get all the Rents of a car
	 * @param brand of the car
	 * @return List<Rent>
	 * @throws RemoteException
	 */
	List<Rent> getCarRents(String brand)  throws RemoteException;
	
	 /**
	  * Get the average Rating of a car
	 * @param brand of the car
	 * @return a double 
	 * @throws RemoteException
	 */
	double averageRatingByCar(String brand)  throws RemoteException;
	
	 /**
	  * Get the average Rating given by a Client
	 * @param email
	 * @return a double 
	 * @throws RemoteException
	 */
	double averageRatingByClient(String email)  throws RemoteException;
	
	 /**
	  * Deletes a Rent
	 * @param id_rent
	 * @throws RemoteException
	 */
	void deleteRent(int id_rent) throws RemoteException;
	
	 /**
	  * Delete a car
	 * @param mat
	 * @throws RemoteException
	 */
	void deleteCar(int mat) throws RemoteException;
	
	 /**
	  * Creates new car in the database
	 * @param ID unique identifier- mat
	 * @param colour
	 * @param model
	 * @param type 
	 * @param brand
	 * @param accesories
	 * @throws RemoteException
	 */
	boolean addCar(int mat, String colour, String model, String type, String brand, String accesories, double price) throws RemoteException;
}