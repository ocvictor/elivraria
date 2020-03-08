package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Usuario;

public interface UsuarioDAO {

	Usuario getByEmail(String email);
	Usuario get(int id);

	boolean add(Usuario usuario);
	
	Endereco getEndereco(int enderecoId);
	boolean addEndereco(Endereco endereco);
	boolean updateEndereco(Endereco endereco);
	Endereco getEnderecoCobranca(int usuarioId);
	List<Endereco> listEnderecoEntrega(int usuarioId);
	

	
}
