package es.deusto.server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;  

import java.rmi.RemoteException;

import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;  
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;  
import org.mockito.junit.MockitoJUnitRunner; 

import org.junit.Before;
import org.junit.Test;
//import org.junit.Ignore;


import es.deusto.server.dao.IUserDAO;
import es.deusto.server.data.Message;
import es.deusto.server.data.User;
import es.deusto.server.remote.Messenger;

/**
 * 
 * @author cortazar
 * Testing of the Service Layer, mocking the DAO layer
 */
@RunWith(MockitoJUnitRunner.class)  
public class DAOMockTest {
	
	Messenger m;
	
	@Mock
	IUserDAO dao;
		
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(DAOMockTest.class);
	}

	@Before
	public void setUp() throws Exception {		
		m = new Messenger(dao);

	}

	@Test
	//@Ignore
	public void testRegisterUserCorrectly() {
	
		// Stubbing - return a given value when a specific method is called
		when( dao.retrieveUser("cortazar") ).thenReturn( null );
		m.registerUser("cortazar", "cortazar");
		
		//Use ArgumentCaptor to capture argument values for further assertions.
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		
		// Setting expectations -  the method storeUser() is called once and the argument is intercepted
		verify (dao).storeUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		System.out.println("Registering mock new user: " + newUser.getLogin());
	
		assertEquals( "cortazar", newUser.getLogin());
		
	}
	
	@Test
	public void testRegisterUserAlreadyExists() {
		User u = new User("cortazar","cortazar");
		
		when( dao.retrieveUser("cortazar") ).thenReturn(u);
		// When the user exist, we update the password
		m.registerUser("cortazar", "dipina");
		
		
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		System.out.println("Changing password of mock user: " + newUser.getPassword());
		assertEquals( "dipina", newUser.getPassword());
		
	}

	@Test(expected=RemoteException.class)
	public void testSayMessageUserInvalid() throws RemoteException {
		
		when( dao.retrieveUser("cortazar") ).thenReturn( null );
		System.out.println("Say message and invalid user, testing exception");
		
		m.sayMessage("cortazar", "cortazar", "testing message");
			
	}
	
	@Test
	public void testSayMessageUserValid() throws RemoteException {
		// Setting up the test data
		User u = new User("cortazar","cortazar");
		Message mes = new Message("testing message");
		mes.setUser(u);
		u.addMessage(mes) ;
		
		//Stubbing
		when( dao.retrieveUser("cortazar") ).thenReturn(u);
		
		//Calling the method under test
		
		m.sayMessage("cortazar", "cortazar", "testing message");
		
		// Verifying the outcome
		ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass( User.class );
		verify (dao).updateUser(userCaptor.capture());
		User newUser = userCaptor.getValue();
		
		assertEquals( "cortazar", newUser.getMessages().get(0).getUser().getLogin());
		
	}

}
