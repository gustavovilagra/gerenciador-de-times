package br.com.fuctura.exception;

public class ObjectExistsException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public ObjectExistsException(String msg) {
		super(msg);
	}

}
