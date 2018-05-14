package es.deusto.server.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;

public class DB implements IDB{

	
	private static final long serialVersionUID = 1L;
	IUserDAO dao;
	final static  Logger logger = LoggerFactory.getLogger(DB.class);
	
	public DB(){
		super();
		dao = new UserDAO();

	}

	public DB(IUserDAO udao) {
		super();
		dao = udao;

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

	@Override
	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		return dao.getAllCars();
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
	public boolean addCarToDb(Car c) {
		// TODO Auto-generated method stub
		Car car = null;

		boolean ret=true;

		try {

			car  = dao.retrieveCar(c.getMat());


		} catch (Exception  e) {
			logger.error("Exception launched in checking if the data already exist: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret = false;
		}

		if (car != null ) {

		}else{



			dao.storeCar(c);


		}

		return ret;
	}

	@Override
	public boolean addRent(Car c, Rent r, Client cl) {
		// TODO Auto-generated method stub
		Car car =null;
		Rent rent = null ;
		Client client =null;
		boolean ret=true;

		try {

			car= dao.retrieveCarByParameter(c.getBrand());				
			rent = dao.retrieveRent(r.getid_rent());
			client = dao.retrieveClient(cl.getEmail());


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
			r.setid_rent(a);

			car.addRent(r);										
			client.addRent(r);


			dao.updateCar(car);
			dao.updateClient(client);

		}

		return ret;	
		
	}

	@Override
	public boolean rentCar(String email, String car_brand) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Car car =null;		
				Client client =null;
				boolean ret=true;
				double price;
				try {
					car= dao.retrieveCarByParameter(car_brand);									
					client = dao.retrieveClient(email);					
				} catch (Exception  e) {
					logger.error("Exception launched in checking if the data already exist: ");
					logger.trace(e.getMessage());
					e.printStackTrace();
					ret=false;
				}
				if(client.getMoney()>=car.getPrice()){

					if (car == null  || client ==null ) {

					}else if (car !=null  &&  client != null  ){
						car.addClient(client);										
						client.addCar(car);	

						price=car.getPrice();

						client.setMoney(client.getMoney()-price);

						dao.updateCar(car);
						dao.updateClient(client);

					}	

				}
				else{
					logger.error("Price higher than your balance");
				}


				return ret;
	}

	@Override
	public Car showCarByMat(int mat) {
		// TODO Auto-generated method stub
		Car c=dao.retrieveCar(mat);
		
		return c;
	}

	@Override
	public Car showCarByBrand(String brand) {
		// TODO Auto-generated method stub
		Car c=dao.retrieveCarByParameter(brand);
		return c;
	}

	@Override
	public Client showClient(String email) {
		// TODO Auto-generated method stub
		Client c=dao.retrieveClient(email);
		return c;
	}

	@Override
	public Rent showRent(int id_rent) {
		// TODO Auto-generated method stub
		Rent r=dao.retrieveRent(id_rent);

		return r;
	}

	@Override
	public List<Rent> getClientRents(String email) {
		// TODO Auto-generated method stub
		Client c=showClient(email);
		List<Rent> clientRents=c.getRents();
		return clientRents;
	}

	@Override
	public List<Rent> getCarRents(String brand) {
		// TODO Auto-generated method stub
		Car c= showCarByBrand(brand);
		List<Rent> carRents=c.getRents();
		return carRents;
	}

	@Override
	public boolean deleteRent(int id_rent) {
		boolean ret=false;
		Rent r;

		r =showRent(id_rent);
		if(r!=null){

			dao.deleteRent(r);


			ret=true;
		}
		else{

		}


		return ret;
	}

	@Override
	public boolean deleteCar(int mat) {
		boolean ret=false;
		Car c;

		c =showCarByMat(mat);
		if(c!=null){

			dao.deleteCar(c);


			ret=true;}
		else{

		}


		return ret;
		
	}

	@Override
	public boolean registerClient(Client cl) {
		// TODO Auto-generated method stub
		Client client = null;
		boolean ret=true;

		try {
			client = dao.retrieveClient(cl.getEmail());
		} catch (Exception  e) {
			logger.error("Exception launched: ");
			logger.trace(e.getMessage());
			e.getStackTrace();
			ret=false;
		}

		if (client != null) {

			client.setPassword(cl.getPassword());
			client.setRole(cl.getRole());

			dao.updateClient(client);

		} else {


			dao.storeClient(cl);

		}
		return ret;
	}
	
}
