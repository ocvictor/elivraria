package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Usuario;

public interface UsuarioDAO {

	Usuario getByEmail(String email);
	Usuario get(int id);

	boolean add(Usuario usuario);
	
	Endereco getEndereco(int enderecoId);
	boolean addEndereco(Endereco endereco);
	boolean updateEndereco(Endereco endereco);
	boolean addCartao(Cartao cartao);
	boolean updateCartao(Cartao cartao);
	boolean updateUsuario(Usuario usuario);
	Endereco getEnderecoCobranca(int usuarioId);
	Cartao getCartao(int cartaoId);
	List<Cartao> listCartao(int usuarioId);
	List<Endereco> listEnderecoEntrega(int usuarioId);
	List<Usuario> listUsuarios();
	List<Endereco> listEnderecos (int usuarioId);
	

	
}
