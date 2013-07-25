package fr.uha.ensisa.ir.tp2.network.server;


import java.io.IOException;
import java.net.Socket;

import fr.uha.ensisa.ir.tp2.network.common.Element;
import fr.uha.ensisa.ir.tp2.network.common.MyJdbc;
import fr.uha.ensisa.ir.tp2.network.common.Protocol;
import fr.uha.ensisa.ir.tp2.network.communication.Reader;
import fr.uha.ensisa.ir.tp2.network.communication.Writer;


public class Session {

	private Socket connection;
	private MyJdbc mydb;
	private int idU=-1;
	private int idUB=-1;
	private int idOB=-1;
	
	public Session (Socket connection) {
		this.connection = connection;
		this.mydb = new MyJdbc();
	}



	public boolean discut() throws Exception {
		try {
			Writer writer = new Writer (connection.getOutputStream());
			Reader reader = new Reader ( connection.getInputStream());
			reader.read ();
			
			switch (reader.getType()) {
			
			case Protocol.JOIN :
				writer.writeOk();
				System.out.println("Client Connecté");
				break;
				
			case Protocol.GET_LOGIN : 
				reader.read();
				String user = reader.getString();
				reader.read();
				String mdp = reader.getString();
				System.out.println("user : "+user+" - mdp :"+mdp);
				idU=mydb.existU(user, mdp);
				if(idU >=0){
					writer.writeOk();
					System.out.println("Client "+ idU + " enregistré");
				}
				else writer.writeKo();
				break;
				
			case Protocol.WIN : 
				if(idU>=0 && idUB>=0 && idOB >=0)  writer.writeOk();
				else writer.writeKo();
				break;	
				
			case Protocol.LOOSE :
				if(idU>=0 && idUB>=0 && idOB >=0)  writer.writeOk();
				else writer.writeKo();
				break;
				
			case Protocol.GET_ME : 
				if(idU>=0) {
					Element brute =mydb.searchUB(idU,idUB); //permet de gerer aleatoire sans le mm
					idUB=brute.getId();
				if(idU>=0 && idUB>=0)  {
					writer.writeOk();
					writer.writeB(brute);

					writer.writeBonus(mydb.searchBonus(idUB));
					System.out.println("brute "+ idUB + "de "+ idU + " envoyé");
				}
				else writer.writeKo();
				}
				else writer.writeKo();
				break;
				
			case Protocol.GET_STATE : 
				if(idU>=0)  writer.writeOk();
				else writer.writeKo();
				break;
				
			case Protocol.GET_OTHER : 
				if(idU>=0) {
					Element bruteO =mydb.notMe(idU,idOB); 
					idOB=bruteO.getId();
				if(idU>=0 && idOB>=0)  {
					writer.writeOk();
					writer.writeB(bruteO);
					
					writer.writeBonus(mydb.searchBonus(idOB));
					System.out.println("brute "+ idOB + "de other envoyé");
				}
				else writer.writeKo();
				}
				else writer.writeKo();
				break;
				
			case Protocol.GET_IMAGE_ME : 
				if(idU>=0 && idUB>=0)  {
					writer.writeOk();
					writer.writeImg(mydb.searchBruteImg(idUB));
					writer.writeBonusImg(mydb.searchBonusImg(idUB));

					System.out.println("Images Me envoyé");
				}
				else writer.writeKo();
				break;
				
			case Protocol.GET_IMAGE_OTHER : 
				if(idU>=0 && idOB>=0)  {
					writer.writeOk();
					writer.writeImg(mydb.searchBruteImg(idOB));
					writer.writeBonusImg(mydb.searchBonusImg(idOB));
					System.out.println("Images Other envoyé");
				}
				else writer.writeKo();
				break;
			
			case Protocol.QUIT : 
				if(idU>=0)  {
					writer.writeOk();
					quit();
					
				}
				else writer.writeKo();
				break;


		} 

			
			return false;
		} catch (IOException e) {
			return true;
		}
	}
	
	public void quit() throws Exception {
		System.out.println("Deconnection Client "+ idU);
		connection.close();
		
	}

}



	