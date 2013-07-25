package fr.uha.ensisa.ir.tp2.network.server;

import java.net.ServerSocket;
import java.net.Socket;

import fr.uha.ensisa.ir.tp2.network.common.Protocol;


public class Main {

	private ServerSocket server = null;
	
	public void Server () {
		try {
			server = new ServerSocket (Protocol.MICROFTP_PORT_ID);
			while (true) {
				Socket connection = server.accept();
				Service service = new Service(connection);
				service.start ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Main m = new Main ();
		m.Server();
		
	}

}
