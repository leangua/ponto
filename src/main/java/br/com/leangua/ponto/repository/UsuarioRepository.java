package br.com.leangua.ponto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.leangua.ponto.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findById(Long Id);

}
