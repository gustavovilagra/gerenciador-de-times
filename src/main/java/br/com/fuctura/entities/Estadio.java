package br.com.fuctura.entities;

import java.time.LocalDate;
import java.util.Objects;


public class Estadio{
		
	private int id;
	private String nome;
	private String apelido;
	private LocalDate dtContrucao;
	private LocalDate dtInaguracao;
	private int capacidade;
	
	public Estadio() {
		
	}

	public Estadio(int id, String nome, String apelido, LocalDate dtContrucao, LocalDate dtInaguracao, int capacidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.dtContrucao = dtContrucao;
		this.dtInaguracao = dtInaguracao;
		this.capacidade = capacidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public LocalDate getDtContrucao() {
		return dtContrucao;
	}

	public void setDtContrucao(LocalDate dtContrucao) {
		this.dtContrucao = dtContrucao;
	}

	public LocalDate getDtInaguracao() {
		return dtInaguracao;
	}

	public void setDtInaguracao(LocalDate dtInaguracao) {
		this.dtInaguracao = dtInaguracao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apelido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estadio other = (Estadio) obj;
		return Objects.equals(apelido, other.apelido);
	}

	@Override
	public String toString() {
		return "Estadio [id=" + id + ", nome=" + nome + ", apelido=" + apelido + ", dtContrucao=" + dtContrucao
				+ ", dtInaguracao=" + dtInaguracao + ", capacidade=" + capacidade + "]";
	}
	
}
