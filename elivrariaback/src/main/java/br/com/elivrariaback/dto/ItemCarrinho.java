package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemcarrinho")
public class ItemCarrinho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Livro livro;
	@Column(name = "carrinho_id")
	private int carrinhoId;	
	@Column(name = "livro_qtd")
	private int livro_qtd;
	private double total;
	@Column(name = "preco_compra")
	private double precoCompra;
	
	public double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}
	
	@Column(name = "disponivel")
	private boolean disponivel = true;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public int getCarrinhoId() {
		return carrinhoId;
	}
	public void setCarrinhoId(int carrinhoId) {
		this.carrinhoId = carrinhoId;
	}

	public int getLivroQtd() {
		return livro_qtd;
	}
	public void setLivroQtd(int livro_qtd) {
		this.livro_qtd = livro_qtd;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
		
	
}
