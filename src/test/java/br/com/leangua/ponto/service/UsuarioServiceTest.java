package br.com.leangua.ponto.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.leangua.ponto.controller.form.UsuarioForm;
import br.com.leangua.ponto.model.Usuario;
import br.com.leangua.ponto.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UsuarioService.class)
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
		usuario.setNomeCompleto("usuario1");
		usuario.setCpf("123");
		usuario.setEmail("usuario1@gamil.com");
	}
	
	@Test
	public void deveCriarUsuario() {
		
		//when(usuarioForm.converter()).thenReturn(usuario);
		when(usuarioRepository.save(usuario)).thenReturn(usuario);
		
		System.out.println(usuarioForm.getNome());
		Usuario usuarioCriado = usuarioService.criar(usuarioForm);
		
		assertEquals(usuario.getNome(), usuarioCriado.getNome());
		assertEquals(usuario.getCpf(), usuarioCriado.getCpf());
		assertEquals(usuario.getEmail(), usuarioCriado.getEmail());
	}
	
	 @Test
	 public void deveBuscarUmUsuarioPorId() {
	    String id = "1";
	    Long idLong = Long.parseLong(id);
	    
//	    Mockito.when(usuarioRepository.findById(Long.getLong(id)).thenReturn(Optional.of(usuario));
	    
	    Optional<Usuario> optional = usuarioService.buscar(id);
	    
	    assertEquals(usuario, optional.get());
	  }


}
