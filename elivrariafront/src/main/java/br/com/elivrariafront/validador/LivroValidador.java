package br.com.elivrariafront.validador;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.elivrariaback.dto.Livro;

public class LivroValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Livro.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Livro livro = (Livro) target;
		if(livro.getFile() == null || livro.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Selecione uma imagem!");
			return;
		}
		if(! (livro.getFile().getContentType().equals("image/jpeg") || 
				livro.getFile().getContentType().equals("image/png")) ||
				livro.getFile().getContentType().equals("image/gif")
			 )
			{
				errors.rejectValue("file", null, "Selecione uma imagem!");
				return;	
			}

	}

}
