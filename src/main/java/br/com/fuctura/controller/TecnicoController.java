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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fuctura.dto.tecnico.TecnicoDTO;
import br.com.fuctura.dto.tecnico.TecnicoDTOInterface;

import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.exception.RequiredParamException;
import br.com.fuctura.service.TecnicoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(tags = {br.com.fuctura.config.SwaggerConfig.API_TECNICO})
@RestController
public class TecnicoController {
	@Qualifier("TecnicoIMPL")
	@Autowired
	private TecnicoService tecServ;
	
	@ApiOperation("salvar um novo tecnico")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="requisiçao creado com sucesso"),
			@ApiResponse(code = 400,message = "erro na requisiçao"),
		    @ApiResponse(code=403,message="parametros errados ou ja existente")})
			
	@PostMapping
	public @ResponseBody ResponseEntity<TecnicoDTO> salvar( @RequestBody TecnicoDTO t) {
		try {
			this.tecServ.salvar(t);
			return ResponseEntity.status(HttpStatus.CREATED).body(t);
		} catch (ObjectExistsException|ObjectNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		
	}
	@ApiOperation("datos completos dos tecnicos do time selecionado")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="requisiçao executada com sucesso"),
			@ApiResponse(code=204,message="sem conteudo")
	})
	@GetMapping
	public @ResponseBody ResponseEntity<List<TecnicoDTOInterface>> listarTecnicos(){
		List<TecnicoDTOInterface> resposta=this.tecServ.listarTecnicosETime();
		if(resposta.isEmpty()) {
			resposta=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	
	@ApiOperation("deletar um tecnico - nome completo")
	@ApiResponses(value= {
			@ApiResponse(code=204,message="objeto deletado com sucesso"),
			@ApiResponse(code=400,message="parametros errados")
	})
	@DeleteMapping(value="/{nome}",produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TecnicoDTO> deletar(@Valid @PathVariable String nome) {
		
		try {
			tecServ.deletar(nome);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (ObjectNotExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
		
	@ApiOperation("atualizar tecnico")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="atualizado com sucesso"),
			@ApiResponse(code=400,message="parametros errados")
	})
	@PutMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<TecnicoDTO> update(@PathVariable @ApiParam(value="id do tecnico",required=true) Long id,
			@RequestBody @ApiParam(value="novos parametros de tecnico",required=true) TecnicoDTO t) {
		
		try {
			this.tecServ.update(id, t);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(t);
		} catch (ObjectNotExistsException | RequiredParamException | ObjectExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
