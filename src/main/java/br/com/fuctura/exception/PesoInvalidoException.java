package br.com.fuctura.exception;

public class PesoInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PesoInvalidoException() {
		super("usuario inserio um numero incorreto");
	}
	

}
