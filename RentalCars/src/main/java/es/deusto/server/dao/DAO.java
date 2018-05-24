package es.deusto.server.dao;


import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;


import es.deusto.server.data.*;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;



/**
 * 
 * Class for managing all DAO actions
 *
 */
public class DAO implements IDAO {

private PersistenceManagerFactory pmf;
private final static Logger logger = Logger.getLogger(DAO.class.getName());

public DAO() {
	pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
}

@Override
public boolean isCorrect(String ClientPass) {
	// TODO Auto-generated method stub
	String [] data=ClientPass.split("@");
	PersistenceManager pm = pmf.getPersistenceManager();
	
	Transaction tx = pm.currentTransaction();
	//ArrayList <Rent> rents = new ArrayList<Rent>();
	Client cl=null;
	
	pm.getFetchPlan().setMaxFetchDepth(3);
	
	try {
		tx.begin();			
		Query<?> q = pm.newQuery("SELECT FROM " + Client.class.getName()+ " WHERE Client=='"+data[0]+"'");
		@SuppressWarnings("unchecked")
		List <Client> result = (List<Client>) q.execute();
		cl=new Client(result.get(0).getName(), result.get(0).getEmail(), result.get(0).getPassword(), false);
					
		logger.addAppender(new ConsoleAppender(new PatternLayout(),"All  retrieved."));
		tx.commit();			
	} catch (Exception ex) {
		logger.addAppender(new ConsoleAppender(new PatternLayout(),"   $ Error retrieving all clients: " + ex.getMessage()));
    	logger.fatal("   $ Error retrieving all clients: " + ex.getMessage(), ex);
    	
    } finally {
    	if (tx != null && tx.isActive()) {
    		tx.rollback();
    	}
		pm.close(); 
    }
	logger.addAppender(new ConsoleAppender(new PatternLayout(),""+cl.getPassword()));
	
	return cl.getPassword().equals(data[1]);
	
}

private void storeObject(Object object) {
	PersistenceManager pm = pmf.getPersistenceManager();
    Transaction tx = pm.currentTransaction();
    try {
       tx.begin();
       pm.makePersistent(object);
       tx.commit();
    } catch (Exception ex) {
    	logger.addAppender(new ConsoleAppender(new PatternLayout(),"   $ Error storing " + ex.getMessage()));
    	logger.fatal("   $ Error storing  ", ex);
    	
    	System.out.println(ex.getStackTrace());
    } finally {
    	if (tx != null && tx.isActive()) {
    		tx.rollback();
    	}
		pm.close();
    }
}


@Override
public Car getCar(String brand) {
	// TODO Auto-generated method stub
	PersistenceManager pm = pmf.getPersistenceManager();
	pm.getFetchPlan().setMaxFetchDepth(3);

	Transaction tx = pm.currentTransaction();
	Car c = null;


	try {
		
		logger.info("   Looking for cars..: " + brand);


		tx.begin();
		Query<?> query = pm.newQuery("SELECT FROM " + Car.class.getName() + " WHERE brand == '" + brand +"'");
		query.setUnique(true);
		c = (Car) query.execute();
		tx.commit();

	} catch (Exception ex) {
		
		logger.error("   $ ErrorLooking for cars :" + ex.getMessage());

	} finally {
		if (tx != null && tx.isActive()) {
			tx.rollback();
		}

		pm.close();
	}

	return c;
	
}

@Override
public void deleteCar(int mat) {
	// TODO Auto-generated method stub
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();
	
	Car c = null;
	try {
		
		logger.info(" *Delete: " + mat );
		tx.begin();
		Query<?> query = pm.newQuery("SELECT FROM " + Rent.class.getName() + " WHERE mat == " + mat );
		query.setUnique(true);
		c = (Car) query.execute();
		int matnum = mat;
	
		tx.commit();
		tx.begin();

		pm.makePersistent(c);
		tx.commit();
	


	} catch (Exception ex) {
		
		logger.error("   $ Error deleting car:" + ex.getMessage());

	}
}

@Override
public ArrayList<Car> getAllCars() {
	// TODO Auto-generated method stub
	PersistenceManager pm = pmf.getPersistenceManager();
	pm.getFetchPlan().setMaxFetchDepth(3);

	Transaction tx = pm.currentTransaction();
	
	ArrayList<Car> carsList = new ArrayList<Car>();
	
	try {
		
		logger.info("  * List of cars..");
		
		pm = pmf.getPersistenceManager();
		tx = pm.currentTransaction();
		tx.begin();
		

		
		Extent<Car> extent = pm.getExtent(Car.class, true);
		

		for (Car car : extent) {
			carsList.add(new Car(car.getMat(),car.getBrand(),car.getColour(), car.getModel(),car.getType(), car.getAccesories(),car.getPrice()));
			
		}
		
		tx.commit();

	} catch (Exception ex) {
		
		logger.error("   $ Error  " + ex.getMessage());
	
	} finally {
		if (tx != null && tx.isActive()) {
			tx.rollback();
		}

		pm.close();
	}
	
	
	
	return null;
}	

@Override
public void rentCar(String brand) {
	// TODO Auto-generated method stub
	
}

@Override
public Car showCars(String brand) {
	// TODO Auto-generated method stub
	PersistenceManager pm = pmf.getPersistenceManager();
	pm.getFetchPlan().setMaxFetchDepth(3);

	Transaction tx = pm.currentTransaction();
	Car c = null;
	

	try {
		
		logger.info("\"   * Car description: " + brand);


		tx.begin();
		Query<?> query = pm.newQuery("SELECT FROM " + Car.class.getName() + " WHERE brand == '" + brand +"'");
		query.setUnique(true);
		c = (Car) query.execute();


		System.out.println("\nMat: " + c.getMat());
		System.out.println("\nModel: " + c.getModel());
		System.out.println("\nColour: " + c.getColour());
		System.out.println("\nBrand: " + c.getBrand());
		System.out.println("\nType: " + c.getType());
		System.out.println("\nAccesories: " + c.getAccesories());
		System.out.println("\nPrice: " + c.getPrice());
		
		tx.commit();

	} catch (Exception ex) {
		
		logger.error("   $ Error " + ex.getMessage());

	} finally {
		if (tx != null && tx.isActive()) {
			tx.rollback();
		}

		pm.close();
	}
	return c;

}

@Override
public void deleteRent(Rent r) {
	// TODO Auto-generated method stub
	PersistenceManager pm = pmf.getPersistenceManager();
	Transaction tx = pm.currentTransaction();

	try {
		tx.begin();
		
		logger.info("   * Delete rent ");

		pm.deletePersistent(r);
		tx.commit();
	} catch (Exception ex) {
		
		logger.error("	$ Error deleting rent " + ex.getMessage());

	} finally {
		if (tx != null && tx.isActive()) {
			tx.rollback();
		}

		pm.close();
	}
}


@Override
public void updateRent(Rent r) {
	// TODO Auto-generated method stub
	
}

@Override
public ArrayList<Rent> getAllRents() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void storeClient(Client client) {
	// TODO Auto-generated method stub
	this.storeObject(client);
}

@Override
public void storeCar(Car car) {
	// TODO Auto-generated method stub
	logger.addAppender(new ConsoleAppender(new PatternLayout(),"   * Storing an object: " + car.getMat()));
	
	 this.storeObject(car);
}
	


public static void main(String[] args) {

	IDAO dao= new DAO();
			
	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new SecurityManager());
	}
	Car c=new Car(123, "Ford", "Mustang", "turismo",54.56 );
	Car c1=new Car(123, "Ford", "Mustang", "turismo",54.56 );
	
	dao.storeCar(c);
	
	
	
	Client cl=new Client("Janire", "user@j", "123a", false);
	Client cl1=new Client("Jon", "jon@j", "123a", true);

	
	Rent r1=new Rent(001, cl1, c1);
	cl.addRent(r1);
	System.out.println("Store client");
	dao.storeClient(cl);
	dao.storeClient(cl1);
	
	
	//System.out.println("Is correct");
	
	
}


}
	



	