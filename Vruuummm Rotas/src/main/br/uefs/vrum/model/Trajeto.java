package br.uefs.vrum.model;

public class Trajeto {

	private int origem;
	private int destino;
	private double custo;

	/**
	 * Retorna a origem do trajeto
	 * @return int origem*/
	public int getOrigem() {
		return origem;
	}
	/**
	 * Atribui uma origem ao trajeto
	 * @param int origem*/
	public void setOrigem(int origem) {
		this.origem = origem;
	}
	/**
	 * Retorna o destino do trajeto
	 * @return int destino*/
	public int getDestino() {
		return destino;
	}
	/**
	 * Atribui um destino ao trajeto
	 * @param int destino*/
	public void setDestino(int destino) {
		this.destino = destino;
	}
	/**
	 * Retorna o custo do trajeto
	 * @return double custo*/
	public double getCusto() {
		return custo;
	}
	/**
	 * Atribui um custo ao trajeto
	 * @param int custo*/
	public void setCusto(double custo) {
		this.custo = custo;
	}
}
