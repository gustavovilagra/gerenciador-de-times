package br.com.fuctura.service;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fuctura.dto.IMCrequerimentoDTO;
import br.com.fuctura.dto.JogadorDTO;
import br.com.fuctura.dto.JogadorDTOInterface;
import br.com.fuctura.dto.JogadorDTOView;
import br.com.fuctura.dto.JogadorJPQLDTO;
import br.com.fuctura.entities.Jogador;
import br.com.fuctura.exception.IdadeInvalidoException;
import br.com.fuctura.exception.NomeInvalidoException;
import br.com.fuctura.exception.PesoInvalidoException;
import br.com.fuctura.repository.JogadorRepository;




@Service("ServiceIMPL")
public class JogadorServiceImpl implements JogadorService {
	public static final Logger LOGGER=LoggerFactory.getLogger(JogadorServiceImpl.class);
	
	@Autowired
	private JogadorRepository repo;
	
	@Autowired
	private CalculadoraService calculadora;
	
	private boolean isNumeric(String stcNumber) {
		if(stcNumber==null)return false;
		String number=stcNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
	@Override
	//mapper manual
	public void salvar(JogadorDTO j) {
		
		var req=IMCrequerimentoDTO.builder()
				.peso(j.getPeso())
				.altura(j.getAltura())
				.build();
		
		var respo=calculadora.calculandoIMC(req);
		
		var jogador=new Jogador();
		jogador.setNome(j.getNome());
		jogador.setIdade(j.getIdade());
		jogador.setPeso(j.getPeso());
		jogador.setAltura(j.getAltura());
		jogador.setImc(respo.getImc());
		jogador.setTime(j.getTime());
		

		if(jogador.getImc()>=30) {
			jogador.setMensagem("jogador com obesidade");
		}
		if(jogador.getImc()<29.9&&jogador.getImc()>=25) {
			jogador.setMensagem("jogador com sobrepeso");
		}
		if(jogador.getImc()<24.9&&jogador.getImc()>18.5) {
			jogador.setMensagem("jogador com peso saudavel");
		}
		if(jogador.getImc()<=18.5) {
			jogador.setMensagem("jogador com baixo peso");
		}
		
		
		
		if(isNumeric(jogador.getNome())||jogador.getNome().isBlank()) {
			throw new NomeInvalidoException();
		}
		if(jogador.getPeso()<=0||jogador.getPeso()>200) {
			throw new PesoInvalidoException(); 
		}
		if(jogador.getIdade()<=0||jogador.getIdade()>150) {
			throw new IdadeInvalidoException();
		}
		
	repo.save(jogador);
	
	LOGGER.info("jogador criado com sucesso");
	
	}

	@Override
	public List<JogadorDTOView> listarNomeImc() {
		LOGGER.info("jogadores encontrados");
		return repo.findBy();
		
	}

	@Override
	public List<JogadorDTOInterface> listarTodos() {
		LOGGER.info("lista de todos os jogadores encontrada");
		
		return repo.findJogadorBy();
		
	}

	@Override
	public void deletarUsuario(String nome) {
		LOGGER.info("jogador deletado com sucesso");
		repo.deleteAll(repo.findJogadorsByNome(nome));
		
	}

	@Override
	public List<JogadorJPQLDTO> listarImcNome(Double min, Double max) {
		LOGGER.info("jogadores com seus imc do peso encontrados");
		return repo.listarComJPQL(min,max);
	}

	@Override
	public void update(JogadorDTO j, String nome) {
		
		Optional<Jogador>jogador=repo.findJogadorByNome(nome);

		var req=IMCrequerimentoDTO.builder()
				.peso(j.getPeso())
				.altura(j.getAltura())
				.build();
		
		var respo=calculadora.calculandoIMC(req);
	
		
		
		Jogador jog=jogador.get();
		jog.setNome(j.getNome());
		jog.setAltura(j.getAltura());
		jog.setPeso(j.getPeso());
		jog.setIdade(j.getIdade());
		jog.setImc(respo.getImc());
		jog.setTime(j.getTime());
		
		if(j.getImc()>=30) {
			j.setMensagem("jogador com obesidade");
		}
		if(j.getImc()<29.9&&j.getImc()>=25) {
			j.setMensagem("jogador com sobrepeso");
		}
		if(j.getImc()<24.9&&j.getImc()>18.5) {
			j.setMensagem("jogador com peso saudavel");
		}
		if(j.getImc()<=18.5) {
			j.setMensagem("jogador com baixo peso");
		}
		
		
		
		if(isNumeric(jog.getNome())||jog.getNome().isBlank()) {
			throw new NomeInvalidoException();
		}
		if(jog.getPeso()<=0||jog.getPeso()>200) {
			throw new PesoInvalidoException(); 
		}
		if(jog.getIdade()<=0||jog.getIdade()>150) {
			throw new IdadeInvalidoException();
		}
		

		repo.save(jog);
		
		LOGGER.info("jogador atualizado com sucesso");
		
	}

	
	
	
}
