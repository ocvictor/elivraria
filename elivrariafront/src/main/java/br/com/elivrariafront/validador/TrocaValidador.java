package br.com.elivrariafront.validador;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dao.ItemVendaDAO;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.GrupoPrecificacao;
import br.com.elivrariaback.dto.ItemVenda;
import br.com.elivrariaback.dto.Troca;
import br.com.elivrariafront.controller.GerenciamentoController;

public class TrocaValidador implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(TrocaValidador.class);
	
	@Autowired
	ItemVendaDAO itemVendaDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return Troca.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		

		Troca troca = (Troca) target;
		
	
		if (troca.getQtdTroca() <= 0) {
			errors.rejectValue("qtdTroca", null, "Quantidade para troca não pode ser zero!");
			return;
		}
		
		if (troca.getQtdTroca() > troca.getItemVenda().getQtdLivro()) {
			errors.rejectValue("qtdTroca", null, "Quantidade para troca Inválida!");
			return;
		}

	}

}
