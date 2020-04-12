package br.com.elivrariafront.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.elivrariaback.dto.Carrinho;

public class CartaoModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Insira a bandeira do Cart�o")
	private int bandeiraId;
	
	@NotNull(message = "Insira o Numero do Cart�o!")
	private long numeroCartao;
	
	@NotBlank(message = "Insira o Nome no Cart�o!")
	private String nomeCartao;
	
	@NotNull(message = "Insira o M�s de Vencimento!")
	private int mesVencimento;
	
	@NotNull(message = "Insira o Ano de Vencimento!")
	private int anoVencimento;
	
	@NotNull(message = "Insira o CCV!")
	private int ccv;

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
	
			
	
	
}
