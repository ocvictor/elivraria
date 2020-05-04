package br.com.elivrariaback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elivrariaback.dao.ItemCarrinhoDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.Usuario;

public class ItemCarrinhoTest {

	

	private static AnnotationConfigApplicationContext context;
	
	
	private static ItemCarrinhoDAO itemCarrinhoDAO;
	private static LivroDAO livroDAO;
	private static UsuarioDAO usuarioDAO;
	
	
	private ItemCarrinho itemCarrinho = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("br.com.elivrariaback");
		context.refresh();
		itemCarrinhoDAO = (ItemCarrinhoDAO)context.getBean("itemCarrinhoDAO");
		livroDAO = (LivroDAO)context.getBean("livroDAO");
		usuarioDAO = (UsuarioDAO)context.getBean("usuarioDAO");
	}
	
	
	@Test
	public void testUpdateCartLine() {

		Usuario usuario = usuarioDAO.getByEmail("vaco@gmail.com");		
		Carrinho carrinho = usuario.getCarrinho();
				
		itemCarrinho = itemCarrinhoDAO.getPorCarrinhoProduto(carrinho.getId(), 2);
		
		itemCarrinho.setLivroQtd(itemCarrinho.getLivroQtd() + 1);
				
		double oldTotal = itemCarrinho.getTotal();
				
		itemCarrinho.setTotal(itemCarrinho.getLivro().getPrecoUnit() * itemCarrinho.getLivroQtd());
		
		carrinho.setTotal(carrinho.getTotal() + (itemCarrinho.getTotal() - oldTotal));
		
		assertEquals("Falha ao atualizar itemCarrinho!",true, itemCarrinhoDAO.update(itemCarrinho));	

		
	}
	
	
	
}
