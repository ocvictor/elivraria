package br.com.elivrariaback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dto.Livro;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static LivroDAO livroDAO;
	
	
	private Livro product;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("br.com.elivrariaback");
		context.refresh();
		livroDAO = (LivroDAO)context.getBean("livroDAO");
	}
	
	
	@Test
	public void testeListaAtivosLivros() {
		assertEquals("Erro na lista de livros ativos!",
				5,livroDAO.listaAtivosLivros().size());				
	} 
	
	
	@Test
	public void testeListaAtivosLivrosPorCategoria() {
		assertEquals("Erro na lista de livros !",
				3,livroDAO.listaAtivosLivroCategoria(3).size());
		assertEquals("Erro na lista de livros !",
				2,livroDAO.listaAtivosLivroCategoria(1).size());
	} 
	
	@Test
	public void testeGetUltimosAtivosLivros() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,livroDAO.getUltimosAtivosLivros(3).size());
		
	} 
	
	
	
		
}
