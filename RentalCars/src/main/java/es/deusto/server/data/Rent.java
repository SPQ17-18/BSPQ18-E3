package es.deusto.server.data;


import java.io.Serializable;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable="true")
public class Rent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//@PrimaryKey
	private int id_rent; 
	
	//@Persistent (defaultFetchGroup="true")
	private Client client;
	
	//@Persistent (defaultFetchGroup="true")
	private Car car;
	/**
	private String comment;
	private double rating;
	*/
	public Rent( int id_rent, Client client, Car car) {
		super();
		this.id_rent=id_rent;
		this.client = client;
		this.car = car;
	}
	
	public Rent(int id_rent, Client client) {
		super();
		this.id_rent=id_rent;
		this.client=client;
		// TODO Auto-generated constructor stub
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
	

	public int getid_rent() {
		return id_rent;
	}

	@Override
	public String toString() {
		return "Rent [id_rent=" + id_rent +  ", client=" + client + ", car=" + car + "]";
	}

	public void setid_rent(int id_rent) {
		this.id_rent = id_rent;
	} 
}