package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Livro;

public interface LivroDAO {

	Livro get(int livroId);
	List<Livro> list();	
	boolean add(Livro livro);
	boolean update(Livro livro);
	boolean delete(Livro livro);

	List<Livro> getLivrosByParam(String param, int count);	
	
	
	List<Livro> listaAtivosLivros();	
	List<Livro> listaAtivosLivroCategoria(int categoryId);
	List<Livro> getUltimosAtivosLivros(int count);
	
}