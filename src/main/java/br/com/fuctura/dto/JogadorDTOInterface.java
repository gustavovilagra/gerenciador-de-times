package br.com.fuctura.dto;

import br.com.fuctura.entities.Time;

public interface JogadorDTOInterface {
	String getNome();
	int getIdade();
	Double getPeso();
	Double getAltura();
	Double getImc();
	String getMensagem();
	Time getTime();
	

}
