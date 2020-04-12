package br.com.elivrariafront.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.EstoqueModelo;
import br.com.elivrariafront.model.RegistroModelo;

@Component
public class EstoqueHandler {
	
	@Autowired
	private EstoqueDAO estoqueDAO;
	
	@Autowired
	private LivroDAO livroDAO;
	
	public EstoqueModelo init() { 
		return new EstoqueModelo();
	} 
	
	public void addLivro(EstoqueModelo estoqueModelo, Livro livro) {
		estoqueModelo.setLivro(livro);
	}
	
	public void addEstoque(EstoqueModelo estoqueModelo, Estoque estoque) {
		estoqueModelo.setEstoque(estoque);
	}
	public String saveAll(EstoqueModelo estoqueModelo) {
		  String transitionValue = "sucesso";
		  Estoque estoque = estoqueModelo.getEstoque();		  
		  return transitionValue ;
	} 

}
