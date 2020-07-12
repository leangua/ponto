package br.com.leangua.ponto.dto;

import java.util.List;

import br.com.leangua.ponto.model.Ponto;

public class PontoDto {
	
	private List<Ponto> ponto;
	private Long horasTrabalhadas;
	private Long minutosTrabalhados;
	
	
	public PontoDto(Iterable<Ponto> registrosPonto, long horasTrabalhadas, long minutosTrabalhados) {
		this.ponto = (List<Ponto>) registrosPonto;
		this.horasTrabalhadas = horasTrabalhadas;
		this.minutosTrabalhados = minutosTrabalhados;
	}
	
	public List<Ponto> getPonto() {
		return ponto;
	}
	public void setPonto(List<Ponto> ponto) {
		this.ponto = ponto;
	}
	public Long getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(Long horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	public Long getMinutosTrabalhados() {
		return minutosTrabalhados;
	}
	public void setMinutosTrabalhados(Long minutosTrabalhados) {
		this.minutosTrabalhados = minutosTrabalhados;
	}

}
