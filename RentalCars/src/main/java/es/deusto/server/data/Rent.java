package es.deusto.server.data;

import java.io.Serializable;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;



@PersistenceCapable (detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Rent implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@PrimaryKey
	private int id_rent;
	
	
	private int mat;
	/*private String accesories;
	private String brand;
	private String colour;	
	private String model;
	private String type;
	private double price;
	*/
	private String email;
	/*private double money;
*/
	
	/*@Persistent (defaultFetchGroup="true")
	private Client client;
	
	@Persistent (defaultFetchGroup="true")
	private Car car;*/
	
	/*private String comment;
	private double rating;*/
	
	
	/*public Rent( int id_rent,Client client, Car car, String comment, double rating) {
		super();
		
		this.client = client;
		this.car = car;
		this.comment = comment;
		this.rating = rating;
	}*/
	 

	
	public Rent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Rent(int id_rent, int mat, String email/*, int mat, String accesories, String brand, String colour, String model, String type,
			double price, String email, double money*/) {
		super();
		this.id_rent = id_rent;
		this.mat = mat;
		/*this.accesories = accesories;
		this.brand = brand;
		this.colour = colour;
		this.model = model;
		this.type = type;
		this.price = price;*/
		this.email = email;
		//this.money = money;
	}





public int getId_rent() {
		return id_rent;
	}


	public void setId_rent(int id_rent) {
		this.id_rent = id_rent;
	}


	public int getMat() {
		return mat;
	}


	public void setMat(int mat) {
		this.mat = mat;
	}

/*
	public String getAccesories() {
		return accesories;
	}


	public void setAccesories(String accesories) {
		this.accesories = accesories;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColour() {
		return colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
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


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

*/
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

/*
	public double getMoney() {
		return money;
	}


	public void setMoney(double money) {
		this.money = money;
	}


	/*
	public Rent(String comment, double rating) {
		super();
		
		this.comment = comment;
		this.rating = rating;
	}



	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}



	public int getId_rent() {
		return id_rent;
	}
	public void setId_rent(int id_rent) {
		this.id_rent=id_rent;
	}


	*/
	//@Override
	/*public String toString() {
		return "Rent [id_rent=" + id_rent +  " Car: mat --> \" + this.mat + \", brand -->  \" + this.brand + \",  model -->  \" + this.model + \", type --> \"+ this.type + \", colour -->  \" + this.colour + \", accesories --> \"+ this.accesories +"
				+ ", Client=" + email +  ",Money=" + money +"]";
	}

*/


}
