package br.com.elivrariafront.model;

import java.io.Serializable;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Usuario;

public class RegistroModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Usuario usuario;
	private Endereco endereco;
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
		
}
