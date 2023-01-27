package br.com.fuctura.entities;

import java.time.LocalDate;

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

import br.com.fuctura.dto.estadio.EstadioDto;
import lombok.Data;

@Data
@Entity
@Table(name="es_estadio")
public class Estadio{
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "es_id")
	private Long id;
	@Column(name ="es_nome") 
	private String nome;
	@Column(name = "es_apelido")
	private String apelido;
	@Column(name = "es_dtContruçao")
	private LocalDate dtContrucao;
	@Column(name = "es_dtInaguraçao")
	private LocalDate dtInaguracao;
	@Column(name = "es_capasidade")
	private int capacidade;
	
	@JoinColumn(name="es_time",nullable =false )
	@JsonIgnore
	@OneToOne(fetch =FetchType.EAGER)
	private Time time;
	
	public EstadioDto dto() {
		return new EstadioDto(this);
	}
}