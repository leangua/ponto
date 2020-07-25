package br.com.leangua.ponto.dto;

import java.time.LocalDateTime;

public class UsuarioDto {

	private String nome;
	private String cpf;
	private String email;
	private LocalDateTime dataCadastro;
	
	public UsuarioDto() {
		
	}
	
	public UsuarioDto(String nome, String cpf, String email, LocalDateTime dataDeCadastro) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataCadastro = dataDeCadastro;
	}
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
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
}
