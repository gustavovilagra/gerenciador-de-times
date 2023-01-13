package br.com.fuctura.dto.jogador;

import lombok.Data;

@Data
public class JogadorDTOUpdate {
	
	private String nome;
	private int idade;
	private Double peso;
	private Double altura;
	private Double imc;
	private String mensagem;

}
