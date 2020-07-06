package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.CartaoValidador;

public interface CartaoDAO {
	
	
	List<CartaoValidador> validar(int bandeiraId, String nome, long numero, int mes, int ano, int ccv);
	Cartao getCartao(int cartaoId);
	boolean addCartao(Cartao cartao);
	boolean updateCartao(Cartao cartao);
	
}
