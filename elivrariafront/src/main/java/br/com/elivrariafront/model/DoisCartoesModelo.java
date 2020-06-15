package br.com.elivrariafront.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;

public class DoisCartoesModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Cartao primeiroCartao;
	private Cartao segundoCartao;
	
	private double valorPrimeiroCartao;
	private double valorSegundoCartao;
	
	
	public Cartao getPrimeiroCartao() {
		return primeiroCartao;
	}
	public void setPrimeiroCartao(Cartao primeiroCartao) {
		this.primeiroCartao = primeiroCartao;
	}
	public Cartao getSegundoCartao() {
		return segundoCartao;
	}
	public void setSegundoCartao(Cartao segundoCartao) {
		this.segundoCartao = segundoCartao;
	}
	public double getValorPrimeiroCartao() {
		return valorPrimeiroCartao;
	}
	public void setValorPrimeiroCartao(double valorPrimeiroCartao) {
		this.valorPrimeiroCartao = valorPrimeiroCartao;
	}
	public double getValorSegundoCartao() {
		return valorSegundoCartao;
	}
	public void setValorSegundoCartao(double valorSegundoCartao) {
		this.valorSegundoCartao = valorSegundoCartao;
	}

	
	
	
	
}
