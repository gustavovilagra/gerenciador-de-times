package br.com.fuctura.dto.estadio;

import java.time.LocalDate;

import br.com.fuctura.entities.Estadio;
import br.com.fuctura.entities.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EstadioDto {
	
	private Long id;
	
	private String nome;
	
	private String apelido;
	
	private LocalDate dtContrucao;
	
	private LocalDate dtInaguracao;
	
	private int capacidade;
	
	private Time time;
	
	public EstadioDto(Estadio estadio) {
		this.id=estadio.getId();
		this.nome=estadio.getNome();
		this.apelido=estadio.getApelido();
		this.dtContrucao=estadio.getDtContrucao();
		this.dtInaguracao=estadio.getDtInaguracao();
		this.capacidade=estadio.getCapacidade();
		this.time=estadio.getTime();
	}
	

}
