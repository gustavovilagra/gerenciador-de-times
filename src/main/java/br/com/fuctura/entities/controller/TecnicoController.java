package br.com.fuctura.entities.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.fuctura.dto.tecnico.TecnicoDTO;
import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;
import br.com.fuctura.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TecnicoController {
	@Qualifier("TecnicoIMPL")
	@Autowired
	private TecnicoService tecServ;
	
	@Operation(summary = "salvar um tecnico")
	@PostMapping
	public void salvar(@Valid @RequestBody TecnicoDTO t) {
		
		tecServ.salvar(t);;
	}
	@Operation(summary = "informação geral dos tecnicos")
	@GetMapping
	public List<TecnicoDTOInterface> listarTecnicos(){
		
		return tecServ.listarTecnicosETime();
	}
	@Operation(summary = "deletar tecnico")
	@RequestMapping(path = "/tecnico/{nome}",method = RequestMethod.DELETE)
	public void deletar(@Valid @PathVariable String nome) {
		tecServ.deletar(nome);
	}
	@Operation(summary = "atualizar tecnico")
	@RequestMapping(path = "/tecnico/{nome}",method = RequestMethod.PUT)
	public void deletar(@PathVariable String nome,@RequestBody TecnicoDTO t) {
		tecServ.update(t, nome);
	}
}
