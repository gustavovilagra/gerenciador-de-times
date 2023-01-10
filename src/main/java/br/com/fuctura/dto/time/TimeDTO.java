package br.com.fuctura.dto.time;

import java.util.ArrayList;
import java.util.List;


import br.com.fuctura.entities.Jogador;
import br.com.fuctura.entities.Tecnico;
import lombok.Data;
@Data
public class TimeDTO {
	private String nome;
	private List<Jogador>jogadores=new ArrayList<>();
	private Tecnico tecnico;

}
