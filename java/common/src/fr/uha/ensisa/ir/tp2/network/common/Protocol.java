package fr.uha.ensisa.ir.tp2.network.common;

public interface Protocol {

	static final public int MICROFTP_PORT_ID = 5678;

	public static final Object EXIT_TEXT = "exit";

	static final public char JOIN		= 'j';
	static final public char WIN		= 'W';
	static final public char LOOSE		= 'L';
	static final public char GET_ME		= 'b';
	static final public char GET_STATE	= 'S';

	static final public char GET_OTHER	= 'B';

	static final public char GET_LOGIN  = 'C';
	
	static final public char GET_IMAGE_ME  = 'd';
	static final public char GET_IMAGE_OTHER  = 'D';
	
	static final public char QUIT	= 'q';





	
}
