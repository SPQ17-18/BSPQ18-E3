package es.deusto.server.data;

import javax.jdo.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;


@PersistenceCapable
public class Client implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String DNI;
	private int age;
	private String name;
	private double money=0;
	private String address;	
	private String email;
	private String password;
	private boolean role;		//true --> admin
 	//false --> user
	

	public Client(String DNI, int age, String name, double money,String address, String email, String password, boolean role) {
		
		this.DNI = DNI;
		this.age = age;
		this.name = name;
		this.money = money;
		this.address = address;
		this.email = email;
		this.password = password;
		this.role=role;
		
	}
	
	public Client(String name, String email, String password, boolean role) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.money=200;
		
	}
	
	
	
	List<Rent> rents = new ArrayList<Rent>();
	
		
	List<Car> cars = new ArrayList<Car>();
	

	
	
	public String getDNI() {
		return DNI;
	}
	
	public void setDNI(String DNI) {
	       this.DNI = DNI;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge( int age){
		this.age = age;
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
	
	public boolean getRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	 public String toString() {
		 return "Client: email --> " + this.email + ", password -->  " + this.password + ",  User Role -->  " + this.role;
		
        	
	 }
	 
	 public void addRent(Rent rent){
		 this.rents.add(rent);
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
	 
	 
}
	 



	
	