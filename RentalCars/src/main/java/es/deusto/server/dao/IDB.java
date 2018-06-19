package es.deusto.server.dao;


	import java.rmi.RemoteException;
import java.util.List;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

	

	public interface IDB {
		
		/**
		 * get all the Clients of the Database
		 * @return A List<Client> of all clients 
		 */
		List<Client> getAllClients();
		
		/**
		 * get all the Rents of the Database
		 * @return A List<Rent> of all clients 
		 */
		List<Rent> getAllRents();
		
		/**
		 * get all the Cars of the Database
		 * @return A List<Car> of all clients 
		 */
		List<Car> getAllCars();
		
		  /**
		   * Allow the Client to rent a selected car
		 * @param email Email of the renting client
		 * @param car_brand the brand of the car to rent
		 * @return true or false to tell if it worked
		 */
		boolean rentCar(String email, String car_brand);
	
		/**
		  * creates a new client
		 * @param email 
		 * @param password
		 * @param role (true admin; false client)
		 * @return true or false to tell if it worked
		 */
		boolean registerClient(String email, String password,boolean role);

		/**
		 * Adds a new car to the database
		 * @param b the car to add
		 * @return true or false to tell if it worked
		 */
		boolean addCarToDb(Car b);
		
		/**
		 * Adds a new Rent to the database
		 * @param b the car its from
		 * @param r the rent
		 * @param client the client that wrote it
		 * @return true or false to tell if it worked
		 */
		boolean addRent(Car b, Rent r, Client client );

		/**
		 * Get a car from  the database by its mat
		 * @param mat
		 * @return The Car 
		 */
		Car showCarBymat	(int mat);
		
		/**
		 * Get a Car from the database by its brand
		 * @param brand of the car
		 * @return The car
		 */
		Car showCarByBrand	(String brand);
		
		/**
		 * Get a Rent from the database by its ID
		 * @param id_rent
		 * @return The Rent
		 */
		Rent showRent	(int id_rent);
		
		/**
		 * Get a Client form the database by its email
		 * @param email Email of the client you want to get
		 * @return The Client
		 */
		Client showClient	(String email);
		
		/**
		 * Get all the rents a Client wrote 
		 * @param email of the client
		 * @return A List<Rent> of a given client
		 */
	
		List<Rent> getClientRents(String email);
	
		
		boolean registerRent(Rent r);

		/**
		 * Get all the rents of a car
		 * @param brand of the car
		 * @return A List<Rent> of a given car
		 */
		List<Rent> getCarRents(String brand);
		
		/**
		 * Gets the average rating of a car
		 * @param brand of the car
		 * @return a double the average rating
		 */
		double averageRatingByCar(String brand);
		
		/**
		 * Gets the average rating given by a client
		 * @param email of the client
		 * @return a double the average rating
		 */
		double averageRatingByClient(String email);
		
		/**
		 * Eliminates a Rent from the database
		 * @param id_rent 
		 * @return true or false to tell if it worked
		 */
		boolean deleteRent(int id_rent);
		
		/**
		 * Eliminates a car from the database
		 * @param mat
		 * @return true or false to tell if it worked
		 */
		boolean deleteCar(int mat);

		boolean addRentToDb(Rent rent);

		Car showCarByModel(String model);
		

		
		
}
