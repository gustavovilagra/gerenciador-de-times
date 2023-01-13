package br.com.fuctura.service;

import org.springframework.stereotype.Service;

import br.com.fuctura.dto.jogador.IMCJogadorDTO;
import br.com.fuctura.dto.jogador.IMCrequerimentoDTO;
@Service
public interface CalculadoraService {
	
	public IMCJogadorDTO calculandoIMC(IMCrequerimentoDTO j);
	

}
