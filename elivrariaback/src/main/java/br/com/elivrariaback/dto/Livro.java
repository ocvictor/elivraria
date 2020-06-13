package br.com.elivrariaback.dto;



import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Component
@Entity
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	private double precoUnit;
	
	@NotNull(message = "Insira o Ano!")
	private int ano;
	@NotNull(message = "Insira a Edição!")
	private String edicao;
	@NotNull(message = "Insira o Numero de Paginas!")
	private int numPaginas;
	@NotBlank(message = "Insira a Sinopse!")
	private String sinopse;
	@NotNull(message = "Insira a altura!")
	private double altura;
	@NotNull(message = "Insira a largura!")
	private double largura;
	@NotNull(message = "Insira a profundidade!")
	private double profundidade;
	@NotNull(message = "Insira o peso!")
	private double peso;
	@NotNull(message = "Insira o codigo de barras!")
	private double codBarras;
		
	private int quantidade;
	@Column(name = "ativo")	
	private boolean ativo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoria_id")	
	private Categoria categoria;
	
	@Column(name = "grupo_precificacao_id")
	@JsonIgnore
	private int grupoPrecificacaoId;
	
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


	public Livro() {		
		
	}
	
	
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



	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	
	
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(double codBarras) {
		this.codBarras = codBarras;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", isbn=" + ISBN + ", titulo=" + titulo + ", editora=" + editora + ", autor="
				+ autor + ", precoUnit=" + precoUnit + ", quantidade=" + quantidade + ", ativo=" + ativo
				+ ", ano=" + ano + ", edicao=" + edicao + ", codbarras=" + codBarras + ", numpaginas= " + numPaginas
				+ ", sinopse= " + sinopse + ", largura= " + largura + ", altura=" + altura + ", profundidade=" + profundidade + ", peso=" + peso
				+ ", categoriaId=" + categoria + ", compras=" + compras + ", visualizacoes="
				+ visualizacoes + ", grupo_precificacao_id=" + grupoPrecificacaoId + "]";
	}
	
	public int getGrupoPrecificacaoId() {
		return grupoPrecificacaoId;
	}

	public void setGrupoPrecificacaoId(int grupoPrecificacaoId) {
		this.grupoPrecificacaoId = grupoPrecificacaoId;
	}
}
