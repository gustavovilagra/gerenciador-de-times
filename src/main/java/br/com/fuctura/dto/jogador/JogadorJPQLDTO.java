package br.com.fuctura.dto.jogador;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JogadorJPQLDTO {//projecoes com classe
	
	private String nome;
	private Double imc;
	
	public JogadorJPQLDTO(String nome,Double imc) {
		this.nome=nome;
		this.imc=imc;
	}

}
