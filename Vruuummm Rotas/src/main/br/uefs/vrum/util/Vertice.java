package br.uefs.vrum.util;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

	private String indice;
	private boolean foiVisitado;
	private List<Aresta> listaAdj;
	
	public Vertice(String indice){
		this.indice = indice;
		setListaAdj(new ArrayList<Aresta>());
	}
	
	/**Retorna o índice do vértice
	 * @return String indice*/
	public String getIndice() {
		return indice;
	}
	/**
	 * Atribui um indice ao vértice
	 * @param String indice*/
	public void setIndice(String indice) {
		this.indice = indice;
	}
	/**
	 * Retorna a lista de adjacências do vértice
	 * @return List<Aresta> listaAdj - retorna uma lista de arestas*/
	public List<Aresta> getListaAdj() {
		return listaAdj;
	}

	/**
	 * Atribui uma lista de adjacências ao vértice
	 * @param List<Aresta> listaAdj*/
	public void setListaAdj(List<Aresta> listaAdj) {
		this.listaAdj = listaAdj;
	}
	
	@Override
	public String toString() { 
		return this.indice;
	}
}
