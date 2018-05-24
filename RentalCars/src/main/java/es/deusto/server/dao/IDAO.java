package es.deusto.server.dao;

import java.util.ArrayList;
import java.util.List;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;


public interface IDAO {
	
	
	public boolean isCorrect(String ClientPass);
	
	/*Car*/
	public Car getCar(String brand);
	public void deleteCar(int mat);
	public ArrayList <Car> getAllCars();
	public void rentCar(String brand); //o por matricula
	public Car showCars(String brand);
	
	/*Rent*/
	public void deleteRent(Rent r);
	public void updateRent(Rent r);
	public ArrayList <Rent> getAllRents();

	public void storeClient(Client client);

	public void storeCar(Car car);
	
	

}
