package br.com.leangua.ponto.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import br.com.leangua.ponto.model.Usuario;

public class UsuarioForm {

	@NotNull 
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cpf; 
	@NotNull
	@NotEmpty
	private String email;
	 
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Usuario converter() {
		return new Usuario(nome, cpf, email); 
	}
}
