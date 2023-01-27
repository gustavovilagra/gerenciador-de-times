package br.com.fuctura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fuctura.dto.estadio.EstadioDto;
import br.com.fuctura.entities.Estadio;

public interface EstadioRepository extends JpaRepository<Estadio, Long>{
	
	public List<EstadioDto> findBy();
	
	public Optional<EstadioDto> findEstadioByNome (String nome);
	
	

	

}
