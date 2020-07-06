package br.com.elivrariafront.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "troca")
public class TrocaModelo implements Serializable {

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
	
	@Column(name = "venda_item_id")
	private int itemVendaId;
	
	@Column(name = "venda_detalhe_id")
	private int vendaDetalheId;
	
	@Column(name = "usuario_id")
	private int usuarioId;
	
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




	public int getItemVendaId() {
		return itemVendaId;
	}

	public void setItemVendaId(int itemVendaId) {
		this.itemVendaId = itemVendaId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
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

	public int getVendaDetalheId() {
		return vendaDetalheId;
	}

	public void setVendaDetalheId(int vendaDetalheId) {
		this.vendaDetalheId = vendaDetalheId;
	}	
			
}
