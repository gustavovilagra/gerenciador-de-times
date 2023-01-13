package br.com.fuctura.exception;

public class ObjectNotFoundException extends Exception{

	
	private static final long serialVersionUID = 1L;
	//objeto nao econtrado
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

}
