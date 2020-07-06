package br.com.elivrariafront.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dao.EnderecoDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.CartaoModelo;
import br.com.elivrariafront.model.RegistroModelo;
import br.com.elivrariafront.validador.RegistroValidador;

@Component
public class RegistroHandler {

private static final Logger logger = LoggerFactory.getLogger(RegistroHandler.class);

 @Autowired
 private PasswordEncoder passwordEncoder;
	
	
 @Autowired
 private UsuarioDAO usuarioDAO;
 
 @Autowired
 private BandeiraDAO bandeiraDAO;
 
 @Autowired
 private CartaoDAO cartaoDAO;
 
 @Autowired
 private EnderecoDAO enderecoDAO; 
 
 public RegistroModelo init() { 
  return new RegistroModelo();
 } 

 public void addUsuario(RegistroModelo registroModelo, Usuario usuario) {
  registroModelo.setUsuario(usuario);
 } 
 public void addEndereco(RegistroModelo registroModelo, Endereco endereco) {
  registroModelo.setEndereco(endereco);
 }
 
 public String addCartao(RegistroModelo registroModelo, CartaoModelo cartaoModelo) {
	 String transitionValue = "success";

	Cartao cartao = new Cartao();
	
	Bandeira bandeira = bandeiraDAO.get(cartaoModelo.getBandeiraId());
	 
	cartao.setBandeira(bandeira);
	cartao.setUsuarioId(registroModelo.getUsuario().getId());
	cartao.setAnoVencimento(cartaoModelo.getAnoVencimento());
	cartao.setCcv(cartaoModelo.getCcv());
	cartao.setMesVencimento(cartaoModelo.getMesVencimento());
	cartao.setNomeCartao(cartaoModelo.getNomeCartao());
	cartao.setNumeroCartao(cartaoModelo.getNumeroCartao());
	
	cartao.setBandeira(bandeira);
	registroModelo.setCartao(cartao);
	
	return transitionValue;
	
 }
 
 public String validarUsuario(Usuario usuario, MessageContext error) {
	 error.clearMessages();

	 String validacaoRegistro = new RegistroValidador().validate(usuario);
	 
	  String transitionValue = "sucesso";
	  
	  if(usuarioDAO.getByEmail(usuario.getEmail())!=null) {
	    error.addMessage(new MessageBuilder().error().source(
	      "email").defaultText("Email já utilizado!").build());
	    transitionValue = "falha";
	  }
	  
	  if(validacaoRegistro.equals("erroConfirmarSenha")) {
		  error.addMessage(new MessageBuilder().error().source(
				  "confirmaSenha").defaultText("Senhas não batem!").build());
		  transitionValue = "falha";	        
		}	 
		
		 
		if(validacaoRegistro.equals("senha8caracteres")) {	
			error.addMessage(new MessageBuilder().error().source(
			  "senha").defaultText("Senha deve conter mais que 8 caractéres").build());
			transitionValue = "falha";
		}
		 
		if (validacaoRegistro.equals("senhaMinusculo")) {			 
			   error.addMessage(new MessageBuilder().error().source(
			   "senha").defaultText("Senha deve conter pelo menos 1 caracter minúsculo").build());
	   transitionValue = "falha";
			   
		}
		if(validacaoRegistro.equals("senhaMaiusculo")) {
			   error.addMessage(new MessageBuilder().error().source(
			   "senha").defaultText("Senha deve conter pelo menos 1 caracter maiúsculo").build());
	   transitionValue = "falha";
			   
		}
		if(validacaoRegistro.equals("senhaEspecial")) {
			   error.addMessage(new MessageBuilder().error().source(
			   "senha").defaultText("Senha deve conter pelo menos 1 caracter especial").build());
	   transitionValue = "falha";
		}
		
//	  boolean hasUpperCase = !usuario.getSenha().equals(usuario.getSenha().toLowerCase());
//	  boolean hasLowercase = !usuario.getSenha().equals(usuario.getSenha().toUpperCase());
//	  boolean isAtLeast8   = usuario.getSenha().length() >= 8;
//	  boolean hasSpecial   = !usuario.getSenha().matches("[A-Za-z0-9 ]*");
//
//	   
//	   if(usuarioDAO.getByEmail(usuario.getEmail())!=null) {
//	    error.addMessage(new MessageBuilder().error().source(
//	      "email").defaultText("Email já utilizado!").build());
//	    transitionValue = "falha";
//	   }
//	   if(!isAtLeast8) {
//		   error.addMessage(new MessageBuilder().error().source(
//				   "senha").defaultText("Senha deve conter mais que 8 caractéres").build());
//		   transitionValue = "falha";
//	   }
//	   if (!hasLowercase) {
//		   error.addMessage(new MessageBuilder().error().source(
//				   "senha").defaultText("Senha deve conter pelo menos 1 caracter minúsculo").build());
//		   transitionValue = "falha";
//		   
//	   }
//	   if(!hasUpperCase) {
//		   error.addMessage(new MessageBuilder().error().source(
//				   "senha").defaultText("Senha deve conter pelo menos 1 caracter maiúsculo").build());
//		   transitionValue = "falha";
//		   
//	   }
//	   if(!hasSpecial) {
//		   error.addMessage(new MessageBuilder().error().source(
//				   "senha").defaultText("Senha deve conter pelo menos 1 caracter especial").build());
//		   transitionValue = "falha";
//		   
//	   }
	   return transitionValue;
 }
 
public List<Bandeira> getBandeiras(RegistroModelo model) {
	
	List<Bandeira> bandeiras = bandeiraDAO.list();
	
	if(bandeiras.size() == 0) {
		bandeiras = new ArrayList<>();
	}

	
	return bandeiras;
	
}
 
 public String saveAll(RegistroModelo registroModelo) {
  String transitionValue = "sucesso";
  Usuario usuario = registroModelo.getUsuario();
  if(usuario.getRole().equals("USER")) {
   Carrinho carrinho = new Carrinho();
   carrinho.setUsuario(usuario);
   usuario.setCarrinho(carrinho);
  }
   
  usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
  
  usuarioDAO.add(usuario);
  Endereco endereco = registroModelo.getEndereco();
  endereco.setUsuarioId(usuario.getId());
  endereco.setCobranca(true); 
  enderecoDAO.addEndereco(endereco);
  Cartao cartao = registroModelo.getCartao();
  cartao.setUsuarioId(usuario.getId());
  cartaoDAO.addCartao(cartao);
  return transitionValue ;
 } 
}
