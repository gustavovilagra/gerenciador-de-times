package br.com.fuctura.controller;


import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fuctura.dto.time.TimeDTO;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.service.TimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {br.com.fuctura.config.SwaggerConfig.API_TIME})
@RestController
@RequestMapping("/time")
public class TimeController {
	
	@Qualifier("TimeImpl") 
	@Autowired 
	private TimeService service;
	
	@ApiOperation("lista dos times existente")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="requisiçao executada com sucesso")
	})
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TimeDTOInterface>> index() {
		List<TimeDTOInterface> resultado=this.service.times();
		if(resultado.isEmpty()) {
			resultado=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.OK).body(resultado); 
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
		}
	
	
	@ApiOperation("datos completos dos jogadores do time selecionado")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="requisiçao executada com sucesso"),
			@ApiResponse(code=404,message="requisiçao nao encontrada ou inexistente")
	})
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TimeDTOInterface2>> listarJogadores(@PathVariable 
			@ApiParam(value="id do time",required=true)Long id) {
		List<TimeDTOInterface2> resultado=this.service.jogadoresTimes(id);
		
		if(resultado.isEmpty()||null==id) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}
	@ApiOperation("salvar um novo time")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="requiciçao salva com sucesso"),
			@ApiResponse(code=403,message="parametros errados ou ja exisitente ")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TimeDTONome> salvar(@Valid @RequestBody TimeDTONome time) {
		
		try {
			this.service.salvar(time);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(time);
		} catch (ObjectExistsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(time);
			

		}
		
	}
	
	@DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public void excluir(@PathVariable Long id) {
		
		service.deletar(id);
		
	}

	
	@PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TimeDTONome> atualizarNome(@PathVariable Long id,@RequestBody TimeDTONome t ) {
		try {
			service.update(t,id);
			return ResponseEntity.status(HttpStatus.OK).body(t);
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
}
	
}
