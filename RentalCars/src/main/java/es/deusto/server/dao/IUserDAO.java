package es.deusto.server.dao;

import java.util.List;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

public interface IUserDAO {
	
	/**
	 * Store a client in the dataBase
	 * @return true of false to tell if it worked
	 */
	boolean storeClient(Client c);
	
	/**
	 * Get a client form the database by its email
	 * @param email Email of the client you want to get
	 */
	Client retrieveClient(String email);
	
	/**
	 * Updates the row in the database of a given client 
	 * @return true of false to tell if it worked
	 */
	boolean updateClient(Client c);

	
	/**
	 * Get a rent from the database by its ID
	 * @param id_rent
	 */
	Rent retrieveRent(int id_rent);
	
	/**
	 * Updates the row in the database of a given rent
	 * @return true of false to tell if it worked
	 */
	boolean updateRent(Rent r);

	/**
	 * Store a Car in the database
	 * @return true of false to tell if it worked
	 */
	boolean storeCar(Car c);
	
	/**
	 * Get a Car from the database by its id
	 * @param id
	 */
	Car retrieveCar(int ID );
	
	/**
	 * Updates the row in the database of a given Car
	 * @return true of false to tell if it worked
	 */
	boolean updateCar(Car c);

	/**
	 * Get a Car from the database by its brand
	 */
	Car retrieveCarByParameter(String brand);
	
	

	/**get all the clients of the Database
	 * @return A List<Client> of all clients 
	 */
	public List<Client> getAllClients();
	
	/** get all the Rents of the Database
	 * @return A List<Rent> of all clients 
	 */
	public List<Rent> getAllRents();
	
	/**
	 * get all the cars of the Database
	 * @return A List<Car> of all clients 
	 */
	public List<Car> getAllCars();
	
	/**
	 * Eliminates a Rent from the database
	 */
	void deleteRent(Rent r);
	/**
	 * Eliminates a car from the database
	 */
	void deleteCar(Car c);


}
