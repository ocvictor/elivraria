package br.com.elivrariafront.model;

import java.io.Serializable;
import java.util.List;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariaback.dto.Usuario;

public class CheckoutModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Endereco endereco;
	private Carrinho carrinho;
	private List<ItemCarrinho> itemCarrinho;
	private VendaDetalhe vendaDetalhe;
	private double checkoutTotal;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public List<ItemCarrinho> getItemCarrinho() {
		return itemCarrinho;
	}
	public void setItemCarrinho(List<ItemCarrinho> itemCarrinho) {
		this.itemCarrinho = itemCarrinho;
	}
	public VendaDetalhe getVendaDetalhe() {
		return vendaDetalhe;
	}
	public void setVendaDetalhe(VendaDetalhe vendaDetalhe) {
		this.vendaDetalhe = vendaDetalhe;
	}
	public double getCheckoutTotal() {
		return checkoutTotal;
	}
	public void setCheckoutTotal(double checkoutTotal) {
		this.checkoutTotal = checkoutTotal;
	}

	
	
}
