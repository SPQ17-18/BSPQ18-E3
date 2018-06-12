package es.deusto.server.dao;


import java.util.List;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

public interface IDAO {

	/**
	 * Store a client in the dataBase
	 * @param u Client you want to store
	 * @return true of false to tell if it worked
	 */
	boolean storeClient(Client u);
	
	/**
	 * Get a Client form the database by its email
	 * @param email Email of the client you want to get
	 * @return The Client
	 */
	Client retrieveClient(String email);
	
	/**
	 * Updates the row in the database of a given Client 
	 * @param u Client you are updating
	 * @return true of false to tell if it worked
	 */
	boolean updateClient(Client u);

	
	/**
	 * Get a Rent from the database by its ID
	 * @param id_rent
	 * @return The Rent
	 */
	Rent retrieveRent(int id_rent );
	
	/**
	 * Updates the row in the database of a given Rent
	 * @param r the rent to update
	 * @return true of false to tell if it worked
	 */
	boolean updateRent(Rent r);

	/**
	 * Store a Car in the database
	 * @param b the Car you want to store
	 * @return true of false to tell if it worked
	 */
	boolean storeCar(Car b);
	boolean storeRent(Rent r);
	
	/**
	 * Get a Car from the database by its ISBN
	 * @param ISBN
	 * @return The Car
	 */
	Car retrieveCar(int ISBN );
	
	/**
	 * Updates the row in the database of a given Car
	 * @param b the car
	 * @return true of false to tell if it worked
	 */
	boolean updateCar(Car b);

	/**
	 * Get a Car from the database by its title
	 * @param title of the car
	 * @return The car
	 */
	Car retrieveCarByParameter(String title);
	
	// boolean deleteDatabase();

	//Get all entities

	/**
	 * get all the Clients of the Database
	 * @return A List<Client> of all clients 
	 */
	public List<Client> getAllClients();
	
	/**
	 * get all the Rents of the Database
	 * @return A List<Rent> of all clients 
	 */
	public List<Rent> getAllRents();
	
	/**
	 * get all the Cars of the Database
	 * @return A List<Car> of all clients 
	 */
	public List<Car> getAllCars();
	
	/**
	 * Eliminates a Rent from the database
	 * @param r the rent to eliminate
	 */
	void deleteRent(Rent r);
	/**
	 * Eliminates a car from the database
	 * @param b the car to eliminate
	 */
	void deleteCar(Car b);


}