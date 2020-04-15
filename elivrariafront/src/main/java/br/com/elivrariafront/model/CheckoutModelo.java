package br.com.elivrariafront.model;

import java.io.Serializable;
import java.util.List;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.CartaoValidador;
import br.com.elivrariaback.dto.CupomPromocional;
import br.com.elivrariaback.dto.CupomTroca;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariaback.dto.Usuario;

public class CheckoutModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double valorFrete;
	private Usuario usuario;
	private Endereco endereco;
	private Carrinho carrinho;
	private Cartao cartao;
	private CartaoValidador cartaoValidador;
	private List<ItemCarrinho> itemCarrinho;
	private VendaDetalhe vendaDetalhe;
	private double checkoutTotal;
	private CupomTroca cupomTroca;
	private CupomPromocional cupomPromocional;
	
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
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	public CartaoValidador getCartaoValidador() {
		return cartaoValidador;
	}
	public void setCartaoValidador(CartaoValidador cartaoValidador) {
		this.cartaoValidador = cartaoValidador;
	}
	public Double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
	public CupomTroca getCupomTroca() {
		return cupomTroca;
	}
	public void setCupomTroca(CupomTroca cupomTroca) {
		this.cupomTroca = cupomTroca;
	}
	public CupomPromocional getCupomPromocional() {
		return cupomPromocional;
	}
	public void setCupomPromocional(CupomPromocional cupomPromocional) {
		this.cupomPromocional = cupomPromocional;
	}
	
	
}
