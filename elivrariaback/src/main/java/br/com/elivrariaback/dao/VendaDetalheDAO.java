package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.RelatorioVenda;
import br.com.elivrariaback.dto.VendaDetalhe;

public interface VendaDetalheDAO {

	VendaDetalhe get(int id);
	
	List<VendaDetalhe> listByUsuario(int usuarioId);
	List<VendaDetalhe> getVendasAprovadas();
	List<VendaDetalhe> getVendasTransporte();
	List<VendaDetalhe> getVendasEntregues();
	boolean update(VendaDetalhe vendaDetalhe);
	List<RelatorioVenda> relatorioVenda (String dataInicio, String dataFim, String Genero, String tipo);
}
