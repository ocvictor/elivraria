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
	
/*	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("absr@gmail.com");		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(2);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);
		
		double oldTotal = cartLine.getTotal();		
		
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		
		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
		assertEquals("Failed to update the cart!",true, userDAO.updateCart(cart));
		
	}
	
	*/
	
	@Test
	public void testUpdateCartLine() {

		// fetch the user and then cart of that user
		Usuario usuario = usuarioDAO.getByEmail("vaco@gmail.com");		
		Carrinho carrinho = usuario.getCarrinho();
				
		itemCarrinho = itemCarrinhoDAO.getPorCarrinhoProduto(carrinho.getId(), 2);
		
		itemCarrinho.setProductCount(itemCarrinho.getProductCount() + 1);
				
		double oldTotal = itemCarrinho.getTotal();
				
		itemCarrinho.setTotal(itemCarrinho.getProduct().getPrecoUnit() * itemCarrinho.getProductCount());
		
		carrinho.setGrandTotal(carrinho.getGrandTotal() + (itemCarrinho.getTotal() - oldTotal));
		
		assertEquals("Falha ao atualizar itemCarrinho!",true, itemCarrinhoDAO.update(itemCarrinho));	

		
	}
	
	
	
}
