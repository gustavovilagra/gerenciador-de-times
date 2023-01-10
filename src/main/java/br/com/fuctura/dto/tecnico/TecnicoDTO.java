package br.com.fuctura.dto.tecnico;

import br.com.fuctura.entities.Time;
import lombok.Data;
@Data
public class TecnicoDTO {
	
	private String nome;
	private int idade;
	private Time time;

}
