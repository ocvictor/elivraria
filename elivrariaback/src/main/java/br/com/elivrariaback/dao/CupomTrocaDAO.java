package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.CupomTroca;

public interface CupomTrocaDAO {

	CupomTroca get(int id);
	List<CupomTroca> listByUsuario(int usuarioId);
	boolean add(CupomTroca cupomTroca);
	boolean delete(CupomTroca cupomTroca);
}
