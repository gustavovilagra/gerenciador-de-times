package br.com.fuctura.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.fuctura.dto.JogadorDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tb_jogador")
public class Jogador implements Serializable{
	
	
	//private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name ="not_id")
	private Long id;
	@Column(name = "not_nome",unique = true)
	private String nome;
	@Column(name = "not_idade")
	private int idade;
	@Column(name = "not_peso")
	private Double peso;
	@Column(name= "not_altura")
	private Double altura;

	@Column(name="not_imc")
	private Double imc;

	@Column(name = "not_mensagem")
	private String mensagem;
	
	@JoinColumn(name="not_time",nullable = false)
	@JsonIgnore
	@ManyToOne(fetch =FetchType.LAZY)
	private Time time;
	
	

	
	public JogadorDTO toDto() {
		return new JogadorDTO(this);
		
	}
	

}
