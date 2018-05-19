package es.deusto.server.data;



import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.transaction.Transaction;

public class Main{

public static List<Client> listaC (){
		
		Client cl = new Client("Janire", "janire@j", "pass", false);
		Client cl1 = new Client("Gorka", "gorka@g", "pass", false);
		Client cl2 = new Client("Jon", "jon@j", "pass", true);
		Client cl3 = new Client("Nacho", "nacho@n", "pass", false);
		Client cl4 = new Client("Javier", "javier@j", "pass", true);
		
		List<Client> c = new ArrayList<>();
	
		c.add(cl);
		c.add(cl1);
		c.add(cl2);
		c.add(cl3);
		c.add(cl4);
		
		return c;
	}
	
	public static void main(String[] args) {
		
		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();
	
			Transaction tx = (Transaction) pm.currentTransaction();
//INSERTA LOS USUARIOS DE UNO EN UNO EN LA BASE DE DATOS
			try {				
				Client cl = new Client("Janire", "janire@j", "pass", false);
				Client cl1 = new Client("Gorka", "gorka@g", "pass", false);
				Client cl2 = new Client("Jon", "jon@j", "pass", true);
				Client cl3 = new Client("Nacho", "nacho@n", "pass", false);
				Client cl4 = new Client("Javier", "javier@j", "pass", true);
				((javax.jdo.Transaction) tx).begin();
				pm.makePersistent(cl);
				pm.makePersistent(cl1);
				pm.makePersistent(cl2);
				pm.makePersistent(cl3);
				pm.makePersistent(cl4);
				System.out.println("Inserting contents into the database ....");
				tx.commit();
			} catch (Exception ex) {
				System.out.println("# Error storing objects: " + ex.getMessage());				
			} finally {
				if (((javax.jdo.Transaction) tx).isActive()) {
					tx.rollback();
				}
				
				pm.close();
			}	
//ELIMINA UN UNICO USUARIO DE LA BASE DE DATOS			
			try {
				//Get the Persistence Manager
				pm = pmf.getPersistenceManager();
				//Obtain the current transaction
				tx = (Transaction) pm.currentTransaction();		
				//Start the transaction
				((javax.jdo.Transaction) tx).begin();

				Query<Client> qu = pm.newQuery(Client.class);
				qu.setFilter("email == 'javier@j'");
				qu.deletePersistentAll();
				System.out.println("Eliminando de la base de datos");
				//End the transaction
				tx.commit();
			} catch (Exception ex) {
				System.err.println(" $ Error al eliminar de la base de datos: " + ex.getMessage());
			} finally {
				if (tx != null && ((javax.jdo.Transaction) tx).isActive()) {
					tx.rollback();
				}
				
				if (pm != null && !pm.isClosed()) {
					pm.close();
				}
			}
			
			
//SELECCIONA UN UNICO USUARIO DE LA BASE DE DATOS				
			try {
				//Get the Persistence Manager
				pm = pmf.getPersistenceManager();
				//Obtain the current transaction
				tx = (Transaction) pm.currentTransaction();		
				//Start the transaction
				((javax.jdo.Transaction) tx).begin();

				Query<Client> qu = pm.newQuery(Client.class);
				qu.setFilter("email == 'jon@j.es'");
				System.out.println("El usuario seleccionado es:"+qu.execute().toString());
				//End the transaction
				tx.commit();
			} catch (Exception ex) {
				System.err.println(" $ Error al seleccionar de la base de datos: " + ex.getMessage());
			} finally {
				if (tx != null && ((javax.jdo.Transaction) tx).isActive()) {
					tx.rollback();
				}
				
				if (pm != null && !pm.isClosed()) {
					pm.close();
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			}
				
	}
}
