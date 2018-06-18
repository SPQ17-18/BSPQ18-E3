package es.deusto.server.remote;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.dao.DB;
import es.deusto.server.dao.IDB;
import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;





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
		List<Client> users = db.getAllClients();
		if(users.isEmpty()){
			throw new RemoteException("No users");
		}
		else{
			return(users);
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

		Car b = new Car(1,"Red", "Seat","Altea", "turismo", "gps", 19.90);

		IDB db = new DB();

		db.addCarToDb(b);
		Car b1=db.showCarByBrand(b.getBrand());
		logger.info("This is the car"+ b1);

		return(b1);
	}

	@Override
	public Car getCarBymat(int mat) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		Car b = null;
		try{
			b = db.showCarBymat(mat);
		}catch (Exception e){

		}
		return b;
	}

	@Override
	public Car getCarByBrand(String brand) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		Car b = null;
		try{
			b=db.showCarByBrand(brand);
		}catch (Exception e){

		}
		return b;
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
	public boolean rentCar(String email, String car_brand) throws RemoteException{
		// TODO Auto-generated method stub
		IDB db =new DB();
		boolean a=false;
		try{
			a = db.rentCar(email, car_brand);
		}catch(Exception e){

		}
		return a;
	}

	public boolean addRent(Car b, Rent r, Client u) {
		// TODO Auto-generated method stub
		IDB db =new DB();
		boolean a=false;
		try{
			a = db.addRent(b, r, u);
		}catch(Exception e){

		}
		return a;
	}
	public List<Rent> getClientRents(String email) {
		IDB db =new DB();
		List<Rent> userRents=null;
		try{
			userRents= db.getClientRents(email);
		}catch(Exception e){

		}
		return userRents;
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
	public  double averageRatingByCar(String colour){
		IDB db =new DB();
		double average=0;
		try{
			average= db.averageRatingByCar(colour);
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
	public void deleteCar(int mat){
		// TODO Auto-generated method stub
		IDB db = new DB();
		try{
			db.deleteCar(mat);


		}catch(Exception e){

		}  
	}
/**
 * @return boolean true if the car is created and stored in db
 */
	@Override
	public boolean addCar(int mat, String colour, String brand, String model, String type, double price,
			String accesories, String img) {
		// TODO Auto-generated method stub
		IDB db = new DB();
		Car b=new Car(mat,  colour,  brand, model, type, accesories, price);
		try{
			db.addCarToDb(b);
		}catch(Exception e){

		}  
		
		
		return false;
	}

@Override
public boolean addRent(Rent rent) throws RemoteException {
	// TODO Auto-generated method stub
	if(rent!=null){
		IDB db = new DB();
		return db.addRentToDb(rent);
	}else{
		throw new RemoteException("Invalid rent");
	}
}

@Override
public Car getCarByModel(String model) throws RemoteException {
	// TODO Auto-generated method stub
				IDB db =new DB();
			Car b = null;
			try{
				b = db.showCarByModel(model);
			}catch (Exception e){

			}
			return b;
		}




}