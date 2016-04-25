package br.uefs.vrum.controller;

import static org.junit.Assert.*;

import org.junit.*;

import br.uefs.vrum.exceptions.arestaInexistenteException;
import br.uefs.vrum.exceptions.verticeInexistenteException;
import br.uefs.vrum.util.*;

public class ControllerTeste {

	Controller controller;
	@Before
	public void setUp(){
		controller = new Controller();
	}
	@Test
	public void adicionarVerticeSucesso(){
		controller.adicionarVertice("Alameda Slim");
		
		try{
			controller.recuperarVertice("Alameda Slim");
		}catch(verticeInexistenteException e){
			fail();
		}
	}
	@Test
	public void adicionarArestaSucesso(){
		
		Vertice verticeOrigem = CriarObjetos.criaVertice("Aurora");
		Vertice verticeDestino = CriarObjetos.criaVertice("Boreal");
		
		controller.adicionarVertice("Aurora");
		controller.adicionarVertice("Boreal");
		Aresta aresta = CriarObjetos.criaAresta(verticeOrigem, verticeDestino, 10);
		
		controller.adicionarAresta(verticeOrigem,verticeDestino,10);
		
		
		try{
			String recebida = controller.recuperarAresta("Aurora","Boreal").getOrigem().getIndice();
			String esperada = aresta.getOrigem().getIndice();
			assertEquals(esperada,recebida);
		}catch(verticeInexistenteException e){
			fail();
		} catch (arestaInexistenteException e) {
			fail();
		}
	}
	@Test
	public void recuperarVerticeSucesso(){
		
	}
	@Test
	public void recuperarArestaSucesso(){
		
	}
	@Test
	public void removerVerticeSucesso(){
		
	}
	@Test
	public void removerArestaSucesso(){
		
	}
}
