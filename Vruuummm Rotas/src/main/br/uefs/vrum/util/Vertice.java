package br.uefs.vrum.util;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

	private String indice;
	private boolean foiVisitado;
	private List<Aresta> listaAdj;
	
	public Vertice(String indice){
		this.indice = indice;
		foiVisitado = false;
		setListaAdj(new ArrayList<Aresta>());
	}
	
	public String getIndice() {
		return indice;
	}
	public void setIndice(String indice) {
		this.indice = indice;
	}
	public boolean isFoiVisitado() {
		return foiVisitado;
	}
	public void setFoiVisitado(boolean foiVisitado) {
		this.foiVisitado = foiVisitado;
	}

	public List<Aresta> getListaAdj() {
		return listaAdj;
	}

	public void setListaAdj(List<Aresta> listaAdj) {
		this.listaAdj = listaAdj;
	}
}
