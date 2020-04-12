package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

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
	
	@Column(name = "livro_id")
	private int livroId;
	
	@Column(name = "venda_item_id")
	private int vendaItemId;
	
	@Column(name = "usuario_id")
	private int usuarioId;
	
	@Column(name = "status_troca_id")
	private int statusTrocaId;
	
	@Column(name = "motivo")
	@NotBlank(message="Insira o motivo da troca")
	private String motivo;

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

	public int getVendaItemId() {
		return vendaItemId;
	}

	public void setVendaItemId(int vendaItemId) {
		this.vendaItemId = vendaItemId;
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
			
	
}
