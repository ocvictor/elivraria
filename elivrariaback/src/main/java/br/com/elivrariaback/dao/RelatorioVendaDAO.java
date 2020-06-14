package br.com.elivrariaback.dao;

import java.util.List;


import br.com.elivrariaback.dto.RelatorioVendaBarra;
import br.com.elivrariaback.dto.RelatorioVendaLinha;

public interface RelatorioVendaDAO {

	List<RelatorioVendaBarra> relatorioVendaBarra (String dataInicio, String dataFim, String indicador);
	List<RelatorioVendaLinha> relatorioVendaLinha (String dataInicio, String dataFim, String indicador);
}
