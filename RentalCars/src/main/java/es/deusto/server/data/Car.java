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
	private boolean accesories;

	
	public Car(int mat, String colour, String model, String type, String brand, boolean accesories){
		this.mat = mat;
		this.colour = colour;
		this.model = model;
		this.type = type;
		this.accesories = true;
		this.brand = brand;
		
	}
	
	public Car(int mat, String brand, String model, String type) {
		this.mat=mat;
		this.brand=brand;
		this.model=model;		
		this.type=type;
	}
	

	
	List<Rent> rents = new ArrayList<Rent>();
	
	List<Client> client = new ArrayList<Client>();
	
	public List<Client> getClient(){
		return this.client;
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
	public boolean accesories(){
		return this.accesories;
	}
	public void setAccesories(boolean accesories){
		this.accesories = accesories;
	}

	public String toString() {
		 return "Car: mat --> " + this.mat + ", brand -->  " + this.brand + ",  model -->  " + this.model + ", type --> "+ this.type + " colour -->  " + this.colour + ", accesories --> "+ this.accesories;
	}
}