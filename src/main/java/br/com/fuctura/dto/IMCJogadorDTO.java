package br.com.fuctura.dto;


import lombok.Data;

@Data
public class IMCJogadorDTO {
	private String nome;
	private Double peso;
	private Double altura;
	private Double imc;
	private String mensagem;
}
