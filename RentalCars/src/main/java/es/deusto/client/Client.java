package es.deusto.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.client.gui.LogIn;
import es.deusto.server.remote.IRemote;

/*Lado cliente
 * */

	public class Client {

		final static  Logger logger = LoggerFactory.getLogger(Client.class);
	
		public static void main(String[] args) {
			if (args.length != 3) {
				logger.info("Use: java [policy] [codebase] Client.Client [host] [port] [server]");
				System.exit(0);
			}
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			LogIn logIn;
			try{
				String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
				IRemote server = (IRemote) java.rmi.Naming.lookup(name);
				//Menu
				//mainMenu(server);
				logIn = new LogIn(server);
			}catch (Exception e) {
				logger.error("RMI Example exception: " + e.getMessage());
				e.printStackTrace();
			}		
		}
	}