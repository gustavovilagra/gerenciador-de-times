package br.com.fuctura.service;

import org.springframework.stereotype.Service;

import br.com.fuctura.dto.IMCJogadorDTO;
import br.com.fuctura.dto.IMCrequerimentoDTO;
@Service
public interface CalculadoraService {
	
	public IMCJogadorDTO calculandoIMC(IMCrequerimentoDTO j);
	

}
