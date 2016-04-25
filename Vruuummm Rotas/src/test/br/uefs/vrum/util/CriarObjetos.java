package br.uefs.vrum.util;

public class CriarObjetos {

	public static Vertice criaVertice(String indice){
		Vertice vertice = new Vertice(indice);
		return vertice;
	}
	
	public static Aresta criaAresta(Vertice verticeOrigem, Vertice verticeDestino,double tempo){
		
		
		Aresta aresta = new Aresta(verticeOrigem,verticeDestino,tempo);
		return aresta;
	}
}
