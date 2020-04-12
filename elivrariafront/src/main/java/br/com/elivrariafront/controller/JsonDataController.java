package br.com.elivrariafront.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariaback.dto.VendaDetalhe;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonDataController.class);


	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private EstoqueDAO estoqueDAO;
	
	@Autowired
	private VendaDetalheDAO vendaDetalheDAO;
	
	@RequestMapping("/admin/todos/livros")
	@ResponseBody
	public List<Livro> getTodosLivrosLista() {		
		return livroDAO.list();
				
	}	
	
	
	@RequestMapping("/todos/livros")
	@ResponseBody
	public List<Livro> getTodosLivros() {
		
		return livroDAO.listaAtivosLivros();
				
	}
	
	@RequestMapping("/categoria/{id}/livros")
	@ResponseBody
	public List<Livro> getLivrosPorCategoria(@PathVariable int id) {
		
		return livroDAO.listaAtivosLivroCategoria(id);
				
	}
	
	
	@RequestMapping("/mv/livros")
	@ResponseBody
	public List<Livro> getLivrosMaisVistos() {		
		return livroDAO.getLivrosByParam("visualizacoes", 5);				
	}
		
	@RequestMapping("/mc/livros")
	@ResponseBody
	public List<Livro> getLivrosMaisComprados() {		
		return livroDAO.getLivrosByParam("compras", 5);				
	}
	
	@RequestMapping("/admin/todos/usuarios")
	@ResponseBody
	public List<Usuario> getTodosUsuariosLista() {		
		return usuarioDAO.listUsuarios();
				
	}
	
	@RequestMapping("/admin/todos/estoques")
	@ResponseBody
	public List<Estoque> getTodosEstoquesLista() {
		return estoqueDAO.listarTodos();				
	}
	
	@RequestMapping("/admin/vendas/aprovadas")
	@ResponseBody
	public List<VendaDetalhe> getVendasAprovadas() {
		return vendaDetalheDAO.getVendasAprovadas();				
	}
}
