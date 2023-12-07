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
@Table(name="estadio")
public class Estadio{
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name ="nome") 
	private String nome;
	@Column(name = "apelido")
	private String apelido;
	@Column(name = "dtContruçao")
	private LocalDate dtContrucao;
	@Column(name = "dtInaguraçao")
	private LocalDate dtInaguracao;
	@Column(name = "capacidade")
	private int capacidade;
	
	@JoinColumn(name="time" )
	@OneToOne(fetch =FetchType.EAGER)
	private Time time;
	
	public EstadioDto dto() {
		return new EstadioDto(this);
	}
}