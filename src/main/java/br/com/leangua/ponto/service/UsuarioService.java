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
	
	
	public Optional<Usuario> buscar(Long id) {
		return usuarioRepository.findById(id);
	}


	public void atualizar(Long id, UsuarioForm usuarioForm) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		Usuario usuario = usuarioOptional.get();
		
		usuario.setNomeCompleto(usuarioForm.getNome());
		usuario.setCpf(usuarioForm.getCpf());
		usuario.setEmail(usuarioForm.getEmail());
		
		usuarioRepository.save(usuario);
		
	}

}
