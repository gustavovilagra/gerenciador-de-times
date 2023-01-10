package br.com.fuctura.exception;

public class NomeInvalidoException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public NomeInvalidoException() {
		super("Erro na hora de digitar o nome");
	}

}
