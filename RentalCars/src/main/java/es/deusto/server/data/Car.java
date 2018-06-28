package es.deusto.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable (detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Car implements Serializable{
	
	// Añadir clients para que salga en la tabla de rents
/*	@Persistent(table="rent")
	@Join(column="mat")
	//@Element(column="car_id")*/
	List<Client> clients= new ArrayList<Client>();
	
	private static final long serialVersionUID = 2L;
	@PrimaryKey
	private int mat;
	private String colour;	
	private String brand;
	private String model;
	private String type;
	private String accesories;
	private double price;
	
	List<Rent> rents;
	


	public Car(int mat, String colour, String brand, String model, String type, String accesories, double price) {
	super();
	this.mat = mat;
	this.colour = colour;
	this.brand = brand;
	this.model = model;
	this.type = type;
	this.accesories = accesories;
	this.price = price;
	this.clients = new ArrayList<Client>();
}


	public int getMat() {
		return mat;
	}



	public void setMat(int mat) {
		this.mat = mat;
	}



	public String getColour() {
		return colour;
	}



	public void setColour(String colour) {
		this.colour = colour;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getAccesories() {
		return accesories;
	}



	public void setAccesories(String accesories) {
		this.accesories = accesories;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}


	public void addRent(Rent rent){
//		rents.
//		add
//		(rent);
	}
	public void removeRent(Rent rent){
//		rents.remove(rent);
	}
	public List<Rent> getRents(){
		return this.rents;
	}
	
	public void addClient(Client client){
		clients.add(client);
	}
	public void removeclient(Client client){
		clients.remove(client);
	}
	public List<Client> getClients(){
		return this.clients;
	}

	
	
	@Override
	public String toString() {
		 return "Car: mat --> " + this.mat + ", brand -->  " + this.brand + ",  model -->  " + this.model + ", type --> "+ this.type + ", colour -->  " + this.colour + ", accesories --> "+ this.accesories;
	}
	
	
	
	
}