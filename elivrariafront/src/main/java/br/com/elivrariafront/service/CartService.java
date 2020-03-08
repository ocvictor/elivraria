package br.com.elivrariafront.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elivrariaback.dao.ItemCarrinhoDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariafront.model.UsuarioModelo;

@Service("cartService")
public class CartService {

	@Autowired
	private ItemCarrinhoDAO cartLineDAO;
	
	@Autowired
	private LivroDAO productDAO;
		
	@Autowired
	private HttpSession session;
	
	public List<ItemCarrinho> getCartLines() {

		return cartLineDAO.list(getCart().getId());

	}
	
	/* to update the cart count */
	public String manageCartLine(int cartLineId, int count) {
		
		ItemCarrinho cartLine = cartLineDAO.get(cartLineId);		

		double oldTotal = cartLine.getTotal();

		
		Livro product = cartLine.getProduct();
		
		// check if that much quantity is available or not
		if(product.getQuantidade() < count) {
			return "result=unavailable";		
		}	
		
		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getPrecoUnit());
		cartLine.setTotal(product.getPrecoUnit() * count);
		cartLineDAO.update(cartLine);

	
		// update the cart
		Carrinho cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartLineDAO.updateCarrinho(cart);
		
		return "result=updated";
	}



	public String addCartLine(int productId) {		
		Carrinho cart = this.getCart();
		String response = null;
		ItemCarrinho cartLine = cartLineDAO.getPorCarrinhoProduto(cart.getId(), productId);
		if(cartLine==null) {
			// add a new cartLine if a new product is getting added
			cartLine = new ItemCarrinho();
			Livro product = productDAO.get(productId);
			// transfer the product details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getPrecoUnit());
			cartLine.setTotal(product.getPrecoUnit());
			
			// insert a new cartLine
			cartLineDAO.add(cartLine);
			
			// update the cart
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
			cartLineDAO.updateCarrinho(cart);

			response = "result=added";						
		} 
		else {
			// check if the cartLine has been already reached to maximum count
			if(cartLine.getProductCount() < 3) {
				// call the manageCartLine method to increase the count
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);				
			}			
			else {				
				response = "result=maximum";				
			}						
		}		
		return response;
	}
	
	private Carrinho getCart() {
		return ((UsuarioModelo)session.getAttribute("userModel")).getCarrinho();
	}


	public String removeCartLine(int cartLineId) {
		
		ItemCarrinho cartLine = cartLineDAO.get(cartLineId);
		// deduct the cart
		// update the cart
		Carrinho cart = this.getCart();	
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);		
		cartLineDAO.updateCarrinho(cart);
		
		// remove the cartLine
		cartLineDAO.remove(cartLine);
				
		return "result=deleted";
	}


	public String validateCartLine() {
		Carrinho cart = this.getCart();
		List<ItemCarrinho> cartLines = cartLineDAO.list(cart.getId());
		double grandTotal = 0.0;
		int lineCount = 0;
		String response = "result=success";
		boolean changed = false;
		Livro product = null;
		for(ItemCarrinho cartLine : cartLines) {					
			product = cartLine.getProduct();
			changed = false;
			// check if the product is active or not
			// if it is not active make the availability of cartLine as false
			if((!product.isAtivo() && product.getQuantidade() == 0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
			}			
			// check if the cartLine is not available
			// check whether the product is active and has at least one quantity available
			if((product.isAtivo() && product.getQuantidade() > 0) && !(cartLine.isAvailable())) {
					cartLine.setAvailable(true);
					changed = true;
			}
			
			// check if the buying price of product has been changed
			if(cartLine.getBuyingPrice() != product.getPrecoUnit()) {
				// set the buying price to the new price
				cartLine.setBuyingPrice(product.getPrecoUnit());
				// calculate and set the new total
				cartLine.setTotal(cartLine.getProductCount() * product.getPrecoUnit());
				changed = true;				
			}
			
			// check if that much quantity of product is available or not
			if(cartLine.getProductCount() > product.getQuantidade()) {
				cartLine.setProductCount(product.getQuantidade());										
				cartLine.setTotal(cartLine.getProductCount() * product.getPrecoUnit());
				changed = true;
				
			}
			
			// changes has happened
			if(changed) {				
				//update the cartLine
				cartLineDAO.update(cartLine);
				// set the result as modified
				response = "result=modified";
			}
			
			grandTotal += cartLine.getTotal();
			lineCount++;
		}
		
		cart.setCartLines(lineCount++);
		cart.setGrandTotal(grandTotal);
		cartLineDAO.updateCarrinho(cart);

		return response;
	}	
}
