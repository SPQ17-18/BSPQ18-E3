package es.deusto.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;




public class DAO implements IDAO {

	private PersistenceManagerFactory pmf;
	final static  Logger logger = LoggerFactory.getLogger(DAO.class);

	public DAO(){
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	@Override
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

	@Override
	public Client retrieveClient(String email) {
		// TODO Auto-generated method stub
		Client client = null;
		Client clientCopy = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			client = pm.getObjectById(Client.class, email);
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

	@Override
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




	@Override
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

	@Override
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

	@Override
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

	@Override
	public Car retrieveCar(int mat) {
		// TODO Auto-generated method stub
		Car car = null;
		Car carCopy = null;
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(2);
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			car = pm.getObjectById(Car.class, mat);
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

	@Override
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

	@Override
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
					car = new Car(p.getMat(), p.getColour(),p.getBrand(),p.getModel(),p.getType(),p.getAccesories(),p.getPrice());
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

	@Override
	public List<Client> getAllClients() {
		// TODO Autof-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.getFetchPlan().setMaxFetchDepth(3);

		List<Client> clients=new ArrayList<>();
		try {
			tx.begin();
			Extent<Client> extentP = pm.getExtent(Client.class);

			for (Client p : extentP) {

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

	@Override
	public List<Rent> getAllRents() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		pm.getFetchPlan().setMaxFetchDepth(3);

		List<Rent> rents=new ArrayList<>();
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

	@Override
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
	@Override
	public void deleteRent(Rent r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.deletePersistent(r);
		tx.commit();
	}
	@Override
	public void deleteCar(Car b) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		tx.begin();
		pm.deletePersistent(b);
		tx.commit();

	}
	@Override
	public boolean storeRent(Rent r) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		boolean ret=true;
		try {
			tx.begin();

			pm.makePersistent(r);
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

	/*
	public  boolean deleteDatabase() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        boolean r = true;
        try
        {
            tx.begin();

            logger.info("Deleting test clients from persistence. Cleaning up.");
            Query<Client> q1 = pm.newQuery(Client.class);
            Query<car> q2 = pm.newQuery(car.class);
            Query<Rent> q3 = pm.newQuery(Rent.class);
            	q1.deletePersistentAll();
            	q2.deletePersistentAll();
            	q3.deletePersistentAll();


            tx.commit();

        }
        catch(Exception ex) {
            logger.error("# Error deleting DB: " + ex.getMessage());
            r = false;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        return r;

	}
	 */
}
