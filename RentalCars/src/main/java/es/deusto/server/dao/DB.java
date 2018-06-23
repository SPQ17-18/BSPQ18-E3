package es.deusto.server.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;




public class DB implements IDB{

	private static final long serialVersionUID = 1L;
	IDAO dao;
	final static  Logger logger = LoggerFactory.getLogger(DB.class);

	public DB(){
		super();
		dao = new DAO();

	}

	public DB(IDAO udao) {
		super();
		dao = udao;

	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return dao.getAllClients();
	}

	@Override
	public List<Rent> getAllRents() {
		// TODO Auto-generated method stub
		return dao.getAllRents();
	}

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return dao.getAllCars();
	}

	@Override
	public boolean rentCar(String email, String car_brand) {
		// TODO Auto-generated method stub
		Car car =null;		
		Client client =null;
		boolean ret=true;
		double price;
	System.out.println("****************retrievingcarbyparameter");
		try {
			car= dao.retrieveCarByParameter(car_brand);		
			System.out.println("****************client = dao.retrieveClient(email)");

			client = dao.retrieveClient(email);	
			System.out.println("finsihed...............");
		} catch (Exception  e) {
			e.printStackTrace();
			logger.error("Exception launched in checking if the data already exist: ");
			logger.trace(e.getMessage());
			ret=false;
		}
		if(client.getMoney()>=car.getPrice()){

			if (car == null  || client ==null ) {

			}else if (car !=null  &&  client != null  ){
				dao.performRent(client, car);
				
				
			}	

		}
		else{
			logger.error("Price higher than your balance");
		}


		return ret;

	}


	public boolean addRent(Car b, Rent r, Client u ) {
		Car car =null;
		Rent rent = null ;
		Client client =null;
		boolean ret=true;

		try {

			car= dao.retrieveCarByParameter(b.getBrand());				
			rent = dao.retrieveRent(r.getId_rent());
			client = dao.retrieveClient(u.getEmail());


		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			e.printStackTrace();
			ret=false;
		}


		if (car == null  || client == null ||rent !=null ) {


		}else if (car !=null  && client !=null && rent == null  ){
			int a= getAllRents().size()+1;
			r.setCar(car);
			r.setClient(client);
			r.setId_rent(a);

			car.addRent(r);										
			client.addRent(r);


			dao.updateCar(car);
			dao.updateClient(client);
			//TODO:: llamar a registerRent
			dao.updateRent(rent);
			dao.retrieveRent(r.getId_rent());
		}

		return ret;	
	}





	@Override
	public boolean registerClient(String email, String password, boolean role) {
		// TODO Auto-generated method stub
		Client client = null;
		boolean ret=true;

		try {
			client = dao.retrieveClient(email);
		} catch (Exception  e) {
			logger.error("Exception launched: ");
			logger.trace(e.getMessage());
			e.printStackTrace();
			ret=false;
		}

		if (client != null) {
			client.setPassword(password);
			client.setRole(role);

			dao.updateClient(client);

		} else {

			client = new Client(email, password,role);
			dao.storeClient(client);

		}
		return ret;
	}

	public boolean registerClient(Client u) {


		Client client = null;
		boolean ret=true;

		try {
			client = dao.retrieveClient(u.getEmail());
		} catch (Exception  e) {
			logger.error("Exception launched: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret=false;
		}

		if (client != null) {

			client.setPassword(u.getPassword());
			client.setRole(u.getRole());

			dao.updateClient(client);

		} else {


			dao.storeClient(u);

		}
		return ret;
	}

	@Override
	public boolean addCarToDb(Car b) {
		// TODO Auto-generated method stub
		Car car = null;

		boolean ret=true;

		try {

			car  = dao.retrieveCar(b.getMat());


		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret = false;
		}

		if (car != null ) {

		}else{



			dao.storeCar(b);


		}

		return ret;
	}

	@Override
	public Car showCarBymat(int mat) {
		// TODO Auto-generated method stub
		Car b=dao.retrieveCar(mat);
		// ao.retrieveLicenseByName(name);
		return b;
	}

	@Override
	public Car showCarByBrand(String brand) {
		// TODO Auto-generated method stub
		Car b=dao.retrieveCarByParameter(brand);
		// ao.retrieveLicenseByName(name);
		return b;
	}

	@Override
	public Rent showRent( int id_rent) {
		// TODO Auto-generated method stub
		Rent r=dao.retrieveRent(id_rent);

		return r;
	}

	@Override
	public Client showClient(String email) {
		// TODO Auto-generated method stub
		Client u=dao.retrieveClient(email);
		// ao.retrieveLicenseByName(name);
		return u;
	}
	public List<Rent> getClientRents(String email){
		Client u=showClient(email);
		List<Rent> clientRents=u.getRents();
		return clientRents;

	}
	public List<Rent> getCarRents(String brand){
		Car b = showCarByBrand(brand);
		List<Rent> carRents=b.getRents();
		return carRents;
	}
	/*public double averageRatingByCar(String brand){
		List<Rent> carRents=null;
		carRents=getCarRents(brand);
		double total=0;


		for(Rent r : carRents){
			total=total+r.getRating();

		}
		return total/carRents.size();
	}
	public double averageRatingByClient(String email){
		List<Rent> clientRents=null;
		clientRents=getClientRents(email);
		double total=0;


		for(Rent r : clientRents){
			total=total+r.getRating();

		}
		return total/clientRents.size();
	}*/
	public boolean deleteRent(int id_rent){
		boolean ret=false;
		Rent r;

		r =showRent(id_rent);
		if(r!=null){

			dao.deleteRent(r);


			ret=true;}
		else{

		}


		return ret;


	}
	public boolean deleteCar(int mat){
		boolean ret=false;
		Car b;

		b =showCarBymat(mat);
		if(b!=null){

			dao.deleteCar(b);


			ret=true;}
		else{

		}


		return ret;


	}

	@Override
	
		public boolean registerRent(Rent r) {


			Rent rent = null;
			boolean ret=true;

			try {
				rent = dao.retrieveRent(r.getId_rent());
			} catch (Exception  e) {
				logger.error("Exception launched: ");
				logger.trace(e.getMessage());
				e.getStackTrace();
				ret=false;
			}

			if (rent != null) {

				rent.setId_rent(r.getId_rent());
				rent.setComment(r.getComment());
				rent.setRating(r.getRating());

				
				dao.updateRent(rent);
			} else {

				dao.storeRent(r);

			}
			return ret;
		}

	@Override
	public boolean addRentToDb(Rent rent) {
		// TODO Auto-generated method stub
		Rent ren = null;

		boolean ret=true;

		try {

			ren=dao.retrieveRent(rent.getId_rent());

		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret = false;
		}

		if (ren != null ) {

		}else{



			dao.storeRent(rent);


		}

		return ret;
	}

	@Override
	public Car showCarByModel(String model) {
		Car b=dao.retrieveCarByParameter(model);
		// ao.retrieveLicenseByName(name);
		return b;
	}

}
