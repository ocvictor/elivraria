package br.com.elivrariaback.dao;

import br.com.elivrariaback.dto.Endereco;

public interface EnderecoDAO {
	
	Endereco getEndereco(int enderecoId);
	boolean addEndereco(Endereco endereco);
	boolean updateEndereco(Endereco endereco);
	
}
