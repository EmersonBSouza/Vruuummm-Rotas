package br.uefs.vrum.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.uefs.vrum.util.Aresta;
import br.uefs.vrum.util.CriarObjetos;
import br.uefs.vrum.util.Vertice;

public class GrafoTeste {
	Grafo grafo;

	@Before
	public void setUp(){
		grafo = new Grafo();
	}

	@Test
	public void adicionarVerticeSucesso() {

		Vertice novoVertice = CriarObjetos.criaVertice("P. Sherman 42, Wallaby Way, Sydney.");
		grafo.adicionarVertice(novoVertice);

		assertEquals(novoVertice, grafo.recuperarVertice("P. Sherman 42, Wallaby Way, Sydney."));
	}
	
	@Test
	public void adicionarArestaSucesso() {
		
		Vertice novoVertice1 = CriarObjetos.criaVertice("Terra do Nunca");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Narnia");
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 8);
		grafo.adicionarVertice(novoVertice1);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarAresta(novaAresta);
		
		assertEquals(novaAresta, grafo.recuperarAresta(novoVertice1, novoVertice2));
	}
	
	@Test
	public void removerVerticeSucesso() {
		
		Vertice novoVertice = CriarObjetos.criaVertice("Notre Dame");
		
		grafo.adicionarVertice(novoVertice);
		
		grafo.removerVertice(novoVertice);
		
		assertEquals(null, grafo.recuperarVertice("Notre Dame"));
		
	}
	
	@Test
	public void removerArestaSucesso() {
		Vertice novoVertice1 = CriarObjetos.criaVertice("TomorrowLand");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Eldorado");
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 42);
		
		grafo.removerAresta(novaAresta);
		
		assertEquals(null, grafo.recuperarAresta(novoVertice1, novoVertice2));
		
	}
	
	@Test
	public void recuperarVerticeSucesso() {
		
		Vertice novoVertice = CriarObjetos.criaVertice("Raccoon city");
		
		grafo.adicionarVertice(novoVertice);
		
		Vertice verticeRecuperado = grafo.recuperarVertice("Raccoon city");
		
		assertEquals(novoVertice, verticeRecuperado);
	}
	
	@Test
	public void recuperarArestaSucesso(){
		
		Vertice novoVertice1 = CriarObjetos.criaVertice("Hogwarts");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Hogsmead");
		
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 7);
		grafo.adicionarVertice(novoVertice1);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarAresta(novaAresta);
		Aresta arestaRecuperada = grafo.recuperarAresta(novoVertice1, novoVertice2);
		
		assertEquals(novaAresta, arestaRecuperada);
	}
	
	@Test
	public void transformarEmMatriz(){
		
		Vertice novoVertice1 = CriarObjetos.criaVertice("Hogwarts");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Hogsmead");
		
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 7);
		grafo.adicionarVertice(novoVertice1);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarAresta(novaAresta);
		
		Vertice novoVertice3 = CriarObjetos.criaVertice("Terra do Nunca");
		Vertice novoVertice4 = CriarObjetos.criaVertice("Narnia");
		Aresta novaAresta2 = CriarObjetos.criaAresta(novoVertice3, novoVertice4, 8);
		grafo.adicionarVertice(novoVertice3);
		grafo.adicionarVertice(novoVertice4);
		grafo.adicionarAresta(novaAresta2);
		
		Aresta novaAresta3 = CriarObjetos.criaAresta(novoVertice1, novoVertice4, 5);
		grafo.adicionarAresta(novaAresta3);
		
		double[][] matrizRecebida = grafo.transformaEmMatriz();
	}
	@Test
	public void menorCaminho(){
		
		Vertice novoVertice1 = CriarObjetos.criaVertice("Hogwarts");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Hogsmead");
		Vertice novoVertice3 = CriarObjetos.criaVertice("Terra do Nunca");
		Vertice novoVertice4 = CriarObjetos.criaVertice("El Dorado");
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 2);
		Aresta novaAresta2 = CriarObjetos.criaAresta(novoVertice1, novoVertice3, 3);
		Aresta novaAresta3 = CriarObjetos.criaAresta(novoVertice2, novoVertice4, 4);
		Aresta novaAresta4 = CriarObjetos.criaAresta(novoVertice3, novoVertice4, 5);
		grafo.adicionarVertice(novoVertice1);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarVertice(novoVertice3);
		grafo.adicionarVertice(novoVertice4);
		grafo.adicionarAresta(novaAresta);
		grafo.adicionarAresta(novaAresta2);
		grafo.adicionarAresta(novaAresta3);
		grafo.adicionarAresta(novaAresta4);
	
		double[][]menorCaminho = grafo.FloydWarshall();
	}
}
