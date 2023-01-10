package br.com.fuctura.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "tb_tecnico")
public class Tecnico implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="tb_id")
	private int id;
	@Column(name="tb_nome")
	private String nome;
	@Column(name="tb_idade")
	private int idade;
	
	@JoinColumn(name="tb_time",nullable =false )
	@JsonIgnore
	@OneToOne(fetch =FetchType.LAZY)
	private Time time;
}