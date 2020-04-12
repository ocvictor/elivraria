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
	
	public String gerenciarItemCarrinho(int itemCarrinhoID, int count) {
		
		ItemCarrinho itemCarrinho = itemCarrinhoDAO.get(itemCarrinhoID);		

		double oldTotal = itemCarrinho.getTotal();

		
		Livro livro = itemCarrinho.getLivro();
		
		if(livro.getQuantidade() < count) {
			return "result=unavailable";		
		}	
		
		itemCarrinho.setLivroQtd(count);
		itemCarrinho.setPrecoCompra(livro.getPrecoUnit());
		itemCarrinho.setTotal(livro.getPrecoUnit() * count);
		itemCarrinhoDAO.update(itemCarrinho);

	
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
			itemCarrinho = new ItemCarrinho();
			Livro livro = productDAO.get(livroId);
			itemCarrinho.setCarrinhoId(carrinho.getId());
			itemCarrinho.setLivro(livro);
			itemCarrinho.setLivroQtd(1);
			itemCarrinho.setPrecoCompra(livro.getPrecoUnit());
			itemCarrinho.setTotal(livro.getPrecoUnit());
			
			itemCarrinhoDAO.add(itemCarrinho);
			
			carrinho.setTotal(carrinho.getTotal() + itemCarrinho.getTotal());
			carrinho.setItens(carrinho.getItens() + 1);
			itemCarrinhoDAO.updateCarrinho(carrinho);

			response = "result=added";						
		} 
		else {
			if(itemCarrinho.getLivroQtd() < 3) {
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
		Carrinho carrinho = this.getCarrinho();	
		carrinho.setTotal(carrinho.getTotal() - itemCarrinho.getTotal());
		carrinho.setItens(carrinho.getItens() - 1);		
		itemCarrinhoDAO.updateCarrinho(carrinho);
		
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
			if((!livro.isAtivo() && livro.getQuantidade() == 0) && itemCarrinho.isDisponivel()) {
				itemCarrinho.setDisponivel(false);
				changed = true;
			}			
			if((livro.isAtivo() && livro.getQuantidade() > 0) && !(itemCarrinho.isDisponivel())) {
					itemCarrinho.setDisponivel(true);
					changed = true;
			}
			
			if(itemCarrinho.getPrecoCompra() != livro.getPrecoUnit()) {
				itemCarrinho.setPrecoCompra(livro.getPrecoUnit());
				itemCarrinho.setTotal(itemCarrinho.getLivroQtd() * livro.getPrecoUnit());
				changed = true;				
			}
			
			if(itemCarrinho.getLivroQtd() > livro.getQuantidade()) {
				itemCarrinho.setLivroQtd(livro.getQuantidade());										
				itemCarrinho.setTotal(itemCarrinho.getLivroQtd() * livro.getPrecoUnit());
				changed = true;
				
			}
			
			if(changed) {				
				itemCarrinhoDAO.update(itemCarrinho);
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
