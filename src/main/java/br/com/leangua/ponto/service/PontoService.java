package br.com.leangua.ponto.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.ponto.dto.PontoDto;
import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.repository.PontoRepository;
import br.com.leangua.ponto.repository.UsuarioRepository;

@Service
public class PontoService {

	@Autowired
	PontoRepository pontoRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	Duration duracao = Duration.ZERO;
	LocalDateTime registroEntrada;
	boolean periodoEmAberto = false;

	public PontoDto somarHorasTrabalhadas(Long idUsuario) {
		
		Iterable<Ponto> registrosPonto = pontoRepository.findAllByUsuario_id(idUsuario);
		
		Iterator<Ponto> iterator = registrosPonto.iterator();
	
		while (iterator.hasNext()) {
			System.out.println("periodo em aberto: " + periodoEmAberto);
			if (!periodoEmAberto) {
				System.out.println("gravando entrada");
				registroEntrada = iterator.next().getDataRegistro();
				periodoEmAberto = true;
			} else  {
				System.out.println("gravando saida");
				duracao = duracao.plus(Duration.between(registroEntrada, iterator.next().getDataRegistro()));
				periodoEmAberto = false;
			}

		}		
		
		System.out.println("Total trabalhado em horas: " + duracao.toHours());
		System.out.println("Total trabalhado em minutos: " + duracao.toMinutes());
		System.out.println("Total trabalhado em milisegundos: " + duracao.toMillis());
		
		return new PontoDto(registrosPonto, duracao.toHours(), duracao.toMinutes());
	}
}
