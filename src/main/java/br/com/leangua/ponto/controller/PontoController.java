package br.com.leangua.ponto.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.leangua.ponto.controller.form.PontoForm;
import br.com.leangua.ponto.dto.PontoDto;
import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.PontoRepository;
import br.com.leangua.ponto.service.PontoService;
import br.com.leangua.ponto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios/{idUsuario}/ponto")
public class PontoController {
	
	@Autowired
	UsuarioService usuarioRepository;
	
	@Autowired
	PontoRepository pontoRepository;
	
	@Autowired
	PontoService pontoService; 
	 
	@GetMapping("/listar")
	public PontoDto listar(@PathVariable Long idUsuario) {	
		return pontoService.somarHorasTrabalhadas(idUsuario);
	} 
	 
	@PostMapping("/registrar")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ponto registrar(@PathVariable String idUsuario, @Valid @RequestBody PontoForm pontoForm) {
		Optional<Usuario> optional = usuarioRepository.buscar(idUsuario);
		
		if(!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		pontoService.validaRegistro(idUsuario, pontoForm);
		
		Ponto ponto = pontoForm.converter(optional.get()); 
		
		return pontoRepository.save(ponto);
		
	}

}
