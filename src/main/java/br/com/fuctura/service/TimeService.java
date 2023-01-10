package br.com.fuctura.service;

import java.util.List;

import org.springframework.stereotype.Service;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;


@Service
public interface TimeService {
	
	public List<TimeDTOInterface> times();
	
	public List<TimeDTOInterface2> jogadoresTimes(Long id);
	
	public void salvar(TimeDTONome time);
	
	public void deletar(Long id);
	
	public void update(TimeDTONome time,String nome);
}
