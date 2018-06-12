package es.deusto.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable (detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Client implements Serializable{

	private static final long serialVersionUID = 2L;
	//private String user;
	@PrimaryKey
	private String email;
	private String name;
	private String address;
	private String password;
	//private String age;
	private boolean role;//true:admin ; false:client
	private double money;
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Client(String email, String password, String name, String address, boolean role) {
		
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.role = role;
		this.money=1000;
	}
	public Client(String email, String password, boolean role) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		this.role = role;
		this.money=1000;
	}
	
	@Persistent(defaultFetchGroup="true", mappedBy="client", dependentElement="true")
	@Join
	List<Rent> rents = new ArrayList<Rent>();
	
	@Persistent(defaultFetchGroup="true", dependentElement="true")
	@Join	
	List<Car> cars = new ArrayList<Car>();
	
	public void addRent(Rent rent){
		rents.add(rent);
	}
	public void removeRent(Rent rent){
		rents.remove(rent);
	}
	public List<Rent> getRents(){
		return this.rents;
	}
	
	public void addCar(Car car){
		cars.add(car);
	}
	public void removeCar(Car car){
		cars.remove(car);
	}
	public List<Car> getCars(){
		return this.cars;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
	       this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
	       this.password = password;
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	       this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
	       this.address = address;
	}

	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	 public String toString() {
		 return "Client: email --> " + this.email + ", password -->  " + this.password + ",  User Role -->  " + this.role;
		
        	
	 }
	 
}
	 



	
	

