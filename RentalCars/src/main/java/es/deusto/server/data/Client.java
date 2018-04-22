package es.deusto.server.data;






import javax.jdo.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;



public class Client implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String DNI;
	private int age;
	private String name;
	private int creditCard;
	private String address;	
	

	public Client(String DNI, int age, String name, int creditCard,String address) {
		
		this.DNI = DNI;
		this.age = age;
		this.name = name;
		this.creditCard = creditCard;
		this.address = address;
		
	}
	
	
	
	List<Rent> reviews = new ArrayList<Rent>();
	
		
	List<Car> cars = new ArrayList<Car>();
	

	public List<Car> getCars(){
		return this.cars;
	}
	
	
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

	public int getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(int creditCard) {
	       this.creditCard = creditCard;
	}

	 public String toString() {
		 return "Client: DNI --> " + this.DNI + ", name -->  " + this.name + ",  age -->  " + this.age + " address --> " + this. age + " creditcard -->"+ this.creditCard;
		
	}
	 
}
	 



	
	

