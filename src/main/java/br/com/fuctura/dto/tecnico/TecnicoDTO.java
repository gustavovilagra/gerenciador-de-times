package br.com.fuctura.dto.tecnico;

import br.com.fuctura.entities.Tecnico;
import br.com.fuctura.entities.Time;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class TecnicoDTO {
	private Long id;
	private String nome;
	private int idade;
	private Time time;
	
	public TecnicoDTO(Tecnico t) {
		if(null !=t) {
			this.id=t.getId();
			this.nome=t.getNome();
			this.idade=t.getIdade();
			this.time=t.getTime();
			
		}
	}

}
