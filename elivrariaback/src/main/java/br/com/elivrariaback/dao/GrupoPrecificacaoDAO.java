package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.GrupoPrecificacao;

public interface GrupoPrecificacaoDAO {

	
	
	GrupoPrecificacao get(int id);
	
	List<GrupoPrecificacao> list();
	boolean add(GrupoPrecificacao grpPrecificacao);
	boolean update(GrupoPrecificacao grpPrecificacao);

	
}
