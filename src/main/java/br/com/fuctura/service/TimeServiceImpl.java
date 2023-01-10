package br.com.fuctura.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;
import br.com.fuctura.entities.Time;
import br.com.fuctura.repository.TimeRepository;


@Service(value = "TimeImpl")
public class TimeServiceImpl implements TimeService{
	
	@Autowired
	private TimeRepository timeRepo;
	
	

	@Override
	public List<TimeDTOInterface>times() {
		
		return timeRepo.findBy();
	}

	@Override
	public void salvar(TimeDTONome time) {
		Time t=new Time();
		t.setNome(time.getNome());
		
		timeRepo.save(t);
		
	}

	@Override
	public void deletar(Long id) {
		
		timeRepo.deleteById(id);
		
	}
	
	

	@Override
	public List<TimeDTOInterface2> jogadoresTimes(Long id) {
		
		
		
		return timeRepo.findTimeById(id);
	}

	@Override
	public void update(TimeDTONome t,String nome) {
		Optional<Time> time=timeRepo.findTimeByNome(nome);
		Time tim=time.get();
		tim.setNome(t.getNome());
		
		timeRepo.save(tim);
		
		}
	

}
