package br.com.leangua.ponto.model;

public enum TipoBatidaPonto {
	
	ENTRADA("Entrada"),
	SAIDA("Saida");
	
	private String nomeTipo;
	
	private TipoBatidaPonto(String tipoBatida) {
		this.nomeTipo = tipoBatida;
	}
	
	
	public String getNome() {
		return this.nomeTipo;
	}

}
