package br.com.elivrariafront.model;

import java.io.Serializable;

import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Usuario;

public class RegistroModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Usuario usuario;
	private Endereco endereco;
	private Cartao cartao;
	
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
	public Cartao getCartao() {
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
		
}
