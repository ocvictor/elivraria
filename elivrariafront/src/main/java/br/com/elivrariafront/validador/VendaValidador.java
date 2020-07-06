package br.com.elivrariafront.validador;

import org.springframework.stereotype.Component;
import br.com.elivrariafront.model.CheckoutModelo;

@Component
public class VendaValidador {
	

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
