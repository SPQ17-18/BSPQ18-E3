package es.deusto.server.data;


import java.io.Serializable;

import javax.jdo.annotations.*;

/**ajjjj
 * SOLO TIENE LOS ATRIBUTOS BASICOS ID, CLIENTE Y COCHE.
 * FALTA POR IMPLEMENTAR EL SISTEMA DE RESERVAS
 * 
 * */

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
	public Rent( Client client, Car car/**, String comment, double rating*/) {
		super();
		
		this.client = client;
		this.car = car;
		/**this.comment = comment;
		this.rating = rating;*/
	}
	
	public Rent() {
		super();
		// TODO Auto-generated constructor stub
	}

/**
	public Rent( String comment, double rating) {
		super();
		
		this.comment = comment;
		this.rating = rating;
	}

*/
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
	
/**	public String getComment() {
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
*/

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
