package es.deusto.server.data;



import java.io.Serializable;

import javax.jdo.annotations.*;





public class Employee implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String password;
	
	public Employee( String user, String password) {

		this.user = user;
		this.password = password;
		
	}
	 
	public Employee() {
		
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addCar (Car car){
		
	}
	public void deleteCar (Car car){
		
	}
	public void available (Car car){
		
	}
}
