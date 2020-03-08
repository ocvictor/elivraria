package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Categoria;

public interface CategoriaDAO {

	
	
	Categoria get(int id);
	List<Categoria> list();
	boolean add(Categoria categoria);
	boolean update(Categoria categoria);
	boolean delete(Categoria categoria);
	
	
}
