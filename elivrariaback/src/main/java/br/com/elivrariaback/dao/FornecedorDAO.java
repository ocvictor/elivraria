package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Fornecedor;

public interface FornecedorDAO {

	
	
	Fornecedor get(int id);
	List<Fornecedor> list();
	boolean add(Fornecedor fornecedor);
	boolean update(Fornecedor fornecedor);
}
