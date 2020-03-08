package br.com.elivrariafront.model;

import java.io.Serializable;
import java.util.List;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariaback.dto.Usuario;

public class CheckoutModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario user;
	private Endereco shipping;
	private Carrinho cart;
	private List<ItemCarrinho> cartLines;
	private VendaDetalhe orderDetail;
	private double checkoutTotal;

	public VendaDetalhe getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(VendaDetalhe orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Carrinho getCart() {
		return cart;
	}

	public void setCart(Carrinho cart) {
		this.cart = cart;
	}

	public double getCheckoutTotal() {
		return checkoutTotal;
	}

	public void setCheckoutTotal(double checkoutTotal) {
		this.checkoutTotal = checkoutTotal;
	}

	public List<ItemCarrinho> getCartLines() {
		return cartLines;
	}

	public void setCartLines(List<ItemCarrinho> cartLines) {
		this.cartLines = cartLines;
	}


	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Endereco getShipping() {
		return shipping;
	}

	public void setShipping(Endereco shipping) {
		this.shipping = shipping;
	}
	
}
