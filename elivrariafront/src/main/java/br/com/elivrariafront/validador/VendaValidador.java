package br.com.elivrariafront.validador;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.CartaoValidador;
import br.com.elivrariafront.model.CheckoutModelo;

@Component
public class VendaValidador {
	
	private static final Logger logger = LoggerFactory.getLogger(VendaValidador.class);

	@Autowired
	private CartaoDAO cartaoDAO;
	
	@Autowired
	private BandeiraDAO bandeiraDAO;
	
	
	public String validate(CheckoutModelo checkoutModelo) {
		
		String returnValue = "success";		
		
		double limite = checkoutModelo.getCartaoValidador().getLimite();
		double total = checkoutModelo.getCheckoutTotal();		

		if(Double.compare(limite, total) < 0) {			
			returnValue = "error";
			return returnValue;
		}
		return returnValue;
	}

}
