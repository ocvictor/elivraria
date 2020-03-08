package br.com.elivrariaback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dto.Categoria;

public class CategoriaTeste {

	private static AnnotationConfigApplicationContext context;
	
	
	private static CategoriaDAO categoryDAO;
	
	
	private Categoria category;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("br.com.elivrariaback");
		context.refresh();
		categoryDAO = (CategoriaDAO)context.getBean("categoryDAO");
	}
	
	
/*	@Test
	public void testAddCategory() {
		
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is some description for laptop!");
		category.setImageURL("CAT_105.png");
		
		assertEquals("Successfully added a category inside the table!",true,categoryDAO.add(category));
		
		
	}
	*/
	
/*	@Test
	public void testGetCategory() {
		
		category = categoryDAO.get(3);
		
		
		assertEquals("Successfully fetched a single category from the table!","Television",category.getName());
		
		
	}
	*/
	
/*	@Test
	public void testUpdateCategory() {
		
		category = categoryDAO.get(3);
		
		category.setName("TV");
		
		assertEquals("Successfully updated a single category in the table!",true,categoryDAO.update(category));
		
		
	}
	*/

/*	@Test
	public void testDeleteCategory() {
		
		category = categoryDAO.get(3);		
		assertEquals("Successfully deleted a single category in the table!",true,categoryDAO.delete(category));
		
		
	}
*/	
/*	
	@Test
	public void testListCategory() {
					
		assertEquals("Successfully fetched the list of categories from the table!",3,categoryDAO.list().size());
		
		
	}
	
*/
	
	@Test
	public void testCRUDCategory() {
		
		// add operation
		category = new Categoria();
		
		category.setNome("Ficção");
		category.setDescricao("Essa é uma descrição!");
		category.setImagemURL("CAT_1.png");
		
		assertEquals("Categoria cadastrada com Sucesso!",true,categoryDAO.add(category));
		
		
		category = new Categoria();
		
		category.setNome("Administração");
		category.setDescricao("Essa é uma descricao!");
		category.setImagemURL("CAT_2.png");
		
		assertEquals("Categoria cadastrada com Sucesso!",true,categoryDAO.add(category));

		
		// fetching and updating the category
		category = categoryDAO.get(2);
		
		category.setNome("Auto-Ajuda");
		
		assertEquals("Categoria atualizada com Sucesso!",true,categoryDAO.update(category));
		
		
		// delete the category
		assertEquals("Categoria deletada com Sucesso!",true,categoryDAO.delete(category));
		
		
		//fetching the list
		assertEquals("Categorias listadas com Sucesso!",1,categoryDAO.list().size());		
				
		
	}
	
	
}
