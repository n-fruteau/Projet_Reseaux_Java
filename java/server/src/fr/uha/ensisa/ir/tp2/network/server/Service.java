package fr.uha.ensisa.ir.tp2.network.server;

import java.io.IOException;
import java.net.Socket;

public class Service extends Thread {

	private Socket connection;
	
	public Service(Socket connection) {
		this.connection = connection;
	}

	public void run() {
		Session session = new Session (connection);
		System.out.println("CLient connecté");
		while (true) {
			try {
				if (session.discut()) break;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (connection != null) connection.close();
		} catch (IOException e) {
		}
	}

}
