package br.com.leangua.ponto.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leangua.ponto.controller.form.PontoForm;
import br.com.leangua.ponto.dto.PontoDto;
import br.com.leangua.ponto.exception.ValidacaoException;
import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.TipoBatidaPonto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.PontoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PontoServiceTest {
	
	@Autowired
	PontoService pontoService;
	
	@MockBean
	PontoRepository pontoRepository;
	
	private TipoBatidaPonto tipoBatida = TipoBatidaPonto.ENTRADA;
	
	private Usuario usuario;
	
	private PontoForm pontoForm;
	
	Collection<Ponto> registrosPonto = new ArrayList<Ponto>();
	
	@Before
	public void preparar() {
		String idString = "1";
		Long idLong = Long.getLong(idString);
		
		String idString2 = "2";
		Long idLong2 = Long.getLong(idString2);
		usuario = new Usuario("usuario1", "123", "usuario1@gmail.com");
		
		usuario.setId(idLong);
		
		Ponto pontoEntrada = new Ponto(tipoBatida, usuario);
		pontoEntrada.setId(idLong);
		pontoEntrada.setTipoBatida(tipoBatida);

		Ponto pontoSaida = new Ponto(TipoBatidaPonto.SAIDA, usuario);
		pontoSaida.setId(idLong2);
		pontoSaida.setDataRegistro(pontoSaida.getDataRegistro().plusMinutes(1));

		registrosPonto.add(pontoEntrada);
		registrosPonto.add(pontoSaida);

	}
	
	@Test
	public void deveSomarHorasTrabalhadas() {
		String id = "1";
		Long idLong = Long.valueOf(id);
		Long idUsuario = idLong;
		Long horasTrabalhadas = Long.valueOf("0");
		Long minutosTrabalhados = Long.valueOf(id);
		
		when(pontoRepository.findAllByUsuario_id(any(Long.class))).thenReturn(registrosPonto);
		
		PontoDto pontoDto = pontoService.somarHorasTrabalhadas(idUsuario);
		
		assertEquals(pontoDto.getHorasTrabalhadas(), horasTrabalhadas);
		assertEquals(pontoDto.getMinutosTrabalhados(), minutosTrabalhados);
	}
	
	@Test
	public void deveValidarRegistroPonto() {
		String idUsuario = "1";
		
		when(pontoRepository.findAllByUsuario_id(any(Long.class))).thenReturn(registrosPonto);
		
		pontoForm = new PontoForm();
		
		pontoForm.setTipoBatida(tipoBatida);
		
		pontoService.validaRegistro(idUsuario, pontoForm);
		
	}
	
	@Test(expected = ValidacaoException.class)
	public void deveLancarExcessaoRegistroPonto() {
		String idUsuario = "1";
		
		when(pontoRepository.findAllByUsuario_id(any(Long.class))).thenReturn(registrosPonto);
		
		pontoForm = new PontoForm();
		
		pontoForm.setTipoBatida(TipoBatidaPonto.SAIDA);
		
		pontoService.validaRegistro(idUsuario, pontoForm);
		
	}

}
