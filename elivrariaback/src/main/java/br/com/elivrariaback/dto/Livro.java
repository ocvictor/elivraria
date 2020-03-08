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

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Component
@Entity
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// private fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Insira o ISBN!")
	private String ISBN;
	@NotBlank(message = "Insira o Titulo!")
	private String titulo;
	@NotBlank(message = "Insira a Editora!")
	private String editora;
	@NotBlank(message = "Insira o Autor!")
	private String autor;
	@Column(name = "precoUnit")
	@Min(value = 1, message="Insira o Preco Unitario!")
	private double precoUnit;
	private int quantidade;
	@Column(name = "ativo")	
	private boolean ativo;
	@Column(name = "categoria_id")
	@JsonIgnore
	private int categoriaId;
	@Column(name = "fornecedor_id")
	@JsonIgnore
	private int fornecedorId;
	private int compras;
	private int visualizacoes;
	
	
	@Transient
	private MultipartFile file;
			
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}


	// default constructor
	public Livro() {
		
		
		
	}
	
	
	// setters and getters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(double precoUnit) {
		this.precoUnit = precoUnit;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public int getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(int fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public int getCompras() {
		return compras;
	}

	public void setCompras(int compras) {
		this.compras = compras;
	}

	public int getVisualizacoes() {
		return visualizacoes;
	}

	public void setVisualizacoes(int visualizacoes) {
		this.visualizacoes = visualizacoes;
	}

	// toString for debugging
	@Override
	public String toString() {
		return "Livro [id=" + id + ", isbn=" + ISBN + ", titulo=" + titulo + ", editora=" + editora + ", autor="
				+ autor + ", precoUnit=" + precoUnit + ", quantidade=" + quantidade + ", ativo=" + ativo
				+ ", categoriaId=" + categoriaId + ", fornecedorID=" + fornecedorId + ", compras=" + compras + ", visualizacoes="
				+ visualizacoes + "]";
	}
}
