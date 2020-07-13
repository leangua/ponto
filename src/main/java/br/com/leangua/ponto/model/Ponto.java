package br.com.leangua.ponto.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ponto {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataRegistro = LocalDateTime.now();
	private TipoBatidaPonto tipoBatida;
	@ManyToOne
	private Usuario usuario;
	
	public Ponto() {
		
	}
	
	public Ponto(TipoBatidaPonto tipoBatida, Usuario usuario) {
		this.tipoBatida = tipoBatida;
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}
	public LocalDateTime getDataRegistro() {
		return dataRegistro;
	}
	public TipoBatidaPonto getTipoBatida() {
		return tipoBatida;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDataRegistro(LocalDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public void setTipoBatida(TipoBatidaPonto tipoBatida) {
		this.tipoBatida = tipoBatida;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
