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
			@ApiResponse(code=200,message="requisiçao executada com sucesso"),
			@ApiResponse(code=204,message="sem conteudo")
	})
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TimeDTOInterface>> index() {
		List<TimeDTOInterface> resultado=this.service.times();
		if(resultado.isEmpty()) {
			resultado=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		}
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
		}
	
	
	@ApiOperation("datos completos dos jogadores do time selecionado")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="requisiçao executada com sucesso"),
			@ApiResponse(code=204,message="sem conteudo")
	})
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TimeDTOInterface2>> indexJogTime(@PathVariable 
			@ApiParam(value="id do time",required=true)Long id) {
		
		List<TimeDTOInterface2> resultado=this.service.jogadoresTimes(id);
		 if(resultado.isEmpty()) {
			resultado=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}
	@ApiOperation("salvar um novo time")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="requiciçao salva com sucesso"),
			@ApiResponse(code=403,message="parametros errados ou ja exisitente ")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TimeDTONome> store(@Valid @RequestBody TimeDTONome time) {
		
		try {
			this.service.salvar(time);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(time);
		} catch (ObjectExistsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			

		}
		
	}
	@ApiOperation("excluir um time")
	@ApiResponses(value= {
			@ApiResponse(code=204,message="Solicitação atendida com sucesso sem corpo"),
			@ApiResponse(code=400,message="erro no parametro")
	})
	@DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TimeDTO>  deletar(@PathVariable  @ApiParam(value = "Id do time", required=true) Long id) {
			if(id==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
			this.service.deletar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		
		
	}

	@ApiOperation("atualizar um time")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="requisiçao atualizada com sucesso"),
			@ApiResponse(code=400,message="erro na requisiçao"),
			@ApiResponse(code=403,message="parametros errados ou ja existentes")
	})
	@PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TimeDTONome> update(@PathVariable @ApiParam(value = "Id do time", required=true) Long id,
			@RequestBody @ApiParam(value = "nome do time", required=true) TimeDTONome t ) {
		
		try {
			service.update(t,id);
			return ResponseEntity.status(HttpStatus.CREATED).body(t);
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
}
	
}
