package br.com.elivrariaback.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Component
@Entity
public class Estoque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "data_entrada")
	private String dataEntrada;
	
	@Column(name = "valor_custo")
	@Min(value = 0, message="Insira o Valor de Custo!")
	private double valorCusto;
	
	private int quantidade;
	
	@Column(name = "livro_id")
	private int livroId;
	
	@Column(name = "fornecedor_id")
	@JsonIgnore
	private int fornecedorId;
		
	@Column(name = "tipo_operacao")
	private String tpoOperacao;
	
	@JsonIgnore
	@Column(name = "flg_zerado")
	private boolean flgZerado;

	public Estoque() {		
		
	}		
	

	@Override
	public String toString() {
		return "Estoque [id=" + id + ", data_entrada=" + dataEntrada + ", valor_custo=" + valorCusto 
				+ ", quantidade=" + quantidade + ", flg_zerado=" + flgZerado + ", tipo_operacao=" + tpoOperacao
				+ ", livro_id=" + livroId + ", fornecedor_id=" + fornecedorId  + "]";
	}
	
	
	public String getTpoOperacao() {
		return tpoOperacao;
	}

	public void setTpoOperacao(String tpoOperacao) {
		this.tpoOperacao = tpoOperacao;
	}

	public boolean isFlgZerado() {
		return flgZerado;
	}

	public void setFlgZerado(boolean flgZerado) {
		this.flgZerado = flgZerado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getLivroId() {
		return livroId;
	}

	public void setLivroId(int livroId) {
		this.livroId = livroId;
	}

	public int getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}


}
