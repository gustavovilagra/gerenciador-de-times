package br.com.fuctura.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

import br.com.fuctura.config.SwaggerConfig;
import br.com.fuctura.dto.estadio.EstadioDto;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.service.EstadioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = {SwaggerConfig.API_ESTADIO})
@RestController
@RequestMapping("/estadio")
public class EstadioContoller {
	
	@Autowired
	public EstadioService esService;
	
	
	@ApiOperation("Salvar um estadio")
	@ApiResponses({@ApiResponse(code = 201, message = "Requisiçao salva executada com sucesso"),
		@ApiResponse(code = 400,message = "erro na requisiçao"),
	    @ApiResponse(code = 401, message = "Acesso negado"),
	    @ApiResponse(code=403,message="parametros errados ou ja existente")})
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<EstadioDto> store(@RequestBody @ApiParam(name="estadio",required = true) EstadioDto estadio){
		try {
			this.esService.salvar(estadio);
			return ResponseEntity.status(HttpStatus.CREATED).body(estadio);
		} catch (ObjectNotExistsException | ObjectExistsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		
		
	}

	@ApiOperation("Listar estadios")
	@ApiResponses({@ApiResponse(code = 200, message = "Requisiçao executada com sucesso")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<EstadioDto>> index(){
		
		List<EstadioDto> resposta=this.esService.listar();
		if(resposta.isEmpty()) {
			resposta=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.OK).body(resposta);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	

	@ApiOperation("Deletar estadio")
	@ApiResponses({@ApiResponse(code = 204, message = "Requisiçao deletada com sucesso"),
		@ApiResponse(code = 400,message = "erro na requisiçao")
	    })
	@DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<EstadioDto> delete(@PathVariable Long id){
		try {
			this.esService.excluir(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (ObjectNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
	}
	@ApiOperation("Atualizar estadio")
	@ApiResponses(value= {
			@ApiResponse(code=201,message="objeto atualizado com sucesso"),
			@ApiResponse(code=400,message="parametros errados")
	})
	@PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<EstadioDto>update(@PathVariable @ApiParam(value="id do estadio",required = true) Long id,
			@RequestBody  @ApiParam(value="atualizar estadio",required = true) EstadioDto dto){
		try {
			if(null==id||dto.getId()!=id) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			this.esService.atualizar(id, dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		} catch (ObjectNotExistsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
		}
		
	}
	
	
	
	
	
}
	
	