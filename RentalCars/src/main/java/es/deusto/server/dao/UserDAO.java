package es.deusto.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;



public class UserDAO implements IUserDAO {

	private PersistenceManagerFactory pmf;
	final static  Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public UserDAO(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	public boolean storeClient(Client u) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean ret=true;
		try {
			tx.begin();

			pm.makePersistent(u);
			tx.commit();
		} catch (Exception ex) {

			ret=false;

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return ret;
	}


	public Client retrieveClient(String DNI) {
		// TODO Auto-generated method stub
		Client client = null;
		Client clientCopy = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			client = pm.getObjectById(Client.class, DNI);
			clientCopy = (Client)pm.detachCopy(client);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{

		}

		finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return clientCopy;
	}

	
	public boolean updateClient(Client u) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean r =true;
		try {
			tx.begin();
			pm.makePersistent(u);
			tx.commit();
		} catch (Exception ex) {

			r=false;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return r;
	}





	public Rent retrieveRent(int id_rent) {
		// TODO Auto-generated method stub
		Rent rent = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			rent = pm.getObjectById(Rent.class, id_rent);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{

		}

		finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return rent;
	}

	
	public boolean updateRent(Rent rent) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean r =true;
		try {
			tx.begin();
			pm.makePersistent(rent);
			tx.commit();
		} catch (Exception ex) {

			r=false;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return r;
	}

	
	public boolean storeCar(Car b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean ret=true;
		try {
			tx.begin();

			pm.makePersistent(b);
			tx.commit();
		} catch (Exception ex) {

			ret=false;

		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return ret;
	}

	
	public Car retrieveCar(int Mat) {
		// TODO Auto-generated method stub
		Car car = null;
		Car carCopy = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			car = pm.getObjectById(Car.class, Mat);
			carCopy = (Car)pm.detachCopy(car);
			tx.commit();
		} catch (javax.jdo.JDOObjectNotFoundException jonfe)
		{

		}

		finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return carCopy;
	}

	
	public boolean updateCar(Car b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean r =true;
		try {
			tx.begin();
			pm.makePersistent(b);
			tx.commit();
		} catch (Exception ex) {

			r=false;
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return r;
	}

	
	public Car retrieveCarByParameter(String brand) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.getFetchPlan().setMaxFetchDepth(3);
		Car car = null;

		try {
			tx.begin();
			Extent<Car> extentP = pm.getExtent(Car.class);

			for (Car p : extentP) {

				if (p.getBrand().equals(brand)) {
					car = p;
				}
			}
			tx.commit();
		} catch (Exception ex) {


		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		logger.info(car.toString());
		return car;
	}

	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.getFetchPlan().setMaxFetchDepth(3);

		List<Client> clients=new ArrayList<>();
		try {
			tx.begin();
			Extent<Client>tentP = pm.getExtent(Client.class);

			for (Client p : tentP) {

				clients.add(p);


			}

			tx.commit();
		} catch (Exception ex) {

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return clients;
	}

	public List<Rent> getAllRents() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.getFetchPlan().setMaxFetchDepth(3);

		List<Rent> rents =new ArrayList<>();
		try {
			tx.begin();
			Extent<Rent> extentP = pm.getExtent(Rent.class);

			for (Rent p : extentP) {

				rents.add(p);


			}

			tx.commit();
		} catch (Exception ex) {

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return rents;
	}

	public List<Car> getAllCars() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.getFetchPlan().setMaxFetchDepth(3);

		List<Car> cars=new ArrayList<>();
		try {
			tx.begin();
			Extent<Car> extentP = pm.getExtent(Car.class);

			for (Car p : extentP) {

				cars.add(p);


			}

			tx.commit();
		} catch (Exception ex) {

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}

		return cars;
	}
	public void deleteRent(Rent r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.deletePersistent(r);
		tx.commit();
	}
	public void deleteCar(Car b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.deletePersistent(b);
		tx.commit();

	}

	
}