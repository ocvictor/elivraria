package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.elivrariaback.util.TrocaSerializer;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = TrocaSerializer.class)
@Entity
@Table(name = "troca")
public class Troca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="data_solicitacao")
	private String dataSolicitacao;
	
	@Column(name = "livro_id")
	private int livroId;
	
	@OneToOne
	@JoinColumn(name = "venda_detalhe_id")
	private VendaDetalhe vendaDetalhe;
	
	@OneToOne
	@JoinColumn(name = "venda_item_id")
	private ItemVenda itemVenda;
	
	@Column(name = "status_troca_id")
	private int statusTrocaId;
	
	@Column(name = "motivo")
	@NotBlank(message="Insira o motivo da troca")
	private String motivo;
	
	@Column(name="quantidade_troca")
	@NotNull(message="Insira a quantidade a ser trocada")
	private int qtdTroca;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLivroId() {
		return livroId;
	}

	public void setLivroId(int livroId) {
		this.livroId = livroId;
	}



	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public int getStatusTrocaId() {
		return statusTrocaId;
	}

	public void setStatusTrocaId(int statusTrocaId) {
		this.statusTrocaId = statusTrocaId;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public int getQtdTroca() {
		return qtdTroca;
	}

	public void setQtdTroca(int qtdTroca) {
		this.qtdTroca = qtdTroca;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}	
	
	
	public VendaDetalhe getVendaDetalhe() {
		return vendaDetalhe;
	}

	public void setVendaDetalhe(VendaDetalhe vendaDetalhe) {
		this.vendaDetalhe = vendaDetalhe;
	}


}
