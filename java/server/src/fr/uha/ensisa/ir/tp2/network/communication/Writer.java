package fr.uha.ensisa.ir.tp2.network.communication;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import fr.uha.ensisa.ir.tp2.network.common.Element;
import fr.uha.ensisa.ir.tp2.network.common.MyJdbc;


public class Writer {

	private OutputStream outputStream;
	@SuppressWarnings("unused")
	private ByteArrayOutputStream baos = new ByteArrayOutputStream ();
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

	@SuppressWarnings("unused")
	private void writeLong(long v) throws IOException {
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
	
	private void writeDiscr(char c) {
		try {
			byte[] d = new byte[1];
			d[0]= (byte) c;
			outputStream.write(d, 0, 1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	@SuppressWarnings("unused")
	private void writeBool(boolean a) throws IOException {
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
	
	public void writeOk() throws IOException{
			/*byte[] d = new byte[1];
			d[0]= (byte) 'O';
			outputStream.write(d, 0, 1);
			
			byte[] e = new byte[1];
			e[0]= (byte) 'K';
			outputStream.write(e, 0, 1);*/
			byte[] returnstatus = new byte[2];
			returnstatus[0]= (byte) 'O';
			returnstatus[1]= (byte) 'K';
			outputStream.write(returnstatus,0,2);
	}
	
	public void writeKo() throws IOException{
		/*byte[] d = new byte[1];
		d[0]= (byte) 'K';
		outputStream.write(d, 0, 1);
		
		byte[] e = new byte[1];
		e[0]= (byte) 'O';
		outputStream.write(e, 0, 1);*/
		
		byte[] returnstatus = new byte[2];
		returnstatus[0]= (byte) 'K';
		returnstatus[1]= (byte) 'O';
		outputStream.write(returnstatus, 0, 2);
	}

	public void writeB(Element elt) throws SQLException, IOException{ 
	 
		if(elt!=null){
			writeUTF( elt.getName());
			writeInt( elt.getLevel());
			writeInt( elt.getLife());
			writeInt( elt.getStrench());
			writeInt( elt.getSpeed());

		}
	}
		
	public void writeBonus(ArrayList<Element> array) throws SQLException, IOException{
		Iterator<Element> itr = array.iterator();
	      while(itr.hasNext()) {
	         writeB(itr.next());
	      }
	}
	
	public void writeImg(byte[] img) throws IOException{
		writeDiscr('t');
		if(img!=null){
			int len =img.length;
			byte[] nb = new byte[4];
			
			int j=0;
			for (int i = 3; i >= 0; i--) {
			    nb[i] = (byte)(len >>> (j * 8));
			    j++;
			}
			outputStream.write(nb, 0, 4);
	    	outputStream.write(img, 0, len);
			
		}
		else{
			int len =0;
			byte[] nb = new byte[4];
			
			int j=0;
			for (int i = 3; i >= 0; i--) {
			    nb[i] = (byte)(len >>> (j * 8));
			    j++;
			}
			outputStream.write(nb, 0, 4);
		}
	}
	
	public void writeBonusImg(ArrayList<byte[]> array) throws SQLException, IOException{
		Iterator<byte[]> itr = array.iterator();
	      while(itr.hasNext()) {
	         writeImg(itr.next());
	      }
	}


}
