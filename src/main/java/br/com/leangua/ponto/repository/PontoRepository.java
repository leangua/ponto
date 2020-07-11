package br.com.leangua.ponto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.leangua.ponto.model.Ponto;

public interface PontoRepository extends CrudRepository<Ponto, Long>{


	Iterable<Ponto> findAllByUsuario_id(Long idUsuario);
	
}
