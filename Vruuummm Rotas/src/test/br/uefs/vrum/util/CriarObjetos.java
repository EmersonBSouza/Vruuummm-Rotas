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
	
	public static Grafo criarGrafo2() {
		Grafo grafo = new Grafo();
		Vertice a = criaVertice("A");
		Vertice b = criaVertice("B");
		Vertice c = criaVertice("C");
		Vertice d = criaVertice("D");
		Vertice e = criaVertice("E");
		Vertice f = criaVertice("F");
		Vertice g = criaVertice("G");
		Vertice h = criaVertice("H");
		Vertice i = criaVertice("I");
		Vertice j = criaVertice("J");
		Vertice k = criaVertice("K");
		Vertice l = criaVertice("L");
		Vertice m = criaVertice("M");
		Vertice n = criaVertice("N");
		Vertice o = criaVertice("O");
		Vertice p = criaVertice("P");
		Vertice q = criaVertice("Q");
		Vertice r = criaVertice("R");
		
		grafo.adicionarVertice(a);
		grafo.adicionarVertice(b);
		grafo.adicionarVertice(c);
		grafo.adicionarVertice(d);
		grafo.adicionarVertice(e);
		grafo.adicionarVertice(f);
		grafo.adicionarVertice(g);
		grafo.adicionarVertice(h);
		grafo.adicionarVertice(i);
		grafo.adicionarVertice(j);
		grafo.adicionarVertice(k);
		grafo.adicionarVertice(l);
		grafo.adicionarVertice(m);
		grafo.adicionarVertice(n);
		grafo.adicionarVertice(o);
		grafo.adicionarVertice(p);
		grafo.adicionarVertice(q);
		grafo.adicionarVertice(r);
		
		grafo.adicionarAresta(criaAresta(a, b, 9));
		grafo.adicionarAresta(criaAresta(a, e, 4));
		
		grafo.adicionarAresta(criaAresta(b, c, 9));
		grafo.adicionarAresta(criaAresta(b, f, 7));
		grafo.adicionarAresta(criaAresta(b, e, 3));
		
		
		grafo.adicionarAresta(criaAresta(c, f, 7));
		grafo.adicionarAresta(criaAresta(c, g, 2));
		
		grafo.adicionarAresta(criaAresta(d, g, 1));
		grafo.adicionarAresta(criaAresta(d, k, 8));
		
		grafo.adicionarAresta(criaAresta(e, i, 9));
		
		grafo.adicionarAresta(criaAresta(f, i, 2));
		
		grafo.adicionarAresta(criaAresta(g, j, 9));
		
		grafo.adicionarAresta(criaAresta(h, o, 3));
		grafo.adicionarAresta(criaAresta(h, l, 7));
		
		grafo.adicionarAresta(criaAresta(i, j, 5));
		grafo.adicionarAresta(criaAresta(i, l, 4));
		
		grafo.adicionarAresta(criaAresta(j, k, 6));
		grafo.adicionarAresta(criaAresta(j, m, 8));
		
		grafo.adicionarAresta(criaAresta(k, n, 6));
		
		grafo.adicionarAresta(criaAresta(l, o, 2));
		grafo.adicionarAresta(criaAresta(l, p, 3));
		
		grafo.adicionarAresta(criaAresta(m, n, 4));
		
		grafo.adicionarAresta(criaAresta(n, q, 8));
		
		grafo.adicionarAresta(criaAresta(q, r, 8));

		return grafo;
	}
}
