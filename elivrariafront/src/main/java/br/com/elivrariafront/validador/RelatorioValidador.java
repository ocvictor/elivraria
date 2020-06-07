package br.com.elivrariafront.validador;


import java.text.SimpleDateFormat;
import java.util.Date;

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
import br.com.elivrariafront.model.RelatorioModelo;

public class RelatorioValidador implements Validator {
	
	private static final Logger logger = LoggerFactory.getLogger(RelatorioValidador.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Date dataInicial;
	private Date dataFinal;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RelatorioModelo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		

		RelatorioModelo relatorioModelo = (RelatorioModelo) target;
		try {
			dataInicial = sdf.parse(relatorioModelo.getDataInicial());
		}catch(Exception e){
			errors.rejectValue("DataInicial", null, "Não foi possivel converter a data inicial");
			return ;
		}
		try {
			dataFinal = sdf.parse(relatorioModelo.getDataFinal());
		}catch(Exception e){
			errors.rejectValue("DataInicial", null, "Não foi possivel converter a data Final");
			return ;
		}
		
	
		if (dataInicial.after(dataFinal)) {
			errors.rejectValue("DataInicial", null, "Data inicial não pode ser maior que a Data final");
			return;
		}
	}

}
