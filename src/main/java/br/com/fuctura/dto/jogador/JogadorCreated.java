package br.com.fuctura.dto.jogador;

import br.com.fuctura.entities.Jogador;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class JogadorCreated {
	
	private String nome;
	private int idade;
	private Double peso;
	private Double altura;
	private Long time;

	public JogadorCreated() {}
	
	public JogadorCreated(Jogador jogador) {
		if(null !=jogador) {
			this.nome=jogador.getNome();
			this.idade=jogador.getIdade();
			this.peso=jogador.getPeso();
			this.altura=jogador.getAltura();
			this.time=jogador.getTime().getId();
		}
	}

}
