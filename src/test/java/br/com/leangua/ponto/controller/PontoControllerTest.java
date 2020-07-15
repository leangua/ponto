package br.com.leangua.ponto.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.leangua.ponto.controller.form.PontoForm;
import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.TipoBatidaPonto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.PontoRepository;
import br.com.leangua.ponto.repository.UsuarioRepository;
import br.com.leangua.ponto.service.PontoService;
import br.com.leangua.ponto.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PontoController.class)
@AutoConfigureMockMvc
public class PontoControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean
	  private UsuarioService usuarioService;
	  
	  @MockBean
	  private UsuarioRepository usuarioRepository;
	  
	  @MockBean
	  private PontoRepository pontoRepository;
	  
	  @MockBean
	  private PontoService pontoService;
	  
	  private Usuario usuario;
	  
	  private ObjectMapper objectMapper = new ObjectMapper();
	  
	  private PontoForm pontoForm;
	  
	  private TipoBatidaPonto tipoBatida = TipoBatidaPonto.ENTRADA;
	  
	  private String idUsuario = "1";
	  
	  @Before
	  public void preparar() {
		  usuario = new Usuario("usuario1", "123", "usuario1@gmail.com");
		  usuario.setId(Long.getLong("1"));
	  }
	  
	  @Test
	  public void deveRegistrarEntrada() throws Exception{
		  when(usuarioService.buscar(any(String.class))).thenReturn(Optional.of(usuario));
		  
		  String idUsuario = "1";
		  pontoForm = new PontoForm();
		  
		  pontoForm.setTipoBatida(tipoBatida);
		  
		  String pontoFormJson = objectMapper.writeValueAsString(pontoForm);
		  
		  this.mockMvc.perform(post("/usuarios/" + idUsuario + "/ponto/registrar", "1")
		            .contentType("application/json")
		            .content(pontoFormJson))
		            .andExpect(status().isCreated());

	  }
	  
	  @Test
	  public void deveListarRegistrosPonto() throws Exception {
		  Ponto pontoEntrada = new Ponto();
		  pontoEntrada.setId(Long.getLong("1"));
		  pontoEntrada.setTipoBatida(tipoBatida);
		  pontoEntrada.setUsuario(usuario);
		  
		  Ponto pontoSaida = new Ponto();
		  pontoSaida.setId(Long.getLong("2"));
		  pontoSaida.setTipoBatida(TipoBatidaPonto.SAIDA);
		  pontoSaida.setUsuario(usuario);
		  Collection<Ponto> registrosPonto = new ArrayList<Ponto>();
		  registrosPonto.add(pontoEntrada);
		  registrosPonto.add(pontoSaida);
		  
		  
		  when(pontoRepository.findAllByUsuario_id(any(Long.class))).thenReturn(registrosPonto);
		  
		  mockMvc.perform(get("/usuarios/" + idUsuario + "/ponto" + "/listar"))
		  				.andExpect(status().is2xxSuccessful());
		  
	  }


}
