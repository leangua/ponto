package br.com.leangua.ponto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.PontoRepository;
import br.com.leangua.ponto.repository.UsuarioRepository;

@Service
public class PontoService {

	@Autowired
	PontoRepository pontoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public void registrar(Ponto ponto) {
	

	}
}
