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
/*	@Test
	public void menorCaminho(){
		
		Vertice novoVertice1 = CriarObjetos.criaVertice("Hogwarts");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Hogsmead");
		Vertice novoVertice3 = CriarObjetos.criaVertice("Terra do Nunca");
		Vertice novoVertice4 = CriarObjetos.criaVertice("El Dorado");
		Vertice novoVertice5 = CriarObjetos.criaVertice("Ola");
		Vertice novoVertice6 = CriarObjetos.criaVertice("Alo");
		Vertice novoVertice7 = CriarObjetos.criaVertice("Lao");
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 1);//1
		Aresta novaAresta2 = CriarObjetos.criaAresta(novoVertice1, novoVertice3, 1);//2
		Aresta novaAresta3 = CriarObjetos.criaAresta(novoVertice2, novoVertice4, 1);
		Aresta novaAresta4 = CriarObjetos.criaAresta(novoVertice3, novoVertice4, 1);
		Aresta novaAresta5 = CriarObjetos.criaAresta(novoVertice4, novoVertice5, 1);
		Aresta novaAresta6 = CriarObjetos.criaAresta(novoVertice4, novoVertice6, 1);
		Aresta novaAresta7 = CriarObjetos.criaAresta(novoVertice5, novoVertice7, 1);
		Aresta novaAresta8 = CriarObjetos.criaAresta(novoVertice6, novoVertice7, 1);
		grafo.adicionarVertice(novoVertice1);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarVertice(novoVertice3);
		grafo.adicionarVertice(novoVertice4);
		grafo.adicionarVertice(novoVertice5);
		grafo.adicionarVertice(novoVertice6);
		grafo.adicionarVertice(novoVertice7);
		grafo.adicionarAresta(novaAresta);
		grafo.adicionarAresta(novaAresta2);
		grafo.adicionarAresta(novaAresta3);
		grafo.adicionarAresta(novaAresta4);
		grafo.adicionarAresta(novaAresta5);
		grafo.adicionarAresta(novaAresta6);
		grafo.adicionarAresta(novaAresta7);
		grafo.adicionarAresta(novaAresta8);
	
		List<Integer>menorCaminho = grafo.menorCaminho(0,6);
		//double[][]menorCaminho = grafo.FloydWarshall();
	}*/


	@Test
	public void menoresCaminhos(){

		Vertice novoVertice1 = CriarObjetos.criaVertice("Hogwarts");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Hogsmead");
		Vertice novoVertice3 = CriarObjetos.criaVertice("Terra do Nunca");
		Vertice novoVertice4 = CriarObjetos.criaVertice("El Dorado");
		Vertice novoVertice5 = CriarObjetos.criaVertice("Final Frontier");
		Vertice novoVertice6 = CriarObjetos.criaVertice("Fear of the Dark");
		Vertice novoVertice7 = CriarObjetos.criaVertice("The Trooper");
		Vertice novoVertice8 = CriarObjetos.criaVertice("2 Minutes to Midnight");
		Vertice novoVertice9 = CriarObjetos.criaVertice("Satellite 16");
//		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 1);//1
//		Aresta novaAresta2 = CriarObjetos.criaAresta(novoVertice1, novoVertice3, 1);//2
//		Aresta novaAresta3 = CriarObjetos.criaAresta(novoVertice1, novoVertice4, 1);
//		Aresta novaAresta4 = CriarObjetos.criaAresta(novoVertice2, novoVertice5, 1);
//		Aresta novaAresta5 = CriarObjetos.criaAresta(novoVertice3, novoVertice5, 1);
//		Aresta novaAresta6 = CriarObjetos.criaAresta(novoVertice4, novoVertice5, 1);
		
		Aresta novaAresta = CriarObjetos.criaAresta(novoVertice1, novoVertice2, 1);//1
		Aresta novaAresta2 = CriarObjetos.criaAresta(novoVertice2, novoVertice3, 1);//2
		Aresta novaAresta3 = CriarObjetos.criaAresta(novoVertice3, novoVertice4, 1);
		Aresta novaAresta4 = CriarObjetos.criaAresta(novoVertice3, novoVertice5, 1);
		Aresta novaAresta5 = CriarObjetos.criaAresta(novoVertice4, novoVertice6, 1);
		Aresta novaAresta6 = CriarObjetos.criaAresta(novoVertice5, novoVertice6, 1);
		Aresta novaAresta7 = CriarObjetos.criaAresta(novoVertice6, novoVertice7, 1);
		Aresta novaAresta8 = CriarObjetos.criaAresta(novoVertice6, novoVertice8, 1);
		Aresta novaAresta9 = CriarObjetos.criaAresta(novoVertice7, novoVertice9, 1);
		Aresta novaAresta10 = CriarObjetos.criaAresta(novoVertice8, novoVertice9, 1);

		grafo.adicionarVertice(novoVertice1);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarVertice(novoVertice3);
		grafo.adicionarVertice(novoVertice4);
		grafo.adicionarVertice(novoVertice5);
		grafo.adicionarVertice(novoVertice6);
		grafo.adicionarVertice(novoVertice7);
		grafo.adicionarVertice(novoVertice8);
		grafo.adicionarVertice(novoVertice9);
		grafo.adicionarAresta(novaAresta);
		grafo.adicionarAresta(novaAresta2);
		grafo.adicionarAresta(novaAresta3);
		grafo.adicionarAresta(novaAresta4);
		grafo.adicionarAresta(novaAresta5);
		grafo.adicionarAresta(novaAresta6);
		grafo.adicionarAresta(novaAresta7);
		grafo.adicionarAresta(novaAresta8);
		grafo.adicionarAresta(novaAresta9);
		grafo.adicionarAresta(novaAresta10);

		List<Integer>menorCaminho = grafo.menorCaminho(0,8);
		//double[][]menorCaminho = grafo.FloydWarshall();
	}
}
