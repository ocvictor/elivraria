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
public class CarrinhoService {

	@Autowired
	private ItemCarrinhoDAO itemCarrinhoDAO;
	
	@Autowired
	private LivroDAO productDAO;
		
	@Autowired
	private HttpSession session;
	
	public List<ItemCarrinho> getItensCarrinho() {

		return itemCarrinhoDAO.list(getCarrinho().getId());

	}
	
	/* to update the cart count */
	public String gerenciarItemCarrinho(int itemCarrinhoID, int count) {
		
		ItemCarrinho itemCarrinho = itemCarrinhoDAO.get(itemCarrinhoID);		

		double oldTotal = itemCarrinho.getTotal();

		
		Livro livro = itemCarrinho.getLivro();
		
		// check if that much quantity is available or not
		if(livro.getQuantidade() < count) {
			return "result=unavailable";		
		}	
		
		// update the cart line
		itemCarrinho.setLivroQtd(count);
		itemCarrinho.setPrecoCompra(livro.getPrecoUnit());
		itemCarrinho.setTotal(livro.getPrecoUnit() * count);
		itemCarrinhoDAO.update(itemCarrinho);

	
		// update the cart
		Carrinho carrinho = this.getCarrinho();
		carrinho.setTotal(carrinho.getTotal() - oldTotal + itemCarrinho.getTotal());
		itemCarrinhoDAO.updateCarrinho(carrinho);
		
		return "result=updated";
	}



	public String addItemCarrinho(int livroId) {		
		Carrinho carrinho = this.getCarrinho();
		String response = null;
		ItemCarrinho itemCarrinho = itemCarrinhoDAO.getPorCarrinhoProduto(carrinho.getId(), livroId);
		if(itemCarrinho==null) {
			// add a new cartLine if a new product is getting added
			itemCarrinho = new ItemCarrinho();
			Livro livro = productDAO.get(livroId);
			// transfer the product details to cartLine
			itemCarrinho.setCarrinhoId(carrinho.getId());
			itemCarrinho.setLivro(livro);
			itemCarrinho.setLivroQtd(1);
			itemCarrinho.setPrecoCompra(livro.getPrecoUnit());
			itemCarrinho.setTotal(livro.getPrecoUnit());
			
			// insert a new cartLine
			itemCarrinhoDAO.add(itemCarrinho);
			
			// update the cart
			carrinho.setTotal(carrinho.getTotal() + itemCarrinho.getTotal());
			carrinho.setItens(carrinho.getItens() + 1);
			itemCarrinhoDAO.updateCarrinho(carrinho);

			response = "result=added";						
		} 
		else {
			// check if the cartLine has been already reached to maximum count
			if(itemCarrinho.getLivroQtd() < 3) {
				// call the manageCartLine method to increase the count
				response = this.gerenciarItemCarrinho(itemCarrinho.getId(), itemCarrinho.getLivroQtd() + 1);				
			}			
			else {				
				response = "result=maximum";				
			}						
		}		
		return response;
	}
	
	private Carrinho getCarrinho() {
		return ((UsuarioModelo)session.getAttribute("usuarioModelo")).getCarrinho();
	}


	public String removeItemCarrinho(int itemCarrinhoId) {
		
		ItemCarrinho itemCarrinho = itemCarrinhoDAO.get(itemCarrinhoId);
		// deduct the cart
		// update the cart
		Carrinho carrinho = this.getCarrinho();	
		carrinho.setTotal(carrinho.getTotal() - itemCarrinho.getTotal());
		carrinho.setItens(carrinho.getItens() - 1);		
		itemCarrinhoDAO.updateCarrinho(carrinho);
		
		// remove the cartLine
		itemCarrinhoDAO.remove(itemCarrinho);
				
		return "result=deleted";
	}


	public String validarItemCarrinho() {
		Carrinho carrinho = this.getCarrinho();
		List<ItemCarrinho> itensCarrinho = itemCarrinhoDAO.list(carrinho.getId());
		double Total = 0.0;
		int lineCount = 0;
		String response = "result=success";
		boolean changed = false;
		Livro livro = null;
		for(ItemCarrinho itemCarrinho : itensCarrinho) {					
			livro = itemCarrinho.getLivro();
			changed = false;
			// check if the product is active or not
			// if it is not active make the availability of cartLine as false
			if((!livro.isAtivo() && livro.getQuantidade() == 0) && itemCarrinho.isDisponivel()) {
				itemCarrinho.setDisponivel(false);
				changed = true;
			}			
			// check if the cartLine is not available
			// check whether the product is active and has at least one quantity available
			if((livro.isAtivo() && livro.getQuantidade() > 0) && !(itemCarrinho.isDisponivel())) {
					itemCarrinho.setDisponivel(true);
					changed = true;
			}
			
			// check if the buying price of product has been changed
			if(itemCarrinho.getPrecoCompra() != livro.getPrecoUnit()) {
				// set the buying price to the new price
				itemCarrinho.setPrecoCompra(livro.getPrecoUnit());
				// calculate and set the new total
				itemCarrinho.setTotal(itemCarrinho.getLivroQtd() * livro.getPrecoUnit());
				changed = true;				
			}
			
			// check if that much quantity of product is available or not
			if(itemCarrinho.getLivroQtd() > livro.getQuantidade()) {
				itemCarrinho.setLivroQtd(livro.getQuantidade());										
				itemCarrinho.setTotal(itemCarrinho.getLivroQtd() * livro.getPrecoUnit());
				changed = true;
				
			}
			
			// changes has happened
			if(changed) {				
				//update the cartLine
				itemCarrinhoDAO.update(itemCarrinho);
				// set the result as modified
				response = "result=modified";
			}
			
			Total += itemCarrinho.getTotal();
			lineCount++;
		}
		
		carrinho.setItens(lineCount++);
		carrinho.setTotal(Total);
		itemCarrinhoDAO.updateCarrinho(carrinho);

		return response;
	}	
}
