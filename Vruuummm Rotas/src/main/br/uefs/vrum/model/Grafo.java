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
	private List<List<Integer>> menoresCaminhos = new ArrayList<>();
	List<Trajeto> trajetos = new ArrayList<Trajeto>();
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
	
	public List<Integer> menorCaminho(int partida,int chegada){
		
		matrizAdjacencia = transformaEmMatriz();
		double custo[] = new double[listaVertices.size()];
		//int[] anterior = new int[listaVertices.size()];
		List<Integer> anterior = new ArrayList<Integer>();
		List<Integer> naoVisitados = new ArrayList<Integer>();
		
		custo[partida]=0;
		
		for(int i=0;i<matrizAdjacencia.length;i++){
			if(i!=partida){
				custo[i] = Double.MAX_VALUE;
			}
			//anterior[i] = -1;
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
				return construirListaMenorCaminho(new ArrayList<>(),indiceVizinhoProximo);
			}
		}	
		return construirListaMenorCaminho(anterior, indiceVizinhoProximo);
	}
	
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
}
