
package br.com.fuctura.entities.controller;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.fuctura.dto.JogadorDTO;
import br.com.fuctura.dto.JogadorDTOInterface;
import br.com.fuctura.dto.JogadorDTOView;
import br.com.fuctura.dto.JogadorJPQLDTO;
import br.com.fuctura.service.JogadorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/jogador")
public class JogadorController {
	
	@Qualifier("ServiceIMPL")
	@Autowired
	private JogadorService service;

	 
	@ApiOperation("salvar jogadores")
	@ApiResponses({@ApiResponse(code = 200, message = "Requisiçao salva executada com sucesso"),
	    @ApiResponse(code = 401, message = "Acesso negado"),
	    @ApiResponse(code = 400,message = "erro na requisiçao")})
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void imcSalvar(@Valid @RequestBody JogadorDTO jogador) { //@Valid serve para validar meu codigo na hora de inserir as informaçoes @NotNull,@Max() etc..
		
		service.salvar(jogador);
	}
	
	@ApiOperation("lista todos/nome/imc")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JogadorDTOView> listarNomeImc(){
		return service.listarNomeImc();
	}
	
	@ApiOperation("listar por peso")
	@GetMapping(value="/{min}/{max}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<JogadorJPQLDTO> jogadorJPQL(@PathVariable Double min,@PathVariable Double max){
		return service.listarImcNome(min,max);
	}
	
	@ApiOperation("datos completos dos jogadores")
	@GetMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JogadorDTOInterface> datosJogador(){
		 return service.listarTodos();
		 }
	
	@ApiOperation("deletar todos os nomes indicado")
	@DeleteMapping(value="/{nome}",produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletar(@PathVariable String nome) {
		service.deletarUsuario(nome);
		
		
	}
	@Operation(summary = "atualizar jogador")
	@PostMapping(value="/{nome}",produces =MediaType.APPLICATION_JSON_VALUE )
	public void atualizar(@PathVariable("nome") String nome,@RequestBody JogadorDTO jogador) {
		service.update(jogador, nome);
	}
}