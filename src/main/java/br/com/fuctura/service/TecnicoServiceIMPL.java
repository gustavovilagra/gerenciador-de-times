package br.com.fuctura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fuctura.dto.tecnico.TecnicoDTO;
import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;
import br.com.fuctura.entities.Tecnico;
import br.com.fuctura.repository.TecnicoRepository;
@Service(value = "TecnicoIMPL")
public class TecnicoServiceIMPL implements TecnicoService{
	@Autowired
	private TecnicoRepository repo;

	
	@Override
	public List<TecnicoDTOInterface>listarTecnicosETime() {
	
		return repo.findTecnicoBy();
	}


	@Override
	public void salvar(TecnicoDTO tecnico) {
		var t=new Tecnico();
		t.setNome(tecnico.getNome());
		t.setIdade(tecnico.getIdade());
		t.setTime(tecnico.getTime());
		
		repo.save(t);
		
		
	}
	@Override
	public void deletar(String nome) {
		repo.deleteAll(repo.findByNome(nome));
	}


	@Override
	public void update(TecnicoDTO t,String nome) {
		
		Optional<Tecnico>tecn=repo.findTecnicoByNome(nome);
		
		Tecnico tecnico=tecn.get();
		tecnico.setNome(t.getNome());
		tecnico.setIdade(t.getIdade());
		
		repo.save(tecnico);
		
	}
}
