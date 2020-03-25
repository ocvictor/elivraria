package br.com.elivrariafront.model;

import java.io.Serializable;

import br.com.elivrariaback.dto.Carrinho;

public class UsuarioModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nomeCompleto;
	private String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private Carrinho carrinho;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	
}
