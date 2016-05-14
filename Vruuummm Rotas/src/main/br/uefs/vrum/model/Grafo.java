package br.uefs.vrum.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import br.uefs.vrum.util.Aresta;
import br.uefs.vrum.util.Vertice;

public class Grafo {

	private List<Vertice> listaVertices;	
	private double[][] matrizAdjacencia;
	private List<List<Integer>> menoresCaminhos;
	List<Trajeto> trajetos;
	
	public Grafo(){
		setListaVertices(new ArrayList<Vertice>());	
	}

	/**
	 * Este m�todo adiciona um v�rtice ao grafo
	 * @param Vertice vertice - Recebe um v�rtice
	 * */
	public void adicionarVertice(Vertice vertice){
		listaVertices.add(vertice);
	}
	
	/**
	 * Este m�todo adiciona uma aresta ao grafo
	 * @param Aresta aresta - Recebe uma aresta
	 * */
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

	/**
	 * Este m�todo remove um v�rtice do grafo
	 * @param Vertice vertice - Recebe o v�rtice a ser removido
	 * */
	public void removerVertice(Vertice vertice){

		List<Aresta> aRemover = new ArrayList<>();
		for(Vertice v : listaVertices)
			if(v.getIndice().equals(vertice.getIndice()))
				aRemover.addAll(v.getListaAdj());
		for(Aresta aresta : aRemover)
			removerAresta(aresta);
		listaVertices.remove(listaVertices.indexOf(vertice));
	}

	/**
	 * Este m�todo remove uma aresta do grafo
	 * @param Aresta aresta - Recebe a aresta a ser removida
	 * */
	public void removerAresta(Aresta aresta){

		Iterator<Aresta> iteradorOrigem = aresta.getOrigem().getListaAdj().iterator();
		Aresta atual = null;

		while(iteradorOrigem.hasNext()){
			atual = (Aresta)iteradorOrigem.next();
			if(atual.getDestino().getIndice().equals(aresta.getDestino().getIndice()))
				break;						
		}
		aresta.getOrigem().getListaAdj().remove(atual);

		Iterator<Aresta> iteradorDestino = aresta.getDestino().getListaAdj().iterator();
		atual = null;

		while(iteradorDestino.hasNext()){
			atual = (Aresta)iteradorDestino.next();
			if(atual.getOrigem().getIndice().equals(aresta.getOrigem().getIndice()))
				break;				
		}
		aresta.getDestino().getListaAdj().remove(atual);
	}
	
	public List<Vertice> getListaVertices() {
		return listaVertices;
	}

	public void setListaVertices(List<Vertice> listaVertices) {
		this.listaVertices = listaVertices;
	}

	/**
	 * Este m�todo recupera um v�rtice do grafo
	 * @param String nome - Recebe o nome do v�rtice desejado
	 * @return procurado - Retorna o v�rtice procurado*/
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

	/**
	 * Este m�todo recupera uma aresta do grafo
	 * @param Vertice origem - Recebem um v�rtice de origem
	 * @param Vertice destino - Recebe um v�rtice de destino
	 * @return Aresta procurada - Retorna a aresta desejada*/
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

	/**
	 * Este m�todo calcula o(s) menor(es) caminho(s) entre dois pontos
	 * @param int partida - Recebe o ponto inicial
	 * @param int chegada - Recebe o ponto final
	 * @return List<List<Vertice>> menoresCaminhos - Retorna uma lista de menores caminhos*/
	public List<List<Vertice>> menorCaminho(int partida,int chegada){

		menoresCaminhos = new ArrayList<List<Integer>>();
		trajetos = new ArrayList<Trajeto>();
		matrizAdjacencia = transformaEmMatriz();
		double custo[] = new double[listaVertices.size()];
		List<Integer> anterior = new ArrayList<Integer>();
		List<Integer> naoVisitados = new ArrayList<Integer>();

		custo[partida]=0;

		for(int i=0;i<matrizAdjacencia.length;i++){
			if(i!=partida){
				custo[i] = Double.MAX_VALUE;
			}
			naoVisitados.add(i);
		}
		Trajeto trajetoInicial = new Trajeto();
		trajetoInicial.setOrigem(-1);
		trajetoInicial.setDestino(partida);
		trajetoInicial.setCusto(0);
		trajetos.add(trajetoInicial);
		anterior.add(-1);

		int indiceVizinhoProximo = 0;
		while(!naoVisitados.isEmpty()){
			indiceVizinhoProximo = obterVizinhoProximo(custo, naoVisitados);
			Iterator iterador = naoVisitados.iterator();
			int index = 0;
			while(iterador.hasNext()){
				if(naoVisitados.get(index)== indiceVizinhoProximo){
					break;
				}
				index++;
			}

			naoVisitados.remove(index);
			List<Integer> vizinhos = encontrarVizinhos(indiceVizinhoProximo);

			for(Integer vizinho: vizinhos){
				double custoTotal = custo[indiceVizinhoProximo]+ matrizAdjacencia[indiceVizinhoProximo][vizinho];
				if(custoTotal<=custo[vizinho]){
					custo[vizinho] = custoTotal;
					anterior.add(indiceVizinhoProximo);
					Trajeto trajeto = new Trajeto();
					trajeto.setCusto(custo[vizinho]);
					trajeto.setOrigem(indiceVizinhoProximo);
					trajeto.setDestino(vizinho);
					trajetos.add(trajeto);
				}
			}

			if(indiceVizinhoProximo == chegada){
				construirListaMenorCaminho(new ArrayList<>(),indiceVizinhoProximo);
				return encontrarVerticesMenorCaminho();
			}
		}	
		return Collections.emptyList();
	}
	/**
	 * Este m�todo encontra os v�rtices mais pr�ximos de determinado v�rtice
	 * @param int vertice - Recebe o v�rtice principal
	 * @return List<Integer> vizinhos - Retorna uma lista de v�rtices vizinhos do v�rtice principal*/
	public List<Integer> encontrarVizinhos(int vertice){
		List<Integer> vizinhos = new ArrayList<Integer>();
		for(int j=0;j < matrizAdjacencia[vertice].length;j++){
			double valor2 = matrizAdjacencia[vertice][j];
			if(matrizAdjacencia[vertice][j] > 0){
				vizinhos.add(j);
			}
		}
		return vizinhos;
	}

	/**
	 * Este m�todo verifica o v�rtice mais pr�ximo 
	 * @param double[] custo - Recebe o custo do valor
	 * @param List<Integer> naoVisitados - Recebe uma lista de v�rtices n�o visitados
	 * @return int indiceMinimo - Retorna o v�rtice mais pr�ximo*/
	public int obterVizinhoProximo(double[]custo,List<Integer> naoVisitados){
		double pesoMinimo = Double.MAX_VALUE;
		int indiceMinimo = 0;

		for(Integer i:naoVisitados){
			if(custo[i] < pesoMinimo){
				pesoMinimo = custo[i];
				indiceMinimo = i;
			}
		}
		return indiceMinimo;
	}
	
	/**
	 * Este m�todo converte a lista de adjac�ncias em uma matriz de adjac�ncias
	 * @return double[][] matrizAdj - Retorna uma matriz com custos */
	public double[][] transformaEmMatriz(){

		double[][] matrizAdj = new double[listaVertices.size()][listaVertices.size()];

		for(int i=0;i<matrizAdj.length;i++){
			for(int j=0;j<matrizAdj.length;j++){
				matrizAdj[i][j] = 0;
				matrizAdj[j][i] = 0;
			}
		}

		for(int i=0;i<listaVertices.size();i++){
			for(int j=0;j<listaVertices.get(i).getListaAdj().size();j++){
				Vertice procurado = listaVertices.get(i).getListaAdj().get(j).getDestino();
				double peso = listaVertices.get(i).getListaAdj().get(j).getTempo();
				int index = listaVertices.indexOf(procurado);
				if(i!=j){
					matrizAdj[i][index] = peso;
					matrizAdj[index][i] = peso;
				} 
			}
		}

		return matrizAdj;
	}


	/**
	 * Este m�todo constr�i as listas com os menores caminhos poss�veis
	 * @param List<Integer> caminho - Recebe uma arrayList
	 * @param int vizinhoMaisProximo - Recebe o v�rtice mais pr�ximo da posi��o atual
	 * @return List<Integer> - Retorna uma lista de v�rtices*/
	public List<Integer> construirListaMenorCaminho(List<Integer> caminho,int vizinhoMaisProximo){
		if(vizinhoMaisProximo == -1){
			return caminho;
		}else{
			caminho.add(vizinhoMaisProximo);
			for(Trajeto trajeto:trajetos){
				if(trajeto.getDestino() == vizinhoMaisProximo){
					construirListaMenorCaminho(new ArrayList<>(caminho), trajeto.getOrigem());
					if(caminho.get(caminho.size()-1) == trajetos.get(0).getDestino()){
						menoresCaminhos.add(caminho);
					}

				}
			}
		}
		Collections.reverse(caminho);
		return caminho;
	}
	
	/**
	 * Este m�todo encontra o v�rtice atrav�s do seu �ndice na matriz de adjac�ncia
	 * @return List<List<Vertice>> verticesMenorCaminho
	 * */
	public List<List<Vertice>> encontrarVerticesMenorCaminho(){

		List<List<Vertice>> verticesMenorCaminho = new ArrayList<>();
		for(List<Integer> menorCaminho:menoresCaminhos){
			List<Vertice> caminhoAtual = new ArrayList<>();
			for(Integer vertice:menorCaminho){
				caminhoAtual.add(listaVertices.get(vertice));
			}
			verticesMenorCaminho.add(caminhoAtual);
		}

		return verticesMenorCaminho;
	}
}
