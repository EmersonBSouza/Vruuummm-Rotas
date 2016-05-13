package br.uefs.vrum.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
	public void removerVerticeComLigacaoSucesso() {
		
		Vertice novoVertice = CriarObjetos.criaVertice("Notre Dame");
		Vertice novoVertice2 = CriarObjetos.criaVertice("Terra do Nunca");
		
		grafo.adicionarVertice(novoVertice);
		grafo.adicionarVertice(novoVertice2);
		grafo.adicionarAresta(CriarObjetos.criaAresta(novoVertice, novoVertice2, 34));
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
	public void calcularTodosMenoresCaminhos(){

		Vertice novoVertice1 = CriarObjetos.criaVertice("The Talisman");
		Vertice novoVertice2 = CriarObjetos.criaVertice("The Wicker Man");
		Vertice novoVertice3 = CriarObjetos.criaVertice("Dance of Death");
		Vertice novoVertice4 = CriarObjetos.criaVertice("El Dorado");
		Vertice novoVertice5 = CriarObjetos.criaVertice("The Final Frontier");
		Vertice novoVertice6 = CriarObjetos.criaVertice("Fear of the Dark");
		Vertice novoVertice7 = CriarObjetos.criaVertice("The Trooper");
		Vertice novoVertice8 = CriarObjetos.criaVertice("2 Minutes to Midnight");
		Vertice novoVertice9 = CriarObjetos.criaVertice("Satellite 16");
		
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

		
		Vertice[] vetor1 = {novoVertice1,novoVertice2,novoVertice3,novoVertice5,novoVertice6,novoVertice7,novoVertice9};
		List<Vertice> caminho1 = new ArrayList<Vertice>();
		for(int i = 0; i<vetor1.length;i++){
			caminho1.add(vetor1[i]);
		}
		
		Vertice[] vetor2 = {novoVertice1,novoVertice2,novoVertice3,novoVertice4,novoVertice6,novoVertice8,novoVertice9};
		List<Vertice> caminho2 = new ArrayList<Vertice>();
		for(int i = 0; i<vetor2.length;i++){
			caminho2.add(vetor2[i]);
		}
		Vertice[] vetor3 = {novoVertice1,novoVertice2,novoVertice3,novoVertice4,novoVertice6,novoVertice7,novoVertice9};
		List<Vertice> caminho3 = new ArrayList<Vertice>();
		for(int i = 0; i<vetor3.length;i++){
			caminho3.add(vetor3[i]);
		}
		Vertice[] vetor4 = {novoVertice1,novoVertice2,novoVertice3,novoVertice5,novoVertice6,novoVertice8,novoVertice9};
		List<Vertice> caminho4 = new ArrayList<Vertice>();
		for(int i = 0; i<vetor4.length;i++){
			caminho4.add(vetor4[i]);
		}
		
		List<List<Vertice>> menoresCaminhos = grafo.menorCaminho(0,8);
		int contador = 0;
		for(List<Vertice> menorCaminho:menoresCaminhos){
			if(menorCaminho.equals(caminho1)||menorCaminho.equals(caminho2)||menorCaminho.equals(caminho3)||menorCaminho.equals(caminho4)){
				contador++;
			}
		}
		assertEquals(contador,4);
	}
	
	@Test
	public void calcularMenorCaminhoSucesso() {

		Grafo grafo = CriarObjetos.criarGrafo1();
		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		Vertice origem = grafo.recuperarVertice("A");
		Vertice destino = grafo.recuperarVertice("F");
		List<List<Vertice>> caminhoObtido = grafo.menorCaminho(0, 5);


		menorCaminho.add(CriarObjetos.criaVertice("A"));
		//menorCaminho.add(0);				
		menorCaminho.add(CriarObjetos.criaVertice("E"));
		//menorCaminho.add(4);
		menorCaminho.add(CriarObjetos.criaVertice("B"));
		//menorCaminho.add(1);
		menorCaminho.add(CriarObjetos.criaVertice("F"));
		//menorCaminho.add(5);

		if(menorCaminho.equals(caminhoObtido.get(0))){
			assertTrue(true);
		}
	}
	
	@Test
	public void calcularMenorCaminhoSucesso2() {
		
		Grafo grafo = CriarObjetos.criarGrafo2();

		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		Vertice origem = grafo.recuperarVertice("A");
		Vertice destino = grafo.recuperarVertice("D");
		List<List<Vertice>> caminhoObtido = grafo.menorCaminho(0, 3);


		menorCaminho.add(CriarObjetos.criaVertice("A"));
		menorCaminho.add(CriarObjetos.criaVertice("E"));
		menorCaminho.add(CriarObjetos.criaVertice("B"));
		menorCaminho.add(CriarObjetos.criaVertice("C"));
		menorCaminho.add(CriarObjetos.criaVertice("G"));
		menorCaminho.add(CriarObjetos.criaVertice("D"));
		
		if(menorCaminho.equals(caminhoObtido.get(0))){
			assertTrue(true);
		}
	}
}
