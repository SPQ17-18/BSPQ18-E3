package es.deusto.server.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Car implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	private int mat;
	private String colour;	
	private String brand;
	private String model;
	private String type;
	private String accesories;
	private double price;

	
	public Car(int mat, String colour, String model, String type, String brand, String accesories,double price){
		this.mat = mat;
		this.colour = colour;
		this.model = model;
		this.type = type;
		this.accesories = accesories;
		this.brand = brand;
		this.price=price;
		
	}
	
	public Car(int mat, String brand, String model, String type, double price) {
		this.mat=mat;
		this.brand=brand;
		this.model=model;		
		this.type=type;
		this.price=price;
	}
	

	
	List<Rent> rents = new ArrayList<Rent>();
	
	List<Client> clients = new ArrayList<Client>();
		
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
	
	
	
	public String getAccesories() {
		return accesories;
	}

	public void setAccesories(String accesories) {
		this.accesories = accesories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public double  getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		 return "Car: mat --> " + this.mat + ", brand -->  " + this.brand + ",  model -->  " + this.model + ", type --> "+ this.type + " colour -->  " + this.colour + ", accesories --> "+ this.accesories;
	}
	
	public void addRent(Rent rent){
		rents.add(rent);
	}
	public void removeRent(Rent rent){
		rents.remove(rent);
	}
	public List<Rent> getRents(){
		return this.rents;
	}
	
	public void addClient(Client client){
		clients.add(client);
	}
	public void removeClient(Client client){
		clients.remove(client);
	}
	public List<Client> getClients(){
		return this.clients;
	}
}