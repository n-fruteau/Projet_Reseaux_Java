import java.io.*; 
import java.net.*;
import java.util.Scanner;

public class Client
{
	
	private static PrintWriter out = null;
	
	public static void main (String argv[]) {
		try {
			Socket socket = new Socket ("localhost", 5678);
			System.out.println("-- Connexion au serveur --");
			InputStream input = socket.getInputStream ();
			out = new PrintWriter(socket.getOutputStream());
			byte [] buffer = new byte [256];
			int length = input.read (buffer, 0, 256);
			System.out.print (">> "); 
			System.out.write (buffer, 0, length);
			out.println("Salut");
            out.flush();
			
			socket.close (); 
		}
		catch (Exception e) {
			e.printStackTrace(); }
		}
}