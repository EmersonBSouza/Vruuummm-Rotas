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
	
	public Vertice getOrigem() {
		return origem;
	}
	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}
	public Vertice getDestino() {
		return destino;
	}
	public void setDestino(Vertice destino) {
		this.destino = destino;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
}
