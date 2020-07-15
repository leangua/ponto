package br.com.leangua.ponto.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leangua.ponto.controller.form.UsuarioForm;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	private UsuarioForm usuarioForm;
	
	private Usuario usuario;
	
	@Before
	public void preparar() {
		usuarioForm = new UsuarioForm();
		usuarioForm.setNome("usuario1");
		usuarioForm.setCpf("123");
		usuarioForm.setEmail("usuario1@gamil.com");
		
		usuario = new Usuario();
		usuario.setId(Long.getLong("1"));
		usuario.setNomeCompleto("usuario1");
		usuario.setCpf("123");
		usuario.setEmail("usuario1@gamil.com");
	}
	
	@Test
	public void deveCriarUsuario() {
		
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

		Usuario usuarioCriado = usuarioService.criar(usuarioForm);
		
		assertEquals(usuario.getNome(), usuarioCriado.getNome());
		assertEquals(usuario.getCpf(), usuarioCriado.getCpf());
		assertEquals(usuario.getEmail(), usuarioCriado.getEmail());
	}
	
	 @Test
	 public void deveBuscarUmUsuarioPorId() {
	    String id = "1";
	    Long idLong = Long.parseLong(id);
	    
	    when(usuarioRepository.findById(idLong)).thenReturn(Optional.of(usuario));
	    
	    Optional<Usuario> optional = usuarioService.buscar(id);
	    
	    assertEquals(usuario, optional.get());
	  }
	 
	 @Test
	 public void deveAtualizarUmUsuario() {
		 String id = "1";
		 Long idLong = Long.parseLong(id);
		 
		 when(usuarioRepository.findById(idLong)).thenReturn(Optional.of(usuario));
		 
		 usuarioService.atualizar(id, usuarioForm);
		 
		 verify(usuarioRepository).save(any(Usuario.class));
		 
	 }


}
