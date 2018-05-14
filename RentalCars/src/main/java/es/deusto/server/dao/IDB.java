package es.deusto.server.dao;

import java.util.List;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

public interface IDB {

	public boolean registerClient(String email, String password, boolean role);
	public boolean registerClient(Client cl);
	public List<Car> getAllCars();
	public List<Client> getAllClients();
	public List<Rent> getAllRents();
	
	public boolean addCarToDb(Car c);

	public boolean addRent(Car c, Rent r, Client client);

	public boolean rentCar(String email, String car_brand);
	
	public Car showCarByMat(int mat);
	public Car showCarByBrand(String brand);
	
	
	public Client showClient(String email);

	
	public Rent showRent(int id_rent);

	public List<Rent> getClientRents(String email);

	public List<Rent> getCarRents(String brand);
	

	public boolean deleteRent(int id_rent);

	public boolean deleteCar(int mat);

}
