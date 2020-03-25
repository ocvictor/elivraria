package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Insira o Tipo de Residência!")
	@Column(name = "tiporesidencia")
	private String tipoResidencia;
	@NotBlank(message = "Insira o Tipo de Logradouro!")
	@Column(name = "tipologradouro")
	private String tipoLogradouro;
	@NotBlank(message = "Insira o Logradouro!")
	@Column(name = "logradouro")
	private String logradouro;
	@NotBlank(message = "Insira o Numero!")
	@Column(name = "numero")
	private String numero;	
	@Column(name = "complemento")
	private String complemento;
	@NotBlank(message = "Insira o bairro!")	
	@Column(name = "bairro")
	private String bairro;
	@NotBlank(message = "Insira a Cidade!")	
	private String cidade;
	@NotBlank(message = "Insira o Estado!")	
	private String estado;
	@NotBlank(message = "Insira o Pais!")	
	private String pais;
	@Column(name ="cep")
	@NotBlank(message = "Insira o CEP!")	
	private String cep;
	@Column(name="entrega")
	private boolean entrega;
	@Column(name="cobranca")
	private boolean cobranca;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public boolean isEntrega() {
		return entrega;
	}
	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}
	public boolean isCobranca() {
		return cobranca;
	}
	public void setCobranca(boolean cobranca) {
		this.cobranca = cobranca;
	}
	
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getTipoResidencia() {
		return tipoResidencia;
	}
	public void setTipoResidencia(String tipoResidencia) {
		this.tipoResidencia = tipoResidencia;
	}
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}
	@Override
	public String toString() {
		return "Endereco [id=" + id + ",tiporesidencia=" + tipoResidencia + ",tipologrdouro=" + tipoLogradouro 
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", cep=" + cep
				+ ", cobranca=" + cobranca + "]";
	}
		
	@Column(name = "usuario_id")
	private int usuarioId;
	
	public boolean entrega() {
		return entrega;
	}

	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
