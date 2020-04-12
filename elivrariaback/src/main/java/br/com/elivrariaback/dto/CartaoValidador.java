package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cartao_validador")
public class CartaoValidador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "bandeira_id")
	private int bandeiraId;

	@Column(name = "numero")
	private long numeroCartao;
	
	@Column(name = "nome")
	private String nomeCartao;
	
	@Column(name = "vencimento_mes")
	private int mesVencimento;
	
	@Column(name = "vencimento_ano")
	private int anoVencimento;
	
	@Column(name = "ccv")
	private int ccv;
	
	@Column(name = "limite")
	private double limite;
	
	@Override
	public String toString() {
		return "CartaoValidador [id=" + id + ",numero=" + numeroCartao + ",nome=" + nomeCartao
				+ ", vencimento_mes=" + mesVencimento + ",vencimento_ano=" + anoVencimento + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBandeiraId() {
		return bandeiraId;
	}

	public void setBandeiraId(int bandeiraId) {
		this.bandeiraId = bandeiraId;
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

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}


	
}
