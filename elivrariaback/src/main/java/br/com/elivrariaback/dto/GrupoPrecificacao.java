package br.com.elivrariaback.dto;



import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "grupo_precificacao")
public class GrupoPrecificacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "descricao")
	@NotBlank(message = "Insira a descricao do grupo!")
	private String descricao;
	
	@Column(name = "percentual_lucro")
	@NotBlank(message = "Insira o percentual de lucro!")
	private double percentualLucro;
	

	public GrupoPrecificacao() {		
		
	}	
	
	
	@Override
	public String toString() {
		return "GrupoPrecificacao [id=" + id + ", descricao=" + descricao +  ", percentual_lucro=" + percentualLucro + "]";
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		descricao = descricao + " - " + String.valueOf(percentualLucro * 100) + "%";
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPercentualLucro() {
		return percentualLucro;
	}

	public void setPercentualLucro(double percentualLucro) {
		this.percentualLucro = percentualLucro;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
	