package br.com.leangua.ponto.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.leangua.ponto.controller.form.PontoForm;
import br.com.leangua.ponto.dto.PontoDto;
import br.com.leangua.ponto.exception.ValidacaoException;
import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.TipoBatidaPonto;
import br.com.leangua.ponto.repository.PontoRepository;
import br.com.leangua.ponto.repository.UsuarioRepository;

@Service
public class PontoService {

	@Autowired
	PontoRepository pontoRepository;
	 
	@Autowired
	UsuarioRepository usuarioRepository;
	

	public PontoDto somarHorasTrabalhadas(Long idUsuario) {
		
		Duration duracao;
		LocalDateTime registroEntrada = null;
		boolean periodoEmAberto;	
		
		Iterable<Ponto> registrosPonto = pontoRepository.findAllByUsuario_id(idUsuario);
		
		Iterator<Ponto> iterator = registrosPonto.iterator();
		
		periodoEmAberto = false;
		duracao = Duration.ZERO;
	
		while (iterator.hasNext()) {
			if (!periodoEmAberto) {
				registroEntrada = iterator.next().getDataRegistro();
				periodoEmAberto = true;
			} else  {
				duracao = duracao.plus(Duration.between(registroEntrada, iterator.next().getDataRegistro()));
				periodoEmAberto = false;
			}

		}		
		
		return new PontoDto(registrosPonto, duracao.toHours(), duracao.toMinutes());
	}

	public void validaRegistro(String idUsuario, PontoForm pontoForm) { 
		
		TipoBatidaPonto ultimoTipoBatida = null;	
		
		Long idLong = Long.parseLong(idUsuario);
		Iterable<Ponto> registrosPonto = pontoRepository.findAllByUsuario_id(idLong);

		Iterator<Ponto> iterator = registrosPonto.iterator();
		
		if (!iterator.hasNext()) {
			if (pontoForm.getTipoBatida().toString() != "ENTRADA") {
				throw new ValidacaoException("tipoBatida", "O primeiro registro deve ser ENTRADA");
			}
		} else {
			while (iterator.hasNext()){
				ultimoTipoBatida = iterator.next().getTipoBatida();
			}
			if (ultimoTipoBatida.toString() == pontoForm.getTipoBatida().toString()) {
				throw new ValidacaoException("tipoBatida", "Entrada/Saida incorreto");
			} 
		}
		
	}
}
