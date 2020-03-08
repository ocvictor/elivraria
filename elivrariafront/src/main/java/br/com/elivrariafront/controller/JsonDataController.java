package br.com.elivrariafront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dto.Livro;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private LivroDAO livroDAO;
	

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
		
	@RequestMapping("/mc/products")
	@ResponseBody
	public List<Livro> getLivrosMaisComprados() {		
		return livroDAO.getLivrosByParam("compras", 5);				
	}
	
	
	
	
}
