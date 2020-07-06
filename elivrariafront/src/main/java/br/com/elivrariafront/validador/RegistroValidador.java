package br.com.elivrariafront.validador;

import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.daoimpl.UsuarioDAOImpl;
import br.com.elivrariaback.dto.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class RegistroValidador {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistroValidador.class);

	public String validate(Object target) {
		
		Usuario usuario = (Usuario) target;
		
		String returnValue = "success";

		boolean hasUpperCase = !usuario.getSenha().equals(usuario.getSenha().toLowerCase());
		boolean hasLowercase = !usuario.getSenha().equals(usuario.getSenha().toUpperCase());
		boolean isAtLeast8   = usuario.getSenha().length() >= 8;
		boolean hasSpecial   = !usuario.getSenha().matches("[A-Za-z0-9 ]*");
		
		
		if(!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
			returnValue = "erroConfirmarSenha";
			return returnValue;
		} 	
   
		if(!isAtLeast8) {		
			returnValue = "senha8caracteres";
			return returnValue;
		}
		
		if (!hasLowercase) {
			returnValue = "senhaMinusculo";
			return returnValue;
		}
   
		if(!hasUpperCase) {
			returnValue = "senhaMaiusculo";
			return returnValue;
		}
		
		if(!hasSpecial) {
			returnValue = "senhaEspecial";
			return returnValue;
		}
		return returnValue;
		
	}
}
