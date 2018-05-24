package es.deusto.server;




import es.deusto.server.remote.IRemote;
import es.deusto.server.remote.Remote;

import java.rmi.Naming;
import org.apache.log4j.Logger;

public class Server{

	private static  final Logger logger = Logger.getLogger(Server.class);
	
	public static void main(String[] args) {
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			
			
			IRemote server = new Remote();
			
			Naming.rebind(name, server);
			
			logger.info("[S] Server '" + name + "' active and waiting...");
			System.out.println("servidor arrancado");
			
			java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader ( System.in );
			java.io.BufferedReader stdin = new java.io.BufferedReader ( inputStreamReader );
			
			String line  = stdin.readLine();
		} catch (Exception e) {
		
			logger.error("- Error in server" + e.getMessage());

			e.printStackTrace();
		}
	}
}
