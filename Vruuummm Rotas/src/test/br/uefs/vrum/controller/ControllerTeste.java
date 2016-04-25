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
		
		Vertice vertice = CriarObjetos.criaVertice("Alameda Slim");
		controller.adicionarVertice("Alameda Slim");
		
		try{
			controller.recuperarVertice("Alameda Slim");
		}catch(verticeInexistenteException e){
			fail();
		}
	}
	@Test
	public void recuperarArestaSucesso(){
		
		Vertice origem = CriarObjetos.criaVertice("Aurora");
		Vertice destino = CriarObjetos.criaVertice("Boreal");
		Aresta aresta = CriarObjetos.criaAresta(origem, destino, 10);
		
		controller.adicionarVertice("Aurora");
		controller.adicionarVertice("Boreal");
		controller.adicionarAresta(origem, destino, 10);
		
		try {
			String recebida = controller.recuperarAresta("Aurora","Boreal").getOrigem().getIndice();
			String esperada = aresta.getOrigem().getIndice();
			assertEquals(esperada,recebida);
		} catch (verticeInexistenteException | arestaInexistenteException e) {
			fail();
		}
	}
	@Test
	public void removerVerticeSucesso(){
		
		controller.adicionarVertice("Alameda Slim");
		try{
			controller.recuperarVertice("Alameda Slim");
		}catch(verticeInexistenteException e){
			fail();
		}
		try {
			controller.removerVertice("Alameda Slim");
		} catch (verticeInexistenteException e) {
			fail();
		}
		try {
			controller.recuperarVertice("Alameda Slim");
		} catch (verticeInexistenteException e) {
			assertTrue(true);
		}
	}
	@Test
	public void removerArestaSucesso(){
		
		Vertice origem = CriarObjetos.criaVertice("Aurora");
		Vertice destino = CriarObjetos.criaVertice("Boreal");
		Aresta aresta = CriarObjetos.criaAresta(origem, destino, 10);
		
		controller.adicionarVertice("Aurora");
		controller.adicionarVertice("Boreal");
		controller.adicionarAresta(origem, destino, 10);
		
		try {
			String recebida = controller.recuperarAresta("Aurora","Boreal").getOrigem().getIndice();
			String esperada = aresta.getOrigem().getIndice();
		} catch (verticeInexistenteException | arestaInexistenteException e) {
			fail();
		}
		try {
			controller.removerAresta("Aurora","Boreal");
		} catch (verticeInexistenteException | arestaInexistenteException e) {
			fail();
		}
		try {
			controller.recuperarAresta("Aurora","Boreal");
		} catch (verticeInexistenteException | arestaInexistenteException e) {
			assertTrue(true);
		}
	}
}
