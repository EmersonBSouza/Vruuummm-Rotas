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
	
	/**Retorna o �ndice do v�rtice
	 * @return String indice*/
	public String getIndice() {
		return indice;
	}
	/**
	 * Atribui um indice ao v�rtice
	 * @param String indice*/
	public void setIndice(String indice) {
		this.indice = indice;
	}
	/**
	 * Retorna a lista de adjac�ncias do v�rtice
	 * @return List<Aresta> listaAdj - retorna uma lista de arestas*/
	public List<Aresta> getListaAdj() {
		return listaAdj;
	}

	/**
	 * Atribui uma lista de adjac�ncias ao v�rtice
	 * @param List<Aresta> listaAdj*/
	public void setListaAdj(List<Aresta> listaAdj) {
		this.listaAdj = listaAdj;
	}
	
	@Override
	public String toString() { 
		return this.indice;
	}
}
