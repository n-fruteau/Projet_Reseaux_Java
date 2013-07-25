package fr.uha.ensisa.ir.tp2.network.communication;

import java.io.IOException;
import java.io.InputStream;

public class Reader {

	private InputStream inputStream;
	private char type;
	private char c;
	private String s;
	private int i;
	
	public Reader(InputStream inputStream) {
		this.inputStream =inputStream;
		
	}
	
	public boolean readServer() throws IOException{
		byte[] text = new byte[2];
		int read = 0;
		 while(read < 2){
	            inputStream.read(text, read, 1);
	            read++;
	     }
		s=new String(text);
		System.out.println(s);
		
		if(s.equals("OK"))
			return true;
		
		return false;
	}
	
	public int getInt(){
		return i;
	}
	
	public String getString(){
		return s;
	}
	
	public char getChar(){
		return c;
	}

	private int readInt() throws IOException {
		byte[] nb = new byte[4];
		int read = 0;
		 while(read < 4){
			 inputStream.read(nb, read, 1);
	            read++;
	     }
		byte b1=nb[0],b2=nb[1],b3=nb[2],b4=nb[3];
		
		int i = ((0xFF & b1) << 24) | ((0xFF & b2) << 16) | ((0xFF & b3) << 8) | (0xFF & b4);
		this.i=i;
		return i;
			/*int res = 0;
			for (int i=0; i<4; i++) {
				res <<= 8;
				res |= (int)nb[i];
			}
			System.out.println(Integer.reverseBytes(res));
	        return res;*/
	}

	private String readUTF () throws IOException {
		readInt();
		int len = i;
		
		byte[] text = new byte[len];
		if(len!=0)
			inputStream.read(text, 0, len);
		s=new String(text);
		return s;

	}
	public byte[] readImg () throws IOException {
		byte[] d = new byte[1];
		inputStream.read(d, 0, 1);
		
		readInt();
		int len = i;
		
		byte[] text = new byte[len];
		if(len!=0)
			inputStream.read(text, 0, len);
		s=new String(text);
		return text;

	}
	
	private void readChar() throws IOException {
		byte[] d = new byte[1];
		inputStream.read(d, 0, 1);
		char c = (char)d[0];
		this.c=c;
	}
	
	@SuppressWarnings("unused")
	private boolean readBool() throws IOException{
		readInt();
		if(i==1)
			return true;
		return false;
	}	
	
	public void read() throws IOException
	{
		byte[] d = new byte[1];
		inputStream.read(d, 0, 1);
		char c = (char)d[0];
		this.type =c;
		
		switch(c)
		{
		case('t'):
			readUTF();
			break;
		case('i'):
			readInt();
			break;
		case('c'):
			readChar();
			break;
		}
	}


	public char getType() {
		return type;
	}


	
	
}
