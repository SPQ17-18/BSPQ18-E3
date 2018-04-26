package es.deusto.server.dao;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

public  class UserDAO implements IUserDAO{
	
	
	private PersistenceManagerFactory pmf;
	final static  Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public UserDAO(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}

	@Override
	public boolean storeClient(Client c) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Client retrieveClient(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateClient(Client c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rent retrieveRent(int id_rent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRent(Rent r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean storeCar(Car c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Car retrieveCar(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCar(Car c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Car retrieveCarByParameter(String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rent> getAllRents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRent(Rent r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCar(Car c) {
		// TODO Auto-generated method stub
		
	}

	
	

	
	
}
