package es.deusto.server.data;

import java.io.Serializable;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.ForeignKeyAction;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class Rent implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Persistent
	@ForeignKey(name="email", deleteAction=ForeignKeyAction.RESTRICT)
	private String email;
	@ForeignKey(name="mat", deleteAction=ForeignKeyAction.RESTRICT)
	private int mat;
	
	
	public Rent(String email,int mat) {
		this.email=email;
		this.mat=mat;
	}
	
	
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	public int getMat() {
		return mat;
	}


}
