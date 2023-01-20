package br.com.fuctura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fuctura.dto.tecnico.TecnicoDTO;
import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;
import br.com.fuctura.entities.Tecnico;
import br.com.fuctura.entities.Time;
import br.com.fuctura.exception.IdadeInvalidoException;
import br.com.fuctura.exception.NomeInvalidoException;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.exception.RequiredParamException;
import br.com.fuctura.repository.TecnicoRepository;
import br.com.fuctura.repository.TimeRepository;
@Service(value = "TecnicoIMPL")
public class TecnicoServiceIMPL implements TecnicoService{
	@Autowired
	private TecnicoRepository repo;
	
	@Autowired
	private TimeRepository timeRepo;

	
	@Override
	public List<TecnicoDTOInterface>listarTecnicosETime() {
	
		return this.repo.findBy();
	}


	@Override
	public void salvar(TecnicoDTO t) throws ObjectExistsException, ObjectNotFoundException {
		
	Optional<Tecnico> resposta=this.repo.findTecnicoByNome(t.getNome());
	if(resposta.isPresent()) {
		throw new ObjectExistsException("tecnico ja existente");
	}
	
	Tecnico tecnico=new Tecnico();
	tecnico.setIdade(t.getIdade());
	tecnico.setNome(t.getNome());
	tecnico.setTime(t.getTime());
	
	if(tecnico.getIdade()<=0||tecnico.getIdade()>100) {
		throw new IdadeInvalidoException();
	}
	if(tecnico.getNome().isBlank()) {
		throw new NomeInvalidoException();
	}
	this.repo.save(tecnico);
		
	}
	@Override
	public void deletar(String nome) throws ObjectNotExistsException {
		Optional<Tecnico>tecnico=this.repo.findTecnicoByNome(nome);
		if(!tecnico.isPresent()) {
			throw new ObjectNotExistsException("tecnico inexistente");
		}
		this.repo.delete(tecnico.get());;
		
	}


	@Override
	public void update(Long id,TecnicoDTO t) throws ObjectNotExistsException, RequiredParamException, ObjectExistsException {
		
		Optional<Tecnico>tecn=repo.findById(id);
		if(tecn.isEmpty()) {
			throw new ObjectNotExistsException("tecnico nao encontrado");
		}
		if(t.getNome().isBlank()||t.getIdade()==0) {
			throw new RequiredParamException("parametros incorrectos");
		}
		

		/*if(tecn.get().getTime().getTecnico()!=null) {
			throw new  ObjectExistsException("time ja com tencico");
		}*/
		
		Tecnico tecnico=tecn.get();
		tecnico.setNome(t.getNome());
		tecnico.setIdade(t.getIdade());
		tecnico.setTime(t.getTime());
		
		
		this.repo.save(tecnico);
		
	}
	
}
