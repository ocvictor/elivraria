package br.com.elivrariafront.validador;

import br.com.elivrariafront.model.DoisCartoesModelo;

public class DoisCartaoValidador {
	

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
