package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Bandeira;

public interface BandeiraDAO {

	Bandeira get(int id);
	List<Bandeira> list();
	boolean add(Bandeira bandeira);
	boolean update(Bandeira bandeira);
}
