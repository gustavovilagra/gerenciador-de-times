package br.com.fuctura.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fuctura.dto.jogador.JogadorDTOInterface;
import br.com.fuctura.dto.jogador.JogadorDTOView;
import br.com.fuctura.dto.jogador.JogadorJPQLDTO;
import br.com.fuctura.entities.Jogador;
@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
	
	public List<JogadorDTOInterface> findJogadorBy();
	
	public List<JogadorDTOView> findBy();
	
	//quando for utilizar uma projecao com interface tem que utilizar os metodos findby,find...by.. etc
	public Optional<Jogador> findJogadorByNome(String nome);
	
	
	public List<Jogador> findJogadorsByNome(String nome);
	
	//public List<Jogador> findDistinctJogadorsByNome(String nome);
	
	@Query(value = "SELECT new br.com.fuctura.dto.JogadorJPQLDTO (J.nome,J.imc) FROM Jogador J WHERE peso BETWEEN :min AND :max ")
	public List<JogadorJPQLDTO> listarComJPQL(@Param(value = "min")Double minimo,@Param(value = "max")Double maximo);
	
	
	//ao utilizar uma consulta com projecao de classe utiliza JPQL ou consulta externa


	
	
}
