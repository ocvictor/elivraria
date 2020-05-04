package br.com.elivrariaback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dto.Categoria;

public class CategoriaTeste {

	private static AnnotationConfigApplicationContext context;
	
	
	private static CategoriaDAO categoriaDAO;
	
	
	private Categoria categoria;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("br.com.elivrariaback");
		context.refresh();
		categoriaDAO = (CategoriaDAO)context.getBean("categoriaDAO");
	}
	

	
	@Test
	public void testCRUDCategory() {
		
		// add operation
		categoria = new Categoria();
		
		categoria.setNome("Ficção");
		categoria.setDescricao("Essa é uma descrição!");
		categoria.setImagemURL("CAT_1.png");
		
		assertEquals("Categoria cadastrada com Sucesso!",true,categoriaDAO.add(categoria));
		
		
		categoria = new Categoria();
		
		categoria.setNome("Administração");
		categoria.setDescricao("Essa é uma descricao!");
		categoria.setImagemURL("CAT_2.png");
		
		assertEquals("Categoria cadastrada com Sucesso!",true,categoriaDAO.add(categoria));

		
		categoria = categoriaDAO.get(2);
		
		categoria.setNome("Auto-Ajuda");
		
		assertEquals("Categoria atualizada com Sucesso!",true,categoriaDAO.update(categoria));
		
		
		assertEquals("Categoria deletada com Sucesso!",true,categoriaDAO.delete(categoria));
		
		
		assertEquals("Categorias listadas com Sucesso!",1,categoriaDAO.list().size());		
				
		
	}
	
	
}
