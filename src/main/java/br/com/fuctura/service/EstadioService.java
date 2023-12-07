package br.com.fuctura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fuctura.dto.estadio.EstadioDto;
import br.com.fuctura.entities.Estadio;
import br.com.fuctura.entities.Time;
import br.com.fuctura.exception.NomeInvalidoException;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.repository.EstadioRepository;
import br.com.fuctura.repository.TimeRepository;

@Service
public class EstadioService {
	@Autowired
	private EstadioRepository repo;
	
	@Autowired
	private TimeRepository timeRepo;
	
	
	@Transactional(rollbackFor = Exception.class)
	public void salvar(EstadioDto es) throws ObjectNotExistsException, ObjectExistsException {
		
		Estadio estadio=new Estadio();
		estadio.setNome(es.getNome());
		estadio.setApelido(es.getApelido());
		estadio.setDtContrucao(es.getDtContrucao());
		estadio.setDtInaguracao(es.getDtInaguracao());
		
		Optional<Time> resultado=this.timeRepo.findById(es.getTime().getId());
		if(resultado.isEmpty()) {
			throw new ObjectNotExistsException("Time nao existe");
		}
	   
		if(resultado.get().getEstadio()!=null) {
			throw new ObjectExistsException("estadio ja cadastrado");
		}  
		
		
		estadio.setTime(es.getTime());
		this.repo.save(estadio);
	}
	@Transactional(readOnly = true)
	public List<EstadioDto> listar(){
		return this.repo.findBy();
	}
	@Transactional
	public void excluir(Long id) throws ObjectNotFoundException {
		Optional<Estadio>resultado=this.repo.findById(id);
		if(resultado.isEmpty()) {
			throw new ObjectNotFoundException("requisiçao nao encontrada");
		}
		this.repo.delete(resultado.get());
	}
	@Transactional(rollbackFor = Exception.class)
	public void atualizar(Long id,EstadioDto dto) throws ObjectNotExistsException {
		Optional<Estadio> resultado=this.repo.findById(id);
		
		if(resultado.isEmpty()) {
			throw new ObjectNotExistsException("id nao encontrado");
		}
		if(dto.getApelido().isBlank()||dto.getNome().isBlank()) {
			throw new NomeInvalidoException();
		}
		
		resultado.get().setApelido(dto.getApelido());
		resultado.get().setNome(dto.getNome());
		resultado.get().setDtContrucao(dto.getDtContrucao());
		resultado.get().setDtInaguracao(dto.getDtInaguracao());
		resultado.get().setTime(dto.getTime());
		resultado.get().setCapacidade(dto.getCapacidade());
		
		this.repo.save(resultado.get());
	}
}
