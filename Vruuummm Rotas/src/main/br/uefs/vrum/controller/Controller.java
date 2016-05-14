package br.uefs.vrum.controller;

import java.util.List;

import br.uefs.vrum.exceptions.arestaInexistenteException;
import br.uefs.vrum.exceptions.verticeInexistenteException;
import br.uefs.vrum.model.Grafo;
import br.uefs.vrum.util.Aresta;
import br.uefs.vrum.util.Vertice;

public class Controller {

	private Grafo grafo = new Grafo();
	
	/**
	 * Este método gerencia a adição de vértices no grafo
	 * @param String local - Recebe o nome do vértice
	 * @return vertice - Retorna o vértice adicionado*/
	public Vertice adicionarPonto(String local){
		Vertice vertice = new Vertice(local);
		grafo.adicionarVertice(vertice);
		return vertice;
	}
	
	/**
	 * Este método gerencia a adição de arestas no grafo
	 * @param Vertice verticeOrigem - Recebe o "ponto inicial" da aresta
	 * @param Vertice verticeDestino - Recebe o "ponto final" da aresta
	 * @param double tempo - Recebe o tempo; 
	 */
	public void adicionarCaminho(Vertice verticeOrigem, Vertice verticeDestino,double tempo){
		Aresta aresta = new Aresta(verticeOrigem,verticeDestino,tempo);
		grafo.adicionarAresta(aresta);
	}
	
	/**
	 * Este método recupera um ponto do grafo
	 * @param String nomeVertice - Recebe o nome do vértice desejado
	 * @throws verticeInexistenteException - Lança a exceção se o vértice não existir
	 * @return Vertice procurado - Retorna o vértice desejado*/
	public Vertice recuperarPonto(String nomeVertice) throws verticeInexistenteException{
		Vertice procurado = grafo.recuperarVertice(nomeVertice);
		if(procurado !=null){
			return procurado;
		}
		throw new verticeInexistenteException();
	}
	
	/**
	 * Este método recupera uma aresta o do grafo
	 * @param String nomeOrigem - Recebe o nome do "ponto inicial" da aresta
	 * @param String nomeDestino - Recebe o nome do "ponto final" da aresta
	 * @throws verticeInexistenteException - Lança a exceção se o vértice não existir
	 * @throws arestaInexistenteException - Lança a exceção se a aresta não existir
	 * @return Aresta procurada - Retorna a aresta desejada*/
	public Aresta recuperarCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		
		Vertice origem = recuperarPonto(nomeOrigem);
		Vertice destino = recuperarPonto(nomeDestino);
		
		Aresta procurada = grafo.recuperarAresta(origem,destino);
		if(procurada !=null){
			return procurada;
		}
		throw new arestaInexistenteException();
	}
	
	/**
	 * Este método gerencia o cálculo do(s) menor(es) caminhos entre dois pontos
	 * @param String nomeOrigem - Recebe o nome do "ponto inicial" do caminho
	 * @param String nomeDestino - Recebe o nome do "ponto final" do caminho
	 * @return List<List<Vertice>> menoresCaminhos - Retorna uma lista de vértices*/
	public List<List<Vertice>> calcularMenorCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException{
		
		Vertice origem = recuperarPonto(nomeOrigem);
		Vertice destino = recuperarPonto(nomeDestino);
		int partida = grafo.getListaVertices().indexOf(origem);
		int chegada = grafo.getListaVertices().indexOf(destino);
		return grafo.menorCaminho(partida, chegada);
	}
	
	/**
	 * Este método gerencia a remoção de um vértice do grafo
	 * @throws verticeInexistenteException - Lança a exceção se o vértice não existir
	 * @param String nomeVertice - Recebe o nome do vértice a ser removido
	 * @return Vertice aRemover - Retorna o vértice removido*/
	public Vertice removerVertice(String nomeVertice) throws verticeInexistenteException{
		Vertice aRemover = recuperarPonto(nomeVertice);
		grafo.removerVertice(aRemover);
		return aRemover;
	}
	
	/**
	 * Este método gerencia a remoção de uma aresta do grafo
	 * @throws verticeInexistenteException - Lança a exceção se o vértice não existir
	 * @thores arestaInexistenteException - Lança a exceção se a aresta não existir
	 * @param String nomeOrigem - Recebe o nome do "ponto inicial" da aresta
	 * @param String nomeDestino - Recebe o nome do "ponto final" da aresta
	 * */
	public void removerCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException, arestaInexistenteException{
		Aresta aRemover = recuperarCaminho(nomeOrigem,nomeDestino);
		grafo.removerAresta(aRemover);
	}
	/**
	 * Retorna o grafo
	 * @return Grafo grafo - Retorna o grafo*/
	public Grafo getGrafo() {
		return this.grafo;
	}
}
