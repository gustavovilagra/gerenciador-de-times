package br.com.fuctura.service;

import java.util.List;


import org.springframework.stereotype.Service;

import br.com.fuctura.dto.tecnico.TecnicoDTO;
import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.exception.RequiredParamException;
@Service
public interface TecnicoService {
	public List<TecnicoDTOInterface>listarTecnicosETime();
	
	public void salvar(TecnicoDTO tecnico) throws ObjectExistsException, ObjectNotFoundException;
	
	public void deletar(String nome)throws ObjectNotExistsException;
	
	public void update(Long id,TecnicoDTO t)throws ObjectNotExistsException, RequiredParamException, ObjectExistsException;

	


}
