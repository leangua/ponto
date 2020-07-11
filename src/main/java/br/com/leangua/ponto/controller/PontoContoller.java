package br.com.leangua.ponto.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.leangua.ponto.controller.form.PontoForm;
import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.PontoRepository;
import br.com.leangua.ponto.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios/{idUsuario}/ponto")
public class PontoContoller {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PontoRepository pontoRepository;
	
	@GetMapping("/listar")
	public Iterable<Ponto> listar(@PathVariable Long idUsuario) {
		return pontoRepository.findAllByUsuario_id(idUsuario);
	}
	
	@PostMapping("/registrar")
	public Ponto registrar(@PathVariable Long idUsuario, @RequestBody PontoForm pontoForm) {
		Optional<Usuario> optional = usuarioRepository.findById(idUsuario);
		
		if(!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		Ponto ponto = pontoForm.converter(optional.get()); 
		
		return pontoRepository.save(ponto);
		
	}

}
