package br.com.elivrariafront.model;

import java.io.Serializable;

import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Livro;

public class EstoqueModelo implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	private Estoque estoque;
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

}
