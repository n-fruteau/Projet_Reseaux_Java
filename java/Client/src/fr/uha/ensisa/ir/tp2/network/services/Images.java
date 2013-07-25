package fr.uha.ensisa.ir.tp2.network.services;

import java.net.*;
import java.io.*;

import fr.uha.ensisa.ir.tp2.network.communication.Reader;
import fr.uha.ensisa.ir.tp2.network.communication.Writer;

 
public class Images {
 
    private Socket socket = null;
    public boolean state;
    private byte[] imageBrute;
    private byte[] imageB1;
    private byte[] imageB2;
    private byte[] imageB3;
     
    public Images(Socket s, boolean imageMe){
        socket = s;
        state = false;
        
        try {
            
            Writer myWriter = new Writer(socket.getOutputStream());
            Reader myReader = new Reader(socket.getInputStream());
            
            char discr;
            if(imageMe) discr='d'; else discr='D';
            
            myWriter.writeDiscr(discr);
            if(myReader.readServer())
            {
            	System.out.println("Image trouvée");
            	
            	//Brute
            	setImageBrute(myReader.readImg());
            	
            	setImageB1(myReader.readImg());
            	
            	setImageB2(myReader.readImg());
            	
            	setImageB3(myReader.readImg());
            	
            	
            	
/*meBrute.setName(myReader.getString());
            	
            	myReader.read();
            	meBrute.setLevel(myReader.getInt());
            	
            	myReader.read();
            	meBrute.setLife(myReader.getInt());
            	
            	myReader.read();
            	meBrute.setStrench(myReader.getInt());
            	
            	myReader.read();
            	meBrute.setSpeed(myReader.getInt());*/
            	state = true;
            }
            else
            {
            	System.out.println("Image indisponible");
            }
             
            } catch (IOException e) {
                 
                System.err.println("Le serveur ne répond plus ");
            }
            
    }

	public byte[] getImageBrute() {
		return imageBrute;
	}

	public void setImageBrute(byte[] imageBrute) {
		this.imageBrute = imageBrute;
	}

	public byte[] getImageB1() {
		return imageB1;
	}

	public void setImageB1(byte[] imageB1) {
		this.imageB1 = imageB1;
	}

	public byte[] getImageB2() {
		return imageB2;
	}

	public void setImageB2(byte[] imageB2) {
		this.imageB2 = imageB2;
	}

	public byte[] getImageB3() {
		return imageB3;
	}

	public void setImageB3(byte[] imageB3) {
		this.imageB3 = imageB3;
	}
 
}