package br.uefs.vrum.util;

import br.uefs.vrum.model.Grafo;

public class CriarObjetos {

	public static Vertice criaVertice(String indice){
		Vertice vertice = new Vertice(indice);
		return vertice;
	}
	
	public static Aresta criaAresta(Vertice verticeOrigem, Vertice verticeDestino,double tempo){
		
		
		Aresta aresta = new Aresta(verticeOrigem,verticeDestino,tempo);
		return aresta;
	}
	
	public static Grafo criarGrafo1() {
		Grafo grafo = new Grafo();
		Vertice a = criaVertice("A");
		Vertice b = criaVertice("B");
		Vertice c = criaVertice("C");
		Vertice d = criaVertice("D");
		Vertice e = criaVertice("E");
		Vertice f = criaVertice("F");
		Vertice g = criaVertice("G");
		Vertice h = criaVertice("H");
		
		grafo.adicionarVertice(a);
		grafo.adicionarVertice(b);
		grafo.adicionarVertice(c);
		grafo.adicionarVertice(d);
		grafo.adicionarVertice(e);
		grafo.adicionarVertice(f);
		grafo.adicionarVertice(g);
		grafo.adicionarVertice(h);

		grafo.adicionarAresta(criaAresta(a, e, 4));
		grafo.adicionarAresta(criaAresta(a, h, 5));
		grafo.adicionarAresta(criaAresta(e, g, 3));
		grafo.adicionarAresta(criaAresta(e, b, 3));
		grafo.adicionarAresta(criaAresta(g, c, 7));
		
		grafo.adicionarAresta(criaAresta(h, d, 7));
		grafo.adicionarAresta(criaAresta(d, b, 7));
		grafo.adicionarAresta(criaAresta(d, f, 6));
		grafo.adicionarAresta(criaAresta(b, f, 6));
		
		return grafo;
	}
}
