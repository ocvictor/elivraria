package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import br.com.elivrariaback.util.CartaoSerializer;


import br.com.elivrariaback.util.VendaDetalheSerializer;
@JsonSerialize(using = CartaoSerializer.class)
@Entity
public class Cartao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Bandeira bandeira;
	
	@Column(name = "usuario_id")
	private int usuarioId;

	@NotNull(message = "Insira o Numero do Cartão!")
	@Column(name = "numero")
	private long numeroCartao;
	
	@NotBlank(message = "Insira o Nome no Cartão!")
	@Column(name = "nome")
	private String nomeCartao;
	
	@NotNull(message = "Insira o Mês de Vencimento!")
	@Column(name = "vencimento_mes")
	private int mesVencimento;
	
	@NotNull(message = "Insira o Ano de Vencimento!")
	@Column(name = "vencimento_ano")
	private int anoVencimento;
	
	@NotNull(message = "Insira o CCV!")
	@Column(name = "ccv")
	private int ccv;
	
	@Override
	public String toString() {
		return "Cartao [id=" + id + ",numero=" + numeroCartao + ",nome=" + nomeCartao
				+ ", vencimento_mes=" + mesVencimento + ",vencimento_ano=" + anoVencimento + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public long getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeCartao() {
		return nomeCartao;
	}

	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}

	public int getMesVencimento() {
		return mesVencimento;
	}

	public void setMesVencimento(int mesVencimento) {
		this.mesVencimento = mesVencimento;
	}

	public int getAnoVencimento() {
		return anoVencimento;
	}

	public void setAnoVencimento(int anoVencimento) {
		this.anoVencimento = anoVencimento;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}
	
	
}
