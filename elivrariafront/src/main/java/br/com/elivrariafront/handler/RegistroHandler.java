package br.com.elivrariafront.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.RegistroModelo;

@Component
public class RegistroHandler {


 @Autowired
 private PasswordEncoder passwordEncoder;
	
	
 @Autowired
 private UsuarioDAO usuarioDAO;
 public RegistroModelo init() { 
  return new RegistroModelo();
 } 
 public void addUsuario(RegistroModelo registroModelo, Usuario usuario) {
  registroModelo.setUsuario(usuario);
 } 
 public void addEndereco(RegistroModelo registroModelo, Endereco endereco) {
  registroModelo.setEndereco(endereco);
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
 
 public String saveAll(RegistroModelo registroModelo) {
  String transitionValue = "success";
  Usuario usuario = registroModelo.getUsuario();
  if(usuario.getRole().equals("USER")) {
   Carrinho carrinho = new Carrinho();
   carrinho.setUsuario(usuario);
   usuario.setCarrinho(carrinho);
  }
   
  usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
  
  usuarioDAO.add(usuario);
  Endereco endereco = registroModelo.getEndereco();
  endereco.setUsuariorId(usuario.getId());
  endereco.setCobranca(true);  
  usuarioDAO.addEndereco(endereco);
  return transitionValue ;
 } 
}
