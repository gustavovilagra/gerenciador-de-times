package br.com.fuctura.service;



import java.util.List;
import org.springframework.stereotype.Service;
import br.com.fuctura.dto.JogadorDTO;
import br.com.fuctura.dto.JogadorDTOInterface;
import br.com.fuctura.dto.JogadorDTOView;
import br.com.fuctura.dto.JogadorJPQLDTO;

@Service
public interface JogadorService {
	
	public  void salvar(JogadorDTO j);
	
	public List<JogadorDTOView> listarNomeImc();
	
	public List<JogadorJPQLDTO> listarImcNome(Double min, Double max);
	
	public List<JogadorDTOInterface> listarTodos();
	
	public void deletarUsuario(String nome);
	
	public void update(JogadorDTO j,String nome);

	

	
	
	
	

}
