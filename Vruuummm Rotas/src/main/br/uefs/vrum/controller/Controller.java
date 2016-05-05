package br.uefs.vrum.controller;

import br.uefs.vrum.exceptions.*;
import br.uefs.vrum.model.Grafo;
import br.uefs.vrum.util.*;

public class Controller {

	Grafo grafo = new Grafo();
	
	public void adicionarPonto(String local){
		Vertice vertice = new Vertice(local);
		grafo.adicionarVertice(vertice);
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
	
	public void calcularMenorCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException{
		
		Vertice origem = recuperarPonto(nomeOrigem);
		Vertice destino = recuperarPonto(nomeDestino);
		int partida = grafo.getListaVertices().indexOf(origem);
		int chegada = grafo.getListaVertices().indexOf(destino);
		grafo.menorCaminho(partida, chegada);
	}
	
	public void removerVertice(String nomeVertice) throws verticeInexistenteException{
		Vertice aRemover = recuperarPonto(nomeVertice);
		grafo.removerVertice(aRemover);
	}
	
	public void removerCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		Aresta aRemover = recuperarCaminho(nomeOrigem,nomeDestino);
		grafo.removerAresta(aRemover);
	}
}
