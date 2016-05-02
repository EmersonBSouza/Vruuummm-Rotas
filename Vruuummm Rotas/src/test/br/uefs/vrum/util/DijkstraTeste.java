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
		
		List<Vertice> menorCaminho = new ArrayList<Vertice>();
		
		menorCaminho.add(CriarObjetos.criaVertice("A"));
		menorCaminho.add(CriarObjetos.criaVertice("E"));
		menorCaminho.add(CriarObjetos.criaVertice("B"));
		menorCaminho.add(CriarObjetos.criaVertice("F"));
		
		List<Vertice> caminhoObtido = Dijkstra.calcularMenorCaminho(grafo);
		
		assertEquals(menorCaminho, caminhoObtido);
		
		
	}
}
