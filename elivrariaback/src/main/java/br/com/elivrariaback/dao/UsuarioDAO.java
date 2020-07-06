package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Usuario;

public interface UsuarioDAO {

	Usuario getByEmail(String email);
	Usuario get(int id);
	boolean add(Usuario usuario);
	boolean update(Usuario usuario);
	Endereco getEnderecoCobranca(int usuarioId);	
	List<Cartao> listCartao(int usuarioId);
	List<Endereco> listEnderecoEntrega(int usuarioId);
	List<Usuario> listUsuarios();
	List<Endereco> listEnderecos (int usuarioId);
	

	
}
