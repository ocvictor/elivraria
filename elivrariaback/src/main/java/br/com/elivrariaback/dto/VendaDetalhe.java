package br.com.elivrariaback.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.elivrariaback.util.VendaDetalheSerializer;

@JsonSerialize(using = VendaDetalheSerializer.class)
@Component
@Entity
@Table(name = "venda_detalhe")
public class VendaDetalhe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@Column(name = "venda_total")
	private double totalVenda;
	
	@JsonIgnore
	@ManyToOne
	private Endereco enderecoEntrega;
	
	@JsonIgnore
	@ManyToOne
	private Endereco enderecoCobranca;
	
	@JsonIgnore
	@OneToMany(mappedBy="vendaDetalhe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ItemVenda> itemVenda = new ArrayList<>();
	
	@Column(name = "venda_qtd")
	private int qtdVenda;
	
	@Column(name="venda_data")
	private String dataVenda;
	
	@Column(name="valor_frete")
	private double valorFrete;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cartao_um_id")
	private Cartao cartaoUm;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cartao_dois_id")
	private Cartao cartaoDois;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cupom_troca_id")
	private CupomTroca cupomTroca;
	
	@ManyToOne
	@JoinColumn(name="status_venda_id")
	private StatusVenda statusVenda;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cupom_promocional_id")
	private CupomPromocional cupomPromocional;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(double totalVenda) {
		this.totalVenda = totalVenda;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public List<ItemVenda> getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(List<ItemVenda> itemVenda) {
		this.itemVenda = itemVenda;
	}

	public int getQtdVenda() {
		return qtdVenda;
	}

	public void setQtdVenda(int qtdVenda) {
		this.qtdVenda = qtdVenda;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public Cartao getCartaoUm() {
		return cartaoUm;
	}

	public void setCartaoUm(Cartao cartaoUm) {
		this.cartaoUm = cartaoUm;
	}

	public Cartao getCartaoDois() {
		return cartaoDois;
	}

	public void setCartaoDois(Cartao cartaoDois) {
		this.cartaoDois = cartaoDois;
	}

	public StatusVenda getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(StatusVenda statusVenda) {
		this.statusVenda = statusVenda;
	}

	public double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(double valorFrete) {
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
