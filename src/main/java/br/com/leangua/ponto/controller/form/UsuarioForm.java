package br.com.leangua.ponto.controller.form;

import br.com.leangua.ponto.model.Usuario;

public class UsuarioForm {

	private String nome;
	private String cpf;
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
