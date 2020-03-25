package br.com.elivrariafront.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.CheckoutModel;
import br.com.elivrariafront.model.RegistroModelo;

@Component
public class RegistroHandler {


 @Autowired
 private PasswordEncoder passwordEncoder;
	
	
 @Autowired
 private UsuarioDAO usuarioDAO;
 
 @Autowired
 private BandeiraDAO bandeiraDAO;
 
 public RegistroModelo init() { 
  return new RegistroModelo();
 } 
 public void addUsuario(RegistroModelo registroModelo, Usuario usuario) {
  registroModelo.setUsuario(usuario);
 } 
 public void addEndereco(RegistroModelo registroModelo, Endereco endereco) {
  registroModelo.setEndereco(endereco);
 }
 
 public void addCartao(RegistroModelo registroModelo, Cartao cartao) {
	  registroModelo.setCartao(cartao);
	 }
 

 public String validarUsuario(Usuario usuario, MessageContext error) {
  String transitionValue = "sucesso";
   if(!usuario.getSenha().equals(usuario.getConfirmaSenha())) {
    error.addMessage(new MessageBuilder().error().source(
      "confirmarSenha").defaultText("Senhas não correspondem!").build());
    transitionValue = "falha";    
   }  
   if(usuarioDAO.getByEmail(usuario.getEmail())!=null) {
    error.addMessage(new MessageBuilder().error().source(
      "email").defaultText("Email já utilizado!").build());
    transitionValue = "falha";
   }
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
  usuarioDAO.addEndereco(endereco);
  Cartao cartao = registroModelo.getCartao();
  cartao.setUsuarioId(usuario.getId());
  usuarioDAO.addCartao(cartao);
  return transitionValue ;
 } 
}
