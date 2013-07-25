package fr.uha.ensisa.ir.tp2.network.communication;

import java.io.IOException;
import java.io.OutputStream;



import fr.uha.ensisa.ir.tp2.network.common.MyJdbc;


public class Writer {

	private OutputStream outputStream;
	MyJdbc mydb= new MyJdbc();
	
	public Writer(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void writeInt(int v) throws IOException {
			writeDiscr('i');
			byte[] nb = new byte[4];
			
			int j=0;
			for (int i = 3; i >= 0; i--) {
			    nb[i] = (byte)(v >>> (j * 8));
			    j++;
			}
			outputStream.write(nb, 0, 4);
	}	

	public void writeLong(long v) throws IOException {
		writeDiscr('i');
		byte[] nb = new byte[8];
		
		int j=0;
		for (int i = 7; i >= 0; i--) {
		    nb[i] = (byte)(v >>> (j * 8));
		    j++;
		}
		outputStream.write(nb, 0, 8);
	}	

	public void writeUTF(String v) throws IOException {

			writeDiscr('t');
			
			int len = v.length();
			
			byte[] nb = new byte[4];
			
			int j=0;
			for (int i = 3; i >= 0; i--) {
			    nb[i] = (byte)(len >>> (j * 8));
			    j++;
			}
			outputStream.write(nb, 0, 4);
			
			byte msg[]= new byte[len];
	        msg = v.getBytes();
	    	outputStream.write(msg, 0, len);

	}	
	
	public void writeChar(char a) {
		try {
			writeDiscr('c');
			
			byte[] d = new byte[1];
			d[0]= (byte) a;
			outputStream.write(d, 0, 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void writeDiscr(char c) {
		try {
			byte[] d = new byte[1];
			d[0]= (byte) c;
			outputStream.write(d, 0, 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public void writeBool(boolean a) throws IOException {
		if(a==true) {
			byte[] nb = new byte[4];
			int v= 1;
			int j=0;
			for (int i = 3; i >= 0; i--) {
			    nb[i] = (byte)(v >>> (j * 8));
			    j++;
			}
			outputStream.write(nb, 0, 4);
		}
		else {
			byte[] nb = new byte[4];
			int v= 0;
			int j=0;
			for (int i = 3; i >= 0; i--) {
			    nb[i] = (byte)(v >>> (j * 8));
			    j++;
			}
			outputStream.write(nb, 0, 4);
		}
	}	


}

