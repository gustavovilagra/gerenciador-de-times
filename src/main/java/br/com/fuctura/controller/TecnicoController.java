package br.com.fuctura.controller;

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
import io.swagger.annotations.Api;
@Api(tags = {br.com.fuctura.config.SwaggerConfig.API_TECNICO})
@RestController
public class TecnicoController {
	@Qualifier("TecnicoIMPL")
	@Autowired
	private TecnicoService tecServ;
	
	
	@PostMapping
	public void salvar(@Valid @RequestBody TecnicoDTO t) {
		
		tecServ.salvar(t);;
	}
	
	@GetMapping
	public List<TecnicoDTOInterface> listarTecnicos(){
		
		return tecServ.listarTecnicosETime();
	}

	@RequestMapping(path = "/tecnico/{nome}",method = RequestMethod.DELETE)
	public void deletar(@Valid @PathVariable String nome) {
		tecServ.deletar(nome);
	}
	
	@RequestMapping(path = "/tecnico/{nome}",method = RequestMethod.PUT)
	public void deletar(@PathVariable String nome,@RequestBody TecnicoDTO t) {
		tecServ.update(t, nome);
	}
}
