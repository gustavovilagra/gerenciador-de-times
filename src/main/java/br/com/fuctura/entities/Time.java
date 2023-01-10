package br.com.fuctura.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "tb_time")
public class Time implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="not_id")
	private Long id;
    
	@Column(name="not_nome")
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "time")
	@JsonIgnore
	private List<Jogador> jogadores=new ArrayList<>();
	
	@JsonIgnore
	@JoinColumn(name="not_tecnico",nullable = false)
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "time")
	private Tecnico tecnico;
	
}
