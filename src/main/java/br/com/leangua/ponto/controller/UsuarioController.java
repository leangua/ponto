package br.com.leangua.ponto.controller;

import java.util.Optional;

import javax.validation.Valid;

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

import br.com.leangua.ponto.controller.form.UsuarioForm;
import br.com.leangua.ponto.dto.UsuarioDto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.UsuarioRepository;
import br.com.leangua.ponto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
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
	public Usuario cadastrar(@Valid @RequestBody UsuarioForm form) {
		return usuarioService.criar(form);
	}
	
	@GetMapping("/{id}")
	public UsuarioDto busca(@PathVariable String id){
		Optional<Usuario> optional = usuarioService.buscar(id);
		
		if (!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = optional.get();
		
		return new UsuarioDto(usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getDataDeCadastro());
	}
	
	@PutMapping("/{id}/atualizar")
	public void atualizar(@PathVariable String id, @Valid @RequestBody UsuarioForm usuario) {
		Optional<Usuario> optional = usuarioService.buscar(id);

		if (!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		usuarioService.atualizar(id, usuario);
	}
	
}
