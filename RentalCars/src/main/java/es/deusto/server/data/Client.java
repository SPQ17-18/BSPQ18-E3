package es.deusto.server.data;

import java.io.Serializable;
import java.util.List;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable (detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Client implements Serializable{

	private static final long serialVersionUID = 2L;
	private String user;
	@PrimaryKey
	private String email;
	private String name;
	private String address;
	private String password;
	private String age;
	private boolean admin;
	private float money;
	
	
	@Join
	private List<Car> carList;


	public Client() {
		
	}
	
	
	
	public Client(String user, String email, String name, String address, String password, String age, boolean admin,
			float money, List<Car> carList) {
		super();
		this.user = user;
		this.email = email;
		this.name = name;
		this.address = address;
		this.password = password;
		this.age = age;
		this.admin = admin;
		this.money = money;
		this.carList = carList;
	}

	

	public Client(String user, String email, String name, String address, String password, String age, boolean admin
			) {
		super();
		this.user = user;
		this.email = email;
		this.name = name;
		this.address = address;
		this.password = password;
		this.age = age;
		this.admin = admin;
		
		
	}

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	@Override
	public String toString() {
		return "Client [user=" + user + ", email=" + email + ", name=" + name + ", address=" + address
				+ ", password=" + password + ", age=" + age + ", admin=" + admin + ", money=" + money
				+ ", listCars=" + "]";
	}



	public void copyClient(Client cli) {
		// TODO Auto-generated method stub
		this.user = cli.user;
		this.email = cli.email;
		this.name = cli.name;
		this.address = cli.address;
		this.password = cli.password;
		this.age = cli.age;
		this.admin = cli.admin;
		this.money = cli.money;
	}

	
	
}
