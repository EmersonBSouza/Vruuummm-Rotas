package br.uefs.vrum.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.uefs.vrum.model.Grafo;

public class DijkstraTeste {

	@Test
	public void calcularMenorCaminhoSucesso() {

		Grafo grafo = CriarObjetos.criarGrafo1();

		List<Integer> menorCaminho = new ArrayList<Integer>();
		Vertice origem = grafo.recuperarVertice("A");
		Vertice destino = grafo.recuperarVertice("F");
		List<Integer> caminhoObtido = grafo.menorCaminho(origem, destino);


		//menorCaminho.add(CriarObjetos.criaVertice("A"));
		menorCaminho.add(0);				
		//menorCaminho.add(CriarObjetos.criaVertice("E"));
		menorCaminho.add(4);
		//menorCaminho.add(CriarObjetos.criaVertice("B"));
		menorCaminho.add(1);
		//menorCaminho.add(CriarObjetos.criaVertice("F"));
		menorCaminho.add(5);

		assertEquals(menorCaminho, caminhoObtido);
	}

	@Test
	public void calcularMenorCaminhoSucesso2() {
		
		Grafo grafo = CriarObjetos.criarGrafo2();

		List<Integer> menorCaminho = new ArrayList<Integer>();
		Vertice origem = grafo.recuperarVertice("A");
		Vertice destino = grafo.recuperarVertice("D");
		List<Integer> caminhoObtido = grafo.menorCaminho(origem, destino);


		//menorCaminho.add(CriarObjetos.criaVertice("A"));
		menorCaminho.add(0);				
		//menorCaminho.add(CriarObjetos.criaVertice("E"));
		menorCaminho.add(4);
		//menorCaminho.add(CriarObjetos.criaVertice("B"));
		menorCaminho.add(1);
		//menorCaminho.add(CriarObjetos.criaVertice("C"));
		menorCaminho.add(2);
		//menorCaminho.add(CriarObjetos.criaVertice("G"));
		menorCaminho.add(6);
		//menorCaminho.add(CriarObjetos.criaVertice("D"));
		menorCaminho.add(3);

		assertEquals(menorCaminho, caminhoObtido);
	}
}
