package es.deusto.server.remote;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import es.deusto.server.data.*;
import es.deusto.server.dao.*;


public class Remote extends UnicastRemoteObject implements IRemote {

	private static final long serialVersionUID = 1L;
	final static  Logger logger = LoggerFactory.getLogger(Remote.class);

	public Remote() throws RemoteException {
		super();

	}

	@Override
	public boolean registerClient(String email, String password, boolean role) throws RemoteException {
		// TODO Auto-generated method stub
		IDB db = new DB();
		//change to objetc the parameters
		return	db.registerClient(email, password, role);
	}

	@Override
	public List<Car> showCarsInStore() throws RemoteException {
		// TODO Auto-generated method stub

		IDB db = new DB();
		List<Car> cars = db.getAllCars();
		if(cars.isEmpty()){
			throw new RemoteException("No cars on store");
		}
		else{
			return(cars);
		}
	}

	@Override
	public List<Client> getAllClients() throws RemoteException {
		// TODO Auto-generated method stub

		IDB db = new DB();
		List<Client> clients = db.getAllClients();
		if(clients.isEmpty()){
			throw new RemoteException("No clients");
		}
		else{
			return(clients);
		}
	}



	@Override
	public boolean addCar(Car car) throws RemoteException{
		if(car!=null){
			IDB db = new DB();
			return db.addCarToDb(car);
		}else{
			throw new RemoteException("Invalid car");
		}
	}

	@Override
	public List<Rent> getAllRents() throws RemoteException {

		IDB db = new DB();
		List<Rent> rents = db.getAllRents();
		if(rents.isEmpty()){
			throw new RemoteException("No Rents");
		}
		else{
			return(rents);
		}
	}

	@Override
	public Car carTest() {

		Car c = new Car(123,"Mercedes","AMG GT-R","deportivo");

		IDB db = new DB();

		db.addCarToDb(c);
		Car c1=db.showCarByBrand(c.getBrand());
		logger.info("Este es el coche"+ c1);

		return(c1);
	}

	@Override
	public Car getCarByMat(int mat) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		Car c = null;
		try{
			c = db.showCarByMat(mat);
		}catch (Exception e){

		}
		return c;
	}

	@Override
	public Car getCarByBrand(String brand) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		Car c = null;
		try{
			c=db.showCarByBrand(brand);
		}catch (Exception e){

		}
		return c;
	}

	@Override
	public Rent getRent(int id_rent) throws RemoteException {
		// TODO Auto-generated method stub
		IDB db =new DB();
		Rent r=null;
		try{
			r= db.showRent(id_rent);
		}catch (Exception e){

		}
		return r;

	}

	@Override
	public Client getClient(String email) throws RemoteException {
		IDB db =new DB();
		if(email != null){

			return db.showClient(email);


		}else{
			throw new RemoteException();



		}


	}

	@Override
	public boolean rentCar(String email, String brand) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		boolean a=false;
		try{
			a = db.rentCar(email, brand);
		}catch(Exception e){

		}
		return a;
	}

	public boolean addRent(Car c, Rent r, Client cl) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		boolean a=false;
		try{
			a = db.addRent(c, r, cl);
		}catch(Exception e){

		}
		return a;
	}
	public List<Rent> getClientRents(String email) {
		IDB db =new DB();
		List<Rent> clientRents=null;
		try{
			clientRents= db.getClientRents(email);
		}catch(Exception e){

		}
		return clientRents;
	}

	public List<Rent> getCarRents(String brand) {
		IDB db =new DB();
		List<Rent> carRents=null;
		try{
			carRents= db.getCarRents(brand);
		}catch(Exception e){

		}
		return carRents;
	}
	public  double averageRatingByCar(String brand){
		IDB db =new DB();
		double average=0;
		try{
			average= db.averageRatingByCar(brand);
		}catch(Exception e){

		}
		return average;

	}
	public  double averageRatingByClient(String email){
		IDB db =new DB();
		double average=0;
		try{
			average= db.averageRatingByClient(email);
		}catch(Exception e){

		}
		return average;

	}

	
	@Override
	public void deleteRent(int id_rent){
		// TODO Auto-generated method stub
		IDB db = new DB();
		try{
			db.deleteRent(id_rent);


		}catch(Exception e){

		}  
	}
	@Override
	public void deleteCar(int ID){
		// TODO Auto-generated method stub
		IDB db = new DB();
		try{
			db.deleteCar(ID);


		}catch(Exception e){

		}  
	}
/**
 * @return boolean true if the car is created and stored in db
 */
	@Override
	public boolean addCar(int mat, String colour, String model, String type,String brand, boolean accesories ) {
		// TODO Auto-generated method stub
		IDB db = new DB();
		Car c=new Car(mat, colour, model, type, brand ,accesories);
		try{
			db.addCarToDb(c);
		}catch(Exception e){

		}  
		
		
		return false;
	}

@Override
public List<Rent> getClientsRents(String email) throws RemoteException {
	// TODO Auto-generated method stub
	IDB db =new DB();
	List<Rent> clientsRents=null;
	try{
		clientsRents= db.getClientRents(email);
	}catch(Exception e){

	}
	return clientsRents;
}





}