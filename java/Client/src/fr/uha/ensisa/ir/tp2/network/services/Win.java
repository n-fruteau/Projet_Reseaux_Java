package fr.uha.ensisa.ir.tp2.network.services;
import java.net.*;
import java.io.*;

import fr.uha.ensisa.ir.tp2.network.communication.Reader;
import fr.uha.ensisa.ir.tp2.network.communication.Writer;
 
public class Win {
 
    private Socket socket = null;
    public boolean state;
     
    public Win(Socket s, boolean win){
        socket = s;
        state = false;
        
        try {
            
            Writer myWriter = new Writer(socket.getOutputStream());
            Reader myReader = new Reader(socket.getInputStream());
            
            char discr;
            if(win) discr='W'; else discr='L';
            myWriter.writeDiscr(discr);
            
            if(myReader.readServer())
            {
            	System.out.println("Battle");
            	state = true;
            }
            else
            {
            	System.out.println("Battle failed");
            	state = false;
            }
             
            } catch (IOException e) {
                 
                System.err.println("Le serveur ne répond plus ");
                state = false;
            }
            
    }
     
 
}