package br.com.fuctura.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fuctura.dto.time.TimeDTO;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;
import br.com.fuctura.entities.Time;
import br.com.fuctura.exception.NomeInvalidoException;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
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
	public void salvar(TimeDTONome time) throws ObjectExistsException {
		
		Time newTime=new Time();
		newTime.setNome(time.getNome());
		
		if(this.isExists(newTime.getNome())) {
			throw new ObjectExistsException("o time ja existe");
		}
		
		this.timeRepo.save(newTime);
		
	}

	@Override
	public void deletar(Long id) {
		Optional<Time> resultado=this.timeRepo.findById(id);
		if(resultado.isPresent()){
			this.timeRepo.delete(resultado.get());
		}
	}
	
	

	@Override
	public List<TimeDTOInterface2> jogadoresTimes(Long id) {
		
		
		
		return timeRepo.findTimeById(id);
	}

	@Override
	public void update(TimeDTONome t,Long id) throws ObjectNotFoundException {
		Optional<Time> time=this.timeRepo.findById(id);
		if(time.isEmpty()||id<=0||id==null) {
			throw new ObjectNotFoundException("objeto vacio");
		}
		if(t.getNome().isBlank()||this.isExists(t.getNome())) {
			throw new NomeInvalidoException();
		}
		time.get().setNome(t.getNome());
		
		this.timeRepo.save(time.get());
		
	}
	
	public boolean isExists(String nome) {
		return !this.timeRepo.findByNome(nome).isEmpty();
	}
		
	

}
