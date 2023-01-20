package br.com.fuctura.service;



import java.util.List;
import org.springframework.stereotype.Service;

import br.com.fuctura.dto.jogador.JogadorDTO;
import br.com.fuctura.dto.jogador.JogadorDTOInterface;
import br.com.fuctura.dto.jogador.JogadorDTOView;
import br.com.fuctura.dto.jogador.JogadorJPQLDTO;
import br.com.fuctura.entities.Jogador;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.exception.RequiredParamException;

@Service
public interface JogadorService {
	
	public void generateJogador(JogadorDTO j)throws ObjectExistsException;
	
	public List<JogadorDTOView> listarNomeImc();
	
	public List<JogadorJPQLDTO> listarMinMax(Double min, Double max);
	
	public List<JogadorDTOInterface> listarTodos();
	
	public void deletarUsuario(Long id)throws ObjectNotFoundException;
	
	public void update(JogadorDTO j,Long id) throws ObjectNotFoundException;
	
	public void checkJogador(JogadorDTO j)throws RequiredParamException;
	
	public boolean isExists(Jogador j);
	
	

	

	
	
	
	

}
