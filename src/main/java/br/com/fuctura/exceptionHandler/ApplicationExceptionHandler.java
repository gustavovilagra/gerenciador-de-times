package br.com.fuctura.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.fuctura.dto.jogador.ErroNaApiDTO;
import br.com.fuctura.exception.IdadeInvalidoException;
import br.com.fuctura.exception.NomeInvalidoException;
import br.com.fuctura.exception.PesoInvalidoException;


@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NomeInvalidoException.class)
	public ResponseEntity<ErroNaApiDTO> nomeInvalido(NomeInvalidoException ex){//throw new NomeInvalidoException();
		
		var mns=new ErroNaApiDTO(HttpStatus.BAD_REQUEST,LocalDateTime.now(),"erro na leitura do nome ");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mns);
		
	}
	@ExceptionHandler(PesoInvalidoException.class)//
	public ResponseEntity<ErroNaApiDTO> pesoInvalido(PesoInvalidoException ex){//throw new PesoInvalidoException();
		
		var mns=new ErroNaApiDTO(HttpStatus.BAD_REQUEST,LocalDateTime.now(),"erro na digitaçao do peso");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mns);
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(//@Valid , @NotNull , @Min(),@Max()
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		
		var message=new ErroNaApiDTO(HttpStatus.BAD_REQUEST,LocalDateTime.now(),ex.getMessage()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}

		
	@ExceptionHandler(IdadeInvalidoException.class)
	public ResponseEntity<ErroNaApiDTO> idadeInvalido(IdadeInvalidoException ex){//throw new NomeInvalidoException();
		
		var mns=new ErroNaApiDTO(HttpStatus.BAD_REQUEST,LocalDateTime.now(),"erro de leitura na idade ");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mns);
		
	}

}
