package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.CupomPromocional;

public interface CupomPromocionalDAO {

	CupomPromocional get(int id);
	List<CupomPromocional> getByDescricao(String descricao);
	boolean add(CupomPromocional cupomPromocional);
	boolean delete(CupomPromocional cupomPromocional);
}
