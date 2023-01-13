package br.com.fuctura.dto.jogador;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ErroNaApiDTO{
	
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yy hh/mm/ss")
	private LocalDateTime tempo;
	private String mensagem;

}
