package br.com.leangua.ponto.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.leangua.ponto.controller.form.UsuarioForm;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.UsuarioRepository;
import br.com.leangua.ponto.service.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @MockBean
	  private UsuarioService usuarioService;
	  
	  @MockBean
	  private UsuarioRepository usuarioRepository;
	  
	  private ObjectMapper objectMapper = new ObjectMapper();
	   
	  private Usuario usuario;
	  
	  private String idUsuario = "1";
	  
	  @Before
	  public void preparar() {
		  usuario = new Usuario();
		    
		  usuario.setNomeCompleto("Usuario 1");
		  usuario.setCpf("123");
		  usuario.setEmail("usuario1@gmail.com");
	  }

	  
	  @Test
	  public void deveCriarUsuario() throws Exception{
		  when(usuarioService.criar(any(UsuarioForm.class))).thenReturn(usuario);
		  		  
		  String usuarioJson = objectMapper.writeValueAsString(usuario);
		  
		  mockMvc.perform(post("/usuarios/cadastrar")
		            .contentType("application/json")
		            .content(usuarioJson))
		            .andExpect(status().isCreated());

	  }
	  
	  @Test
	  public void deveValidarDadosCliente() throws Exception{
		  usuario = new Usuario();
		  
		  usuario.setNomeCompleto("Usuario 1");
		  usuario.setCpf("123");
		  
		  String usuarioJson = objectMapper.writeValueAsString(usuario);
		  
		  System.out.println(usuarioJson);
		  
		  mockMvc.perform(post("/usuarios/cadastrar")
		            .contentType("application/json")
		            .content(usuarioJson))
		  			.andExpect(status().is4xxClientError());
		  
	  }
	  
	  @Test
	  public void deveListarClientes() throws Exception{
		  
		  mockMvc.perform(get("/usuarios/listar"))
		  			.andExpect(status().is2xxSuccessful());
	  }
	  
	  
	  @Test
	  public void deveListarClienteEspecifico() throws Exception{
		  
		  mockMvc.perform(get("/usuarios/" + idUsuario))
		  			.andExpect(status().is2xxSuccessful());
	  }
	  
	  @Test
	  public void deveAtualizarDadosCliente() throws Exception{
		  
		  String id = "1";
		  usuario = new Usuario();
		  
		  usuario.setNomeCompleto("Usuario 2");
		  usuario.setCpf("123");
		  
		  String usuarioJson = objectMapper.writeValueAsString(usuario);
		  
		  System.out.println(usuarioJson);
		  
		  mockMvc.perform(put("/usuarios/" + id + "/atualizar")
		            .contentType("application/json")
		            .content(usuarioJson))
		  			.andExpect(status().is2xxSuccessful());
	  }
	  
	  
}
