package br.com.elivrariafront.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.elivrariaback.dto.Carrinho;

public class ErroModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}
