package br.com.fuctura.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fuctura.dto.tecnico.TecnicoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tecnico")
public class Tecnico{
	
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="nome")
	private String nome;
	@Column(name="idade")
	private int idade;
	
	@JoinColumn(name="time" )
	@JsonIgnore
	@OneToOne(fetch =FetchType.LAZY)
	private Time time;
	
	public TecnicoDTO toDto() {
		return new TecnicoDTO(this);
	
}
}