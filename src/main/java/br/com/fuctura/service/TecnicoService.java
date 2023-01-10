package br.com.fuctura.service;

import java.util.List;


import org.springframework.stereotype.Service;

import br.com.fuctura.dto.tecnico.TecnicoDTO;
import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;
@Service
public interface TecnicoService {
	public List<TecnicoDTOInterface>listarTecnicosETime();
	
	public void salvar(TecnicoDTO tecnico);
	
	public void deletar(String nome);
	
	public void update(TecnicoDTO t,String nome);

	


}
