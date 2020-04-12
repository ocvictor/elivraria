package br.com.elivrariafront.exception;

import java.io.Serializable;

public class LivroNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public LivroNotFoundException() {
		this("Livro n�o est� dispon�vel!");
	}
	
	public LivroNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}
}
