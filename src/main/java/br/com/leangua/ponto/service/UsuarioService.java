package br.com.leangua.ponto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.ponto.controller.form.UsuarioForm;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario criar(UsuarioForm usuarioForm) {
		Usuario usuario = new Usuario(usuarioForm.getNome(), usuarioForm.getCpf(), usuarioForm.getEmail());
//		Usuario usuario = usuarioForm.converter();
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> buscar(String id) {
		Long idLong = Long.parseLong(id);
		return usuarioRepository.findById(idLong);
	}
 

	public void atualizar(String id, UsuarioForm usuarioForm) {
		
		Long idLong = Long.parseLong(id);
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idLong);
		
		Usuario usuario = usuarioOptional.get();
		
		usuario.setNomeCompleto(usuarioForm.getNome());
		usuario.setCpf(usuarioForm.getCpf());
		usuario.setEmail(usuarioForm.getEmail());
		
		usuarioRepository.save(usuario);	
	}

}
