package br.com.fuctura.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fuctura.dto.time.TimeDTO;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;
import br.com.fuctura.entities.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long>{
	
	
	public List<TimeDTOInterface> findBy();
	
	public List<TimeDTOInterface2> findTimeById(Long id);
	
	
	public Optional<TimeDTONome> findTimeByNome(String nome);
	
	public Optional<Time> findById(Long id);
	
	public List<Time>findByNome(String nome);
	
	
	
	

}
