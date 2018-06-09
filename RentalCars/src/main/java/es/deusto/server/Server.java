
package es.deusto.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.deusto.server.dao.CarDAO;
import es.deusto.server.dao.ClientDAO;
import es.deusto.server.dao.RentDAO;
import es.deusto.server.data.Car;
import es.deusto.server.data.Client;
import es.deusto.server.data.Rent;
import es.deusto.server.remote.CarsRemote;
import es.deusto.server.remote.ICarsRemote;

public class Server {
	private static final long serialVersionUID = 1L;
	
	
	private static ClientDAO clientDAO;
	private static CarDAO carDAO;
	private static RentDAO rentDAO;
	
	private static final Logger logger = Logger.getLogger(Server.class);

	public Server () throws RemoteException{
		super();
		rentDAO=new RentDAO();
		clientDAO=new ClientDAO();
		carDAO= new CarDAO();
		List<Client> listClients=new ArrayList<>();
		List<Car> listCars = new ArrayList<>();
		
			
      
        Car c1 = new Car(123, "Seat", "Red" , "Altea", "turismo", "gps", 20.65);
        Car c2 = new Car(124, "Seat", "Blue" , "Altea", "deportivo", "gps", 30.80);
        Car c3 = new Car(125, "Ford", "Red" , "Focus", "turismo", "gpsll", 45.74);
        Car c4 = new Car(126, "Brand2", "White" , "Model2", "turismo", "gps", 75.65);
        Car c5 = new Car(127, "Seat", "Yellow" , "Altea", "deportivo", "gps", 56.32);
       
        
        carDAO.storeCar(c1);
        carDAO.storeCar(c2);
        carDAO.storeCar(c3);
        carDAO.storeCar(c4);
        carDAO.storeCar(c5);
        
        listCars.add(c2);
        listCars.add(c4);
        
               
        Client cl1=new Client("janireu", "janire@deusto.es", "Janire", "address1", "spq", "20", false);
		Client cl2 = new Client("spq", "spq@deusto.es", "spq", "adress2", "spq", "20", false);
		
		clientDAO.storeClient(cl1);
		clientDAO.storeClient(cl2);
		listClients.add(cl1);
		
		rentDAO.storeRent(new Rent("spq@deusto.es",3));
		rentDAO.storeRent(new Rent("spq@deusto.es",5));
	
		
        
	}

		
	public synchronized boolean registrarClient(String user, String email, String name, String address, String password,
			String age, boolean admin) throws RemoteException {
		Client cli = new Client (user, email, name, address, password, age, admin);
		return clientDAO.storeClient(cli);
	}
	
	
	public synchronized boolean registerClient(String user, String email, String name, String address, String password,
			String age, float money, boolean admin) throws RemoteException {
				Client cli = new Client (user, email, name, address, password, age, admin);
				cli.setMoney(money);
				
				return clientDAO.storeClient(cli);
	}
	
	
	public synchronized boolean clientRegistrado (String user, String password) throws RemoteException {
        return clientDAO.loginClient(user, password);
	}
	
	
	public synchronized boolean Rent(Rent rent) throws RemoteException {
       return rentDAO.storeRent(rent);
	}
	
	public synchronized List<Car> getRent(String email) throws RemoteException {
		List<Rent> codigosaux=rentDAO.getRent(email);
		List<String> codigos=new ArrayList<String>();

		for(int p=0;p<codigosaux.size();p++) {
		if((codigosaux.get(p).getEmail()).equals(email)) {
			codigos.add(Integer.toString(codigosaux.get(p).getMat()));
			}
		}
		logger.info ("  codigos.size(): " + codigos.size());
		List<Car> a=new ArrayList<Car>();
		List<Car> aux=new ArrayList<Car>();
		a=carDAO.getCars("","","");
		logger.info (" a.size()=: " + a.size());
		for(int j=0;j<a.size();j++) {
			for(int i=0;i<codigos.size();i++) {
			if(Integer.toString(a.get(j).getMat()).equals(codigos.get(i))) {
				aux.add(a.get(j));
				break;
				}
			}
		}
		return aux;
	}
	

	public synchronized List<Car> Search(String brand, String model, String type) {
		logger.info("NOMBRE="+brand+" anyo="+model+" genero="+type);

		List<Car> a=new ArrayList<Car>();
		List<Car> aux=new ArrayList<Car>();
		a=carDAO.getCars(brand,model,type);
		String brand1=brand.toLowerCase();
		String model1=model.toLowerCase();
		String type1=type.toLowerCase();
		for(int j=0;j<a.size();j++) {
			String br=a.get(j).getBrand().toLowerCase();
			String mo=""+a.get(j).getModel();
			String ty=a.get(j).getType().toLowerCase();
			if(br.contains(brand1) && model1.equals(mo) && type1.equals(ty)) {
				aux.add(a.get(j));
			}
		}
		return aux;
	}
	
	
	public Client returnClient(String email) throws RemoteException {
		return clientDAO.getClient(email);
	}

	
	public void updateClient(Client cli) throws RemoteException {
		clientDAO.updateClient(cli);
	}

	
	public List<String> Model() throws RemoteException {
		return carDAO.Model();
	}


	
	public List<String> Brand() throws RemoteException {
		return carDAO.Brand();
	}
	
	public void deleteClient(Client cli) throws RemoteException {
		clientDAO.deleteClient(cli);
	}
	
	
	public boolean checkClient(Client cli) throws RemoteException {
		return clientDAO.checkClient(cli);
	}
	
	
	public static void main(String[] args) {

		
	if (args.length != 3) {
		System.exit(0);
	}

	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new SecurityManager());
	}
	
	String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

	
	System.setProperty("java.security.policy", "target\\classes\\security\\java.policy");


	try {
		ICarsRemote objServer = new CarsRemote();
		Naming.rebind(name, objServer);	
		logger.info("Server '" + name + "' active and waiting...");
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(inputStreamReader);
        @SuppressWarnings("unused")
		String line = stdin.readLine();
       
       
	} catch (RemoteException re) {
		logger.debug(" # Collector RemoteException: " + re.getMessage());
		re.printStackTrace();
		System.exit(-1);
	} catch (MalformedURLException murle) {
		logger.debug(" # Collector MalformedURLException: " + murle.getMessage());
		murle.printStackTrace();
		System.exit(-1);
	} catch (IOException e) {
		e.printStackTrace();
	}

}


}