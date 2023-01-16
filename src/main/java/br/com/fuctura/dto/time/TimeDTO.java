package br.com.fuctura.dto.time;

import java.util.ArrayList;
import java.util.List;


import br.com.fuctura.entities.Jogador;
import br.com.fuctura.entities.Tecnico;
import br.com.fuctura.entities.Time;
import lombok.Data;
@Data
public class TimeDTO {
	private Long id;
	private String nome;
	private List<Jogador>jogadores=new ArrayList<>();
	private Tecnico tecnico;
	
	public TimeDTO(Time time) {
		if(time!=null) {
		time.setId(this.id);	
		time.setNome(this.nome);
		time.setJogadores(this.jogadores);
		time.setTecnico(this.tecnico);
	}
	}

}
