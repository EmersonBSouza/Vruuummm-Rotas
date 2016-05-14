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
	 * Este m�todo gerencia a adi��o de v�rtices no grafo
	 * @param String local - Recebe o nome do v�rtice
	 * @return vertice - Retorna o v�rtice adicionado*/
	public Vertice adicionarPonto(String local){
		Vertice vertice = new Vertice(local);
		grafo.adicionarVertice(vertice);
		return vertice;
	}
	
	/**
	 * Este m�todo gerencia a adi��o de arestas no grafo
	 * @param Vertice verticeOrigem - Recebe o "ponto inicial" da aresta
	 * @param Vertice verticeDestino - Recebe o "ponto final" da aresta
	 * @param double tempo - Recebe o tempo; 
	 */
	public void adicionarCaminho(Vertice verticeOrigem, Vertice verticeDestino,double tempo){
		Aresta aresta = new Aresta(verticeOrigem,verticeDestino,tempo);
		grafo.adicionarAresta(aresta);
	}
	
	/**
	 * Este m�todo recupera um ponto do grafo
	 * @param String nomeVertice - Recebe o nome do v�rtice desejado
	 * @throws verticeInexistenteException - Lan�a a exce��o se o v�rtice n�o existir
	 * @return Vertice procurado - Retorna o v�rtice desejado*/
	public Vertice recuperarPonto(String nomeVertice) throws verticeInexistenteException{
		Vertice procurado = grafo.recuperarVertice(nomeVertice);
		if(procurado !=null){
			return procurado;
		}
		throw new verticeInexistenteException();
	}
	
	/**
	 * Este m�todo recupera uma aresta o do grafo
	 * @param String nomeOrigem - Recebe o nome do "ponto inicial" da aresta
	 * @param String nomeDestino - Recebe o nome do "ponto final" da aresta
	 * @throws verticeInexistenteException - Lan�a a exce��o se o v�rtice n�o existir
	 * @throws arestaInexistenteException - Lan�a a exce��o se a aresta n�o existir
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
	 * Este m�todo gerencia o c�lculo do(s) menor(es) caminhos entre dois pontos
	 * @param String nomeOrigem - Recebe o nome do "ponto inicial" do caminho
	 * @param String nomeDestino - Recebe o nome do "ponto final" do caminho
	 * @return List<List<Vertice>> menoresCaminhos - Retorna uma lista de v�rtices*/
	public List<List<Vertice>> calcularMenorCaminho(String nomeOrigem, String nomeDestino) throws verticeInexistenteException{
		
		Vertice origem = recuperarPonto(nomeOrigem);
		Vertice destino = recuperarPonto(nomeDestino);
		int partida = grafo.getListaVertices().indexOf(origem);
		int chegada = grafo.getListaVertices().indexOf(destino);
		return grafo.menorCaminho(partida, chegada);
	}
	
	/**
	 * Este m�todo gerencia a remo��o de um v�rtice do grafo
	 * @throws verticeInexistenteException - Lan�a a exce��o se o v�rtice n�o existir
	 * @param String nomeVertice - Recebe o nome do v�rtice a ser removido
	 * @return Vertice aRemover - Retorna o v�rtice removido*/
	public Vertice removerVertice(String nomeVertice) throws verticeInexistenteException{
		Vertice aRemover = recuperarPonto(nomeVertice);
		grafo.removerVertice(aRemover);
		return aRemover;
	}
	
	/**
	 * Este m�todo gerencia a remo��o de uma aresta do grafo
	 * @throws verticeInexistenteException - Lan�a a exce��o se o v�rtice n�o existir
	 * @thores arestaInexistenteException - Lan�a a exce��o se a aresta n�o existir
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
