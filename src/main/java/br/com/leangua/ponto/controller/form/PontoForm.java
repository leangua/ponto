package br.com.leangua.ponto.controller.form;

import br.com.leangua.ponto.model.Ponto;
import br.com.leangua.ponto.model.TipoBatidaPonto;
import br.com.leangua.ponto.model.Usuario;

public class PontoForm {


	private TipoBatidaPonto tipoBatida;

	public TipoBatidaPonto getTipoBatida() {
		return tipoBatida;
	}

	public void setTipoBatida(TipoBatidaPonto tipoBatida) {
		this.tipoBatida = tipoBatida;
	}
	
	public Ponto converter(Usuario usuario) {
		return new Ponto(tipoBatida, usuario);
	}
}
