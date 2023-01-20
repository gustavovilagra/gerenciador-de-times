
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

import br.com.fuctura.dto.jogador.JogadorDTO;
import br.com.fuctura.dto.jogador.JogadorDTOInterface;
import br.com.fuctura.dto.jogador.JogadorDTOView;
import br.com.fuctura.dto.jogador.JogadorJPQLDTO;
import br.com.fuctura.exception.ObjectExistsException;
import br.com.fuctura.exception.ObjectNotFoundException;
import br.com.fuctura.exception.RequiredParamException;
import br.com.fuctura.service.JogadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = {br.com.fuctura.config.SwaggerConfig.API_JOGADOR})
@RestController
@RequestMapping("/jogador")
public class JogadorController {
	
	@Qualifier("ServiceIMPL")
	@Autowired
	private JogadorService service;

	 
	@ApiOperation("salvar jogadores")
	@ApiResponses({@ApiResponse(code = 200, message = "Requisiçao salva executada com sucesso"),
		@ApiResponse(code = 400,message = "erro na requisiçao"),
	    @ApiResponse(code = 401, message = "Acesso negado"),
	    @ApiResponse(code=403,message="parametros errados ou ja existente")})
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JogadorDTO>  store(
			@Valid @RequestBody  @ApiParam(value = "Objeto a ser criado id,imc e mensagem podem ser ignorado ", required=true) JogadorDTO dto) {
		
		
		try {
			this.service.checkJogador(dto);
			this.service.generateJogador(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
			
		}catch(RequiredParamException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}catch(ObjectExistsException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		}
		
	
	
	@ApiOperation("listar jogadores com nome e seu valor do imc")
	@ApiResponses({@ApiResponse(code = 200, message = "Requisiçao executada com sucesso")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<JogadorDTOView>> IndexNomeImcMsg(){
		List<JogadorDTOView> jogadores=this.service.listarNomeImc();
		if(jogadores.isEmpty()) {
			List<JogadorDTOView>jogadoresView=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.OK).body(jogadoresView);
		}
		return ResponseEntity.status(HttpStatus.OK).body(jogadores);
		
	}
	
	
	@ApiOperation("listar por peso")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="requisiçao executada com sucesso"),
			@ApiResponse(code=400,message="petiçao incorreta")
	})
	@GetMapping(value="/{min}/{max}",produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity <List<JogadorJPQLDTO>> indexJPQL(@PathVariable  @ApiParam(value = "peso minimo", required=true) Double min,
			@PathVariable  @ApiParam(value = "peso maximo", required=true) Double max){
			
			List<JogadorJPQLDTO> resultado=this.service.listarMinMax(min, max);
			if(resultado.isEmpty()) {
				resultado=new ArrayList<>();
				ResponseEntity.status(HttpStatus.OK).body(resultado);
			}
			return ResponseEntity.status(HttpStatus.OK).body(resultado);
	}
	
	
	
	@ApiOperation("datos completos dos jogadores")
	@ApiResponses(value= {
			@ApiResponse(code=200,message="requisiçao executada com sucesso")
	})
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<JogadorDTOInterface>> index(){
		List<JogadorDTOInterface>jogadores=this.service.listarTodos();
		if(jogadores.isEmpty()) {
			jogadores=new ArrayList<>();
			return ResponseEntity.status(HttpStatus.OK).body(jogadores);
		}
		
		 return ResponseEntity.status(HttpStatus.OK).body(jogadores);
		 }
	
	
	
	@ApiOperation("deletar todos os nomes indicado")
	@ApiResponses(value= {
			@ApiResponse(code=204,message="solicitude concluida"),
			@ApiResponse(code= 400,message="erro na requeciçao")
	})
	@DeleteMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<JogadorDTO> deletar(@PathVariable @ApiParam(value = "id do jogador", required=true)Long id) throws ObjectNotFoundException {
		if(id==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		this.service.deletarUsuario(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
		
	}
	
	
	@ApiOperation("atualizar jogador")
		@ApiResponses(value= {
		@ApiResponse(code = 201,message = "requisiçao creada"),
		@ApiResponse(code = 400,message = "erro na requisiçao"),
		@ApiResponse(code = 401, message = "Acesso negado"),
		@ApiResponse(code=403,message="parametros errados ou ja existente")})
	
	@PutMapping(value="/{id}",produces =MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody ResponseEntity<JogadorDTO> update(
			@PathVariable @ApiParam(value="id do jogador",required=true) Long id,
			@RequestBody @ApiParam(value="Objeto a ser atualizado imc e mensagem podem ser ignorados", required=true) JogadorDTO jogador) {
		
		if(null==id || jogador.getId() !=id) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jogador);
		}
		try {
			this.service.checkJogador(jogador);
			
			this.service.update(jogador, id);
			return ResponseEntity.status(HttpStatus.CREATED).body(jogador);
			
		}catch(RequiredParamException | ObjectNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jogador);
			
		}
		
		
		
	}
}