package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.VendaDetalhe;

public interface VendaDetalheDAO {

	VendaDetalhe get(int id);
	
	List<VendaDetalhe> listByUsuario(int usuarioId);
	List<VendaDetalhe> getVendasAprovadas();
	List<VendaDetalhe> getVendasTransporte();
	List<VendaDetalhe> getVendasEntregues();
	
	
}
