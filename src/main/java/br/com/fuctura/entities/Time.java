package br.com.fuctura.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.fuctura.dto.time.TimeDTO;
import lombok.Data;
@Data
@Entity
@Table(name = "time")
public class Time {
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="id")
	private Long id;
    
	@Column(name="nome")
	private String nome;


	@OneToMany(fetch = FetchType.LAZY,mappedBy = "time")
	@JsonIgnore
	private List<Jogador> jogadores=new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "time")
	private Tecnico tecnico;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,mappedBy ="time")
	private  Estadio estadio;
	
	
	public TimeDTO toDto() {
		return new TimeDTO(this);
	}
	
}
