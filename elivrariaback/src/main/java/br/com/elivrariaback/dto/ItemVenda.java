package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "venda_item")
public class ItemVenda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "livro_id")
	private Livro livro;
	
	@ManyToOne
	@JoinColumn(name = "venda_detalhe_id")
	private VendaDetalhe vendaDetalhe;


	@Column (name = "preco_compra")
	private double compraPreco;
	
	@Column (name = "livro_qtd")
	private int qtdLivro;
	
	private double total;

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

	public VendaDetalhe getVendaDetalhe() {
		return vendaDetalhe;
	}

	public void setVendaDetalhe(VendaDetalhe vendaDetalhe) {
		this.vendaDetalhe = vendaDetalhe;
	}

	public double getCompraPreco() {
		return compraPreco;
	}

	public void setCompraPreco(double compraPreco) {
		this.compraPreco = compraPreco;
	}

	public int getQtdLivro() {
		return qtdLivro;
	}

	public void setQtdLivro(int qtdLivro) {
		this.qtdLivro = qtdLivro;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	
		
}
