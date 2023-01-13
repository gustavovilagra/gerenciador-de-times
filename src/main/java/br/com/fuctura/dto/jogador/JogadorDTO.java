package br.com.fuctura.dto.jogador;



import br.com.fuctura.entities.Jogador;
import br.com.fuctura.entities.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JogadorDTO {
	private Long id;
	private String nome;
	private int idade;
	private Double peso;
	private Double altura;
	private Double imc;
	private String mensagem;
	private Time time;

	
	public JogadorDTO(Jogador jogador) {
		if(null !=jogador) {
			this.nome=jogador.getNome();
			this.idade=jogador.getIdade();
			this.peso=jogador.getPeso();
			this.altura=jogador.getAltura();
			this.imc=jogador.getImc();
			this.mensagem=jogador.getMensagem();
			this.time=jogador.getTime();
		}
	}
}
