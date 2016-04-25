package br.uefs.vrum.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.uefs.vrum.util.Aresta;
import br.uefs.vrum.util.Vertice;

public class Grafo {

	private List<Vertice> listaVertices;	
	
	public Grafo(){
		setListaVertices(new ArrayList<Vertice>());	
	}

	public void adicionarVertice(Vertice vertice){
		listaVertices.add(vertice);
	}
	public void adicionarAresta(Aresta aresta){
		
		Iterator<Vertice> iterador = listaVertices.iterator();
		Vertice atual = null;
		while(iterador.hasNext()){
			atual = (Vertice) iterador.next();
			if(atual.getIndice().equals(aresta.getOrigem().getIndice())){
				atual.getListaAdj().add(aresta);
			}
			if(atual.getIndice().equals(aresta.getDestino().getIndice())){
				Aresta auxiliar = new Aresta(aresta.getDestino(),aresta.getOrigem(),aresta.getTempo());
				atual.getListaAdj().add(auxiliar);
			}
		}		
	}

	public void removerVertice(Vertice vertice){
		
		Iterator<Vertice> iterador = listaVertices.iterator();
		Vertice atual = null;
		
		while(iterador.hasNext()){
			atual = (Vertice) iterador.next();
			if(atual.equals(vertice)){
				Iterator<Aresta> adjacencias = vertice.getListaAdj().iterator();
				Aresta aRemover = null;
				while(adjacencias.hasNext()){
					aRemover = (Aresta)adjacencias.next();
					removerAresta(aRemover);
				}
			}
		}
		listaVertices.remove(vertice);
	}
	
	public void removerAresta(Aresta aresta){
		
		Iterator<Aresta> iteradorOrigem = aresta.getOrigem().getListaAdj().iterator();
		Aresta atual = null;
		
		while(iteradorOrigem.hasNext()){
			atual = (Aresta)iteradorOrigem.next();
			if(atual.getDestino().equals(aresta.getDestino())){
				iteradorOrigem.remove();
				break;
			}
		}
		
		Iterator<Aresta> iteradorDestino = aresta.getDestino().getListaAdj().iterator();
		atual = null;
		
		while(iteradorDestino.hasNext()){
			atual = (Aresta)iteradorOrigem.next();
			if(atual.getDestino().equals(aresta.getOrigem())){
				iteradorDestino.remove();
				break;
			}
		}
	}
	public List<Vertice> getListaVertices() {
		return listaVertices;
	}
	
	public void setListaVertices(List<Vertice> listaVertices) {
		this.listaVertices = listaVertices;
	}
	
	public Vertice recuperarVertice(String nome){
		
		Iterator<Vertice> iterador = listaVertices.iterator();
		Vertice procurado = null;
		while(iterador.hasNext()){
			procurado = (Vertice)iterador.next();
			if(procurado.getIndice().equals(nome)){
				return procurado;
			}
		}
		return null;
	}
	
	public Aresta recuperarAresta(Vertice origem, Vertice destino){
		
		Iterator<Aresta> iterador = origem.getListaAdj().iterator();
		Aresta procurada = null;
		while(iterador.hasNext()){
			procurada = (Aresta)iterador.next();
			if(procurada.getOrigem().getIndice().equals(origem.getIndice()) && procurada.getDestino().getIndice().equals(destino.getIndice())){
				return procurada;
			}
		}
		return null;
	}
}
