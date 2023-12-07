package br.com.fuctura.entities;




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

import br.com.fuctura.dto.jogador.JogadorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="jogador")
public class Jogador {
	
	
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name ="id")
	private Long id;
	@Column(name = "nome",unique = true)
	private String nome;
	@Column(name = "idade")
	private int idade;
	@Column(name = "peso")
	private Double peso;
	@Column(name= "not_altura")
	private Double altura;

	@Column(name="imc")
	private Double imc;

	@Column(name = "mensagem")
	private String mensagem;
	
	@JoinColumn(name="time",nullable = false)
	@JsonIgnore
	@ManyToOne(fetch =FetchType.LAZY)
	private Time time;
	

	
	public JogadorDTO toDto() {
		return new JogadorDTO(this);
		
	}
	

}
