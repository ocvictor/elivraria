package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Troca;

public interface TrocaDAO {
	
	Troca get(int id);
	List<Troca> list();
	List<Troca> getSolicitadas();
	List<Troca> getEmAnalise();
	List<Troca> getConfirmadas();


	boolean add(Troca troca);
	boolean update(Troca troca);
}
