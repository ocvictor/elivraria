package br.com.elivrariafront.validador;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dao.ItemVendaDAO;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.CartaoValidador;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.GrupoPrecificacao;
import br.com.elivrariaback.dto.ItemVenda;
import br.com.elivrariaback.dto.StatusVenda;
import br.com.elivrariaback.dto.Troca;
import br.com.elivrariafront.controller.GerenciamentoController;
import br.com.elivrariafront.model.DoisCartoesModelo;

public class DoisCartaoValidador {
	
	@Autowired
	private CartaoDAO cartaoDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(DoisCartaoValidador.class);

	public String validar(Object target, String result) {		

		DoisCartoesModelo doisCartoes = (DoisCartoesModelo) target;
		
		if(doisCartoes.getPrimeiroCartao().getId() == doisCartoes.getSegundoCartao().getId()) {
			result = "erroCartao";
			return result;
		}
		if (doisCartoes.getValorPrimeiroCartao() < 10) {
			result = "erroValorPrimeiro";
			return result;
		}
		
		if (doisCartoes.getValorSegundoCartao() < 10) {
			result = "erroValorSegundo";
			return result;
		}
		
		
		return result;

	}

}
