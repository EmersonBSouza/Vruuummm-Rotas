package br.uefs.vrum.util;


public class Aresta {

	private Vertice origem;
	private Vertice destino;
	private double tempo;
	
	public Aresta(Vertice origem, Vertice destino, double tempo){
		this.origem = origem;
		this.destino = destino;
		this.tempo = tempo;
	}
	
	/**
	 * Retorna o "ponto inicial" da aresta
	 * @return Vertice origem*/
	public Vertice getOrigem() {
		return origem;
	}
	/**
	 * Atribui um "ponto inicial" à aresta
	 * @param Vertice origem*/
	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}
	/**
	 * Retorna o "ponto final" da aresta
	 * @return Vertice origem*/
	public Vertice getDestino() {
		return destino;
	}
	/**
	 * Atribui um "ponto final" à aresta
	 * @param Vertice destino*/
	public void setDestino(Vertice destino) {
		this.destino = destino;
	}
	/**
	 * Retorna o custo da aresta
	 * @return double tempo*/
	public double getTempo() {
		return tempo;
	}
	/**
	 * Atribui um custo à aresta
	 * @param double tempo*/
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
}
