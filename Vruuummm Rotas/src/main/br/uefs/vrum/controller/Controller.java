package br.uefs.vrum.controller;

import br.uefs.vrum.exceptions.*;
import br.uefs.vrum.model.Grafo;
import br.uefs.vrum.util.*;

public class Controller {

	Grafo grafo = new Grafo();
	
	public void adicionarVertice(String local){
		Vertice vertice = new Vertice(local);
		grafo.adicionarVertice(vertice);
	}
	
	public void adicionarAresta(Vertice verticeOrigem, Vertice verticeDestino,double tempo){
		Aresta aresta = new Aresta(verticeOrigem,verticeDestino,tempo);
		grafo.adicionarAresta(aresta);
	}
	
	public Vertice recuperarVertice(String nomeVertice) throws verticeInexistenteException{
		Vertice procurado = grafo.recuperarVertice(nomeVertice);
		if(procurado !=null){
			return procurado;
		}
		throw new verticeInexistenteException();
	}
	
	public Aresta recuperarAresta(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		
		Vertice origem = recuperarVertice(nomeOrigem);
		Vertice destino = recuperarVertice(nomeDestino);
		
		Aresta procurada = grafo.recuperarAresta(origem,destino);
		if(procurada !=null){
			return procurada;
		}
		throw new arestaInexistenteException();
	}
	
	public void calcularMenorCaminho(){
		
	}
	
	public void removerVertice(String nomeVertice) throws verticeInexistenteException{
		Vertice aRemover = recuperarVertice(nomeVertice);
		grafo.removerVertice(aRemover);
	}
	
	public void removerAresta(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		Aresta aRemover = recuperarAresta(nomeOrigem,nomeDestino);
		grafo.removerAresta(aRemover);
	}
}
