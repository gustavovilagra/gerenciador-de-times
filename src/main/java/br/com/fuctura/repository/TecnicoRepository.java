package br.com.fuctura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;
import br.com.fuctura.entities.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long>{
	public List<TecnicoDTOInterface>findTecnicoBy();
	
	public List<Tecnico>findByNome(String nome);
	
	public Optional<Tecnico>findTecnicoByNome(String nome);
}
