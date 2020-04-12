package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Estoque;

public interface EstoqueDAO {

	List<Estoque> getByLivro(int livroId);
	List<Estoque> listarTodos();
	
	Object getByLivroDataZero(int livroId);
	
	Estoque get(int estoqueId);
	boolean add(Estoque estoque);
	boolean update(Estoque estoque);


}


