package br.com.leangua.ponto.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.com.leangua.ponto.model.TipoBatidaPonto;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.PontoRepository;
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
	  private PontoRepository pontoRepository;
	  
	  @MockBean
	  private PontoService pontoService;
	  
	  private ObjectMapper mapper = new ObjectMapper();
	  
	  private Usuario usuario;
	  
	  private ObjectMapper objectMapper = new ObjectMapper();
	  
	  private PontoForm pontoForm;
	  
	  private TipoBatidaPonto tipoBatida = TipoBatidaPonto.ENTRADA;
	  
	  @Before
	  public void preparar() {
		  usuario = new Usuario("usuario1", "123", "usuario1@gmail.com");
	  }
	  
	  @Test
	  public void deveRegistrarEntrada() throws Exception{
		  String idUsuario = "1";
		  pontoForm = new PontoForm();
		  
		  pontoForm.setTipoBatida(tipoBatida);
		  
		  String pontoFormJson = objectMapper.writeValueAsString(pontoForm);
		  
		  this.mockMvc.perform(post("/usuarios/" + idUsuario + "/ponto/registrar", "1")
		            .contentType("application/json")
		            .content(pontoFormJson))
		            .andExpect(status().isCreated());

	  }


}
