package br.com.elivrariafront.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.UsuarioModelo;

@ControllerAdvice
public class GlobalController {
	
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private HttpSession session;
	
	private UsuarioModelo usuarioModelo = null;
	private Usuario usuario = null;	
	
	
	@ModelAttribute("usuarioModelo")
	public UsuarioModelo getUserModel() {		
		if(session.getAttribute("usuarioModelo")==null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
			if(!authentication.getPrincipal().equals("anonymousUser")){
				usuario = usuarioDAO.getByEmail(authentication.getName());
				
				if(usuario!=null) {
					usuarioModelo = new UsuarioModelo();
					usuarioModelo.setId(usuario.getId());
					usuarioModelo.setNomeCompleto(usuario.getNome() + " " + usuario.getSobrenome());
					usuarioModelo.setRole(usuario.getRole());
					
					if(usuario.getRole().equals("USER")) {
						usuarioModelo.setCarrinho(usuario.getCarrinho());					
					}				
	
					session.setAttribute("usuarioModelo", usuarioModelo);
					return usuarioModelo;
				}			
			}
		}
		
		return (UsuarioModelo) session.getAttribute("usuarioModelo");		
	}
		
}
