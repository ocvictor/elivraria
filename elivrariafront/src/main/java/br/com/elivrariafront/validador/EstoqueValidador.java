package br.com.elivrariafront.validador;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.GrupoPrecificacao;
import br.com.elivrariafront.controller.GerenciamentoController;

public class EstoqueValidador implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(EstoqueValidador.class);
	
	@Autowired
	GrupoPrecificacaoDAO grpDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return Estoque.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		Estoque estoque = (Estoque) target;
		
		if (estoque.getQuantidade() <= 0) {
			errors.rejectValue("quantidade", null, "Quantidade não pode ser zero!");
			return;
		}
		
		if (estoque.getDataEntrada().isEmpty() || estoque.getDataEntrada().isBlank()) {
			errors.rejectValue("Data Entrada", null, "Data entrada deve ser preenchida!");
			return;
		}

	}

}
