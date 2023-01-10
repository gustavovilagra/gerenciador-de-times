package br.com.fuctura.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fuctura.config.ApplicationConfig;
import br.com.fuctura.dto.IMCJogadorDTO;
import br.com.fuctura.dto.IMCrequerimentoDTO;


@Service
public class CalculadoraServiceImpl implements CalculadoraService{
	
	
	@Autowired
	private RestTemplate cliente;
	
	@Autowired
	private ApplicationConfig config;
	
	@Override
	public IMCJogadorDTO calculandoIMC(IMCrequerimentoDTO req) {
		
		var resp=cliente.postForObject(config.getUrlImcCalculadora(),req,IMCJogadorDTO.class);
		return resp;
		
		
	}

}
