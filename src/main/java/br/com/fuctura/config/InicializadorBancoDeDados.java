package br.com.fuctura.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.fuctura.dto.jogador.IMCJogadorDTO;
import br.com.fuctura.dto.jogador.IMCrequerimentoDTO;
import br.com.fuctura.entities.Estadio;
import br.com.fuctura.entities.Jogador;
import br.com.fuctura.entities.Tecnico;
import br.com.fuctura.entities.Time;
import br.com.fuctura.repository.EstadioRepository;
import br.com.fuctura.repository.JogadorRepository;
import br.com.fuctura.repository.TecnicoRepository;
import br.com.fuctura.repository.TimeRepository;
@Component
public class InicializadorBancoDeDados implements CommandLineRunner{
	@Autowired
	private JogadorRepository jr;
	
	@Autowired
	private TimeRepository timeRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private TecnicoRepository tecnoRepo;
	
	@Autowired
	private EstadioRepository esRepo;
	

	
	public void imcSalvar(Jogador jogador) {
		var req=IMCrequerimentoDTO.builder()
				.peso(jogador.getPeso())
				.altura(jogador.getAltura())
				.build();
		
		var resp=restTemplate.postForObject("http://localhost:8085/calculadora/imc",req,IMCJogadorDTO.class);
		
		jogador.setImc(resp.getImc());

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
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Time talleres=new Time();
		talleres.setNome("Talleres");
		
		Tecnico tecnico1=new Tecnico();
		tecnico1.setNome("Maradona");
		tecnico1.setIdade(58);
		tecnico1.setTime(talleres);
		
		
		
		
		Time belgrano=new Time();
		belgrano.setNome("belgrano");
		
	
		
		Tecnico tecnico2=new Tecnico();
		tecnico2.setNome("mascherano");
		tecnico2.setIdade(56);
		tecnico2.setTime(belgrano);
		
		
		LocalDate localDateC = LocalDate. of(2016, 8, 19);
		LocalDate localdateI=LocalDate.of(2022, 12, 01);
		
		
		Estadio estadio=new Estadio();
		estadio.setNome("estadio mario alberto kempes");
		estadio.setApelido("albiazul");
		estadio.setDtContrucao(localDateC);
		estadio.setDtInaguracao(localdateI);
		estadio.setCapacidade(20000);
		estadio.setTime(belgrano);
	
		
		
		
		Jogador j1=new Jogador();
		j1.setNome("gustavo");
		j1.setIdade(32);
		j1.setPeso(83.4);
		j1.setAltura(1.72);
		imcSalvar(j1);
		j1.setTime(talleres);
		
		
		Jogador j2=new Jogador();
		j2.setNome("melina vidal");
		j2.setIdade(27);
		j2.setPeso(57.4);
		j2.setAltura(1.67);
		imcSalvar(j2);
		j2.setTime(belgrano);
		
		
	
		Jogador j3=new Jogador();
		j3.setNome("facundo");
		j3.setIdade(34);
		j3.setPeso(55.4);
		j3.setAltura(1.70);
		imcSalvar(j3);
		j3.setTime(talleres);
		
		Jogador j4=new Jogador();
		j4.setNome("felipe");
		j4.setIdade(22);
		j4.setPeso(80.4);
		j4.setAltura(1.80);
		imcSalvar(j4);
		j4.setTime(belgrano);
		
		Jogador j5=new Jogador();
		j5.setNome("romario");
		j5.setIdade(38);
		j5.setPeso(70.4);
		j5.setAltura(1.60);
		imcSalvar(j5);
		j5.setTime(talleres);
		
		Jogador j6=new Jogador();
		j6.setNome("miguel");
		j6.setIdade(32);
		j6.setPeso(83.4);
		j6.setAltura(1.55);
		imcSalvar(j6);
		j6.setTime(belgrano);
		
		talleres.getJogadores().addAll(Arrays.asList(j1,j3,j5));
		belgrano.getJogadores().addAll(Arrays.asList(j2,j4,j6));

		
		
		timeRepo.save(talleres);
		timeRepo.save(belgrano);
		jr.save(j1);
		jr.save(j2);
		jr.save(j3);
		jr.save(j4);
		jr.save(j5);
		jr.save(j6);
		
		tecnoRepo.save(tecnico1);
		tecnoRepo.save(tecnico2);
		
		this.esRepo.save(estadio);
		
		
		
	}

}
