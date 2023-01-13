package br.com.fuctura.controller;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.fuctura.dto.time.TimeDTOInterface;
import br.com.fuctura.dto.time.TimeDTOInterface2;
import br.com.fuctura.dto.time.TimeDTONome;
import br.com.fuctura.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TimeController {
	
	@Qualifier("TimeImpl") 
	@Autowired 
	private TimeService service;
	
	@Operation(summary = "listar todos os times")
	@RequestMapping(path = "/time",method = RequestMethod.GET)
	public List<TimeDTOInterface> listar() {
		return service.times();
		}
	@Operation(summary = "listar os jogadores do time selecionado")
	@RequestMapping(path = "/time/jogadores/{id}",method = RequestMethod.GET)
	public List<TimeDTOInterface2> listarJogadores(@PathVariable Long id) {
		return service.jogadoresTimes(id);
	}
	@Operation(summary = "adicionar um time")
	@RequestMapping(path = "/time",method =RequestMethod.POST)
	public void salvar(@Valid @RequestBody TimeDTONome time) {
		
		service.salvar(time);
		
	}
	@Operation(summary = "deletar um time")
	@RequestMapping(path = "/time/{id}",method =RequestMethod.DELETE)
	public void excluir(@PathVariable Long id) {
		
		service.deletar(id);
		
	}

	@Operation(summary = "atualizar nome")
	@RequestMapping(path = "/times/{nome}",method =RequestMethod.PUT )
	public void atualizarNome(@PathVariable String nome,@RequestBody TimeDTONome t ) {
		service.update(t, nome);
}
	
}
