package br.com.fuctura.dto.jogador;

import br.com.fuctura.entities.Time;

public interface JogadorDTOInterface {
	Long getId();
	String getNome();
	int getIdade();
	Double getPeso();
	Double getAltura();
	Double getImc();
	Time getTime();
	String getMensagem();
	
	
	
}
