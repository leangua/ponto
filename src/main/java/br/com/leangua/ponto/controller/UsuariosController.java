package br.com.leangua.ponto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.leangua.ponto.controller.form.UsuarioForm;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.UsuarioRepository;
import br.com.leangua.ponto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/listar")
	public Iterable<Usuario> lista(){		
		return usuarioRepository.findAll();
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario cadastrar(@RequestBody UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = form.converter();
		return usuarioRepository.save(usuario);
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> busca(@PathVariable Long id){
		Optional<Usuario> usuarios = usuarioService.buscar(id);
		return usuarios;
	}
	
	@PutMapping("/{id}/atualizar")
	public void atualizar(@PathVariable Long id, @RequestBody UsuarioForm usuario) {
		Optional<Usuario> optional = usuarioService.buscar(id);

		if (!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		usuarioService.atualizar(id, usuario);
	}

	
}
