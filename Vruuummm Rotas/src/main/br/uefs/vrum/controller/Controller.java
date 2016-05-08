package br.uefs.vrum.controller;

import java.util.List;

import br.uefs.vrum.exceptions.arestaInexistenteException;
import br.uefs.vrum.exceptions.verticeInexistenteException;
import br.uefs.vrum.model.Grafo;
import br.uefs.vrum.util.Aresta;
import br.uefs.vrum.util.Vertice;

public class Controller {

	private Grafo grafo = new Grafo();
	
	public Vertice adicionarPonto(String local){
		Vertice vertice = new Vertice(local);
		grafo.adicionarVertice(vertice);
		return vertice;
	}
	
	public void adicionarCaminho(Vertice verticeOrigem, Vertice verticeDestino,double tempo){
		Aresta aresta = new Aresta(verticeOrigem,verticeDestino,tempo);
		grafo.adicionarAresta(aresta);
	}
	
	public Vertice recuperarPonto(String nomeVertice) throws verticeInexistenteException{
		Vertice procurado = grafo.recuperarVertice(nomeVertice);
		if(procurado !=null){
			return procurado;
		}
		throw new verticeInexistenteException();
	}
	
	public Aresta recuperarCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		
		Vertice origem = recuperarPonto(nomeOrigem);
		Vertice destino = recuperarPonto(nomeDestino);
		
		Aresta procurada = grafo.recuperarAresta(origem,destino);
		if(procurada !=null){
			return procurada;
		}
		throw new arestaInexistenteException();
	}
	
	public List<List<Vertice>> calcularMenorCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException{
		
		Vertice origem = recuperarPonto(nomeOrigem);
		Vertice destino = recuperarPonto(nomeDestino);
		int partida = grafo.getListaVertices().indexOf(origem);
		int chegada = grafo.getListaVertices().indexOf(destino);
		return grafo.menorCaminho(partida, chegada);
	}
	
	public Vertice removerVertice(String nomeVertice) throws verticeInexistenteException{
		Vertice aRemover = recuperarPonto(nomeVertice);
		grafo.removerVertice(aRemover);
		return aRemover;
	}
	
	public void removerCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		Aresta aRemover = recuperarCaminho(nomeOrigem,nomeDestino);
		grafo.removerAresta(aRemover);
	}
	
	public Grafo getGrafo() {
		return this.grafo;
	}
}
