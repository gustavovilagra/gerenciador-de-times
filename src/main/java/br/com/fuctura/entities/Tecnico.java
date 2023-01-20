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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_tecnico")
public class Tecnico{
	
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="tb_id")
	private Long id;
	@Column(name="tb_nome")
	private String nome;
	@Column(name="tb_idade")
	private int idade;
	
	@JoinColumn(name="tb_time",nullable =false )
	@JsonIgnore
	@OneToOne(fetch =FetchType.LAZY)
	private Time time;
	
	public TecnicoDTO toDto() {
		return new TecnicoDTO(this);
	
}
}