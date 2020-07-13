package br.com.leangua.ponto.exception;

public class ValidacaoException extends RuntimeException{
	private String atributo;
	
	public String getAtributo() {
		return atributo;
	}

	public ValidacaoException(String atributo, String message) {
		super(message);
		this.atributo = atributo;
	}

}
