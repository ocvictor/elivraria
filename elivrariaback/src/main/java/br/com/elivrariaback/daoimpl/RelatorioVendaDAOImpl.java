package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.RelatorioVendaDAO;
import br.com.elivrariaback.dto.RelatorioVendaBarra;
import br.com.elivrariaback.dto.RelatorioVendaLinha;

@Repository("relatorioVendaDAO")
@Transactional
public class RelatorioVendaDAOImpl implements RelatorioVendaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(VendaDetalheDAOImpl.class);

	
	@Override
	public List<RelatorioVendaBarra> relatorioVendaBarra(String dataInicio, String dataFim, String indicador) {
		String select = "";
		
		if (indicador.equals("Categoria")) {
			select =  " select t3.id as id, t3.nome as atributo, sum(t1.livro_qtd) as quantidade"
				    + " from venda_item t1 "
					+ " join livro t2 on t2.id = t1.livro_id "
					+ " join categoria t3 on t2.categoria_id = t3.id "
					+ " join venda_detalhe t4 on t4.id = t1.venda_detalhe_id " 
					+ " where date(t4.venda_data) >= :dataInicio "
					+ " and   date(t4.venda_data) <= :dataFim "
					+ " and   t4.status_venda_id = 4"
					+ " group by 1,2";
		}
		
		if(indicador.equals("Livro")) {
			select =  " select t2.id as id, t2.titulo as atributo, sum(t1.livro_qtd) as quantidade"
				    + " from venda_item t1 "
					+ " join livro t2 on t2.id = t1.livro_id "
					+ " join venda_detalhe t4 on t4.id = t1.venda_detalhe_id " 
					+ " join usuario t5 on t4.usuario_id = t5.id "
					+ " where date(t4.venda_data) >= :dataInicio "
					+ " and   date(t4.venda_data) <= :dataFim "
					+ " and   t4.status_venda_id = 4"
					+ " group by 1,2";
		}
		
		if(indicador.equals("GrupoPrecificacao")) {
			select =  " select t3.id as id, t3.descricao as atributo, sum(t1.livro_qtd) as quantidade"
				    + " from venda_item t1 "
					+ " join livro t2 on t2.id = t1.livro_id "
					+ " join grupo_precificacao t3 on t2.grupo_precificacao_id = t3.id "
					+ " join venda_detalhe t4 on t4.id = t1.venda_detalhe_id " 
					+ " where date(t4.venda_data) >= :dataInicio "
					+ " and   date(t4.venda_data) <= :dataFim "
					+ " and   t4.status_venda_id = 4"
					+ " group by 1,2";
		}
		
		
		Query query = sessionFactory.getCurrentSession().createNativeQuery(select,RelatorioVendaBarra.class);
		

		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		
		return query.getResultList();
	}
	
	@Override
	public List<RelatorioVendaLinha> relatorioVendaLinha(String dataInicio, String dataFim, String indicador) {
		String select = "";
		
		if (indicador.equals("Categoria")) {
			select = " select "
					+ " concat(t3.id,coalesce (concat(month(t4.venda_data),year(t4.venda_data)),concat(month(t5.db_date),year(t5.db_date)))) as id, "
					+ " t3.nome as atributo, "
					+ " coalesce (concat(month(t4.venda_data),'/',year(t4.venda_data)),concat(month(t5.db_date),'/',year(t5.db_date))) as AnoMes, "
					+ " coalesce(sum(t1.livro_qtd),0) as quantidade "
					+ " from categoria t3 "
					+ " join livro t2 on t2.categoria_id = t3.id "
					+ " left join time_dimension t5 "
					+ " on t5.db_date >= :dataInicio "
					+ " and  t5.db_date <= :dataFim "
					+ " left join venda_detalhe t4  "
					+ " on t4.status_venda_id = 4 "
					+ " and month(t5.db_date) = month(t4.venda_data)  "
					+ " and year(t5.db_date) = year(t4.venda_data) "
					+ " left join venda_item t1 on t4.id = t1.venda_detalhe_id and t2.id = t1.livro_id  "
					+ " group by 3,2,1 "
					+ " order by 2, year(t5.db_date),month(t5.db_date) " ;
		}
		
		if(indicador.equals("Livro")) {
			select = " select "
					+ " concat(t2.id,coalesce (concat(month(t4.venda_data),year(t4.venda_data)),concat(month(t5.db_date),year(t5.db_date)))) as id, "
					+ " t2.titulo as atributo, "
					+ " coalesce (concat(month(t4.venda_data),'/',year(t4.venda_data)),concat(month(t5.db_date),'/',year(t5.db_date))) as AnoMes, "
					+ " coalesce(sum(t1.livro_qtd),0) as quantidade "
					+ " from livro t2 "
					+ " left join time_dimension t5 "
					+ " on year(t5.db_date) >= year(:dataInicio) "
					+ " and  year(t5.db_date) <= year(:dataFim) "
					+ " and month(t5.db_date) >= month(:dataInicio) "
					+ " and  month(t5.db_date) <= month(:dataFim) "
					+ " left join venda_detalhe t4  "
					+ " on t4.status_venda_id = 4 "
					+ " and month(t5.db_date) = month(t4.venda_data)  "
					+ " and year(t5.db_date) = year(t4.venda_data) "
					+ " left join venda_item t1 on t4.id = t1.venda_detalhe_id and t2.id = t1.livro_id  "
					+ " group by 3,2,1 "
					+ " order by 2, year(t5.db_date),month(t5.db_date) " ;
		}
		
		if(indicador.equals("GrupoPrecificacao")) {
			select = " select "
					+ " concat(t3.id,coalesce (concat(month(t4.venda_data),year(t4.venda_data)),concat(month(t5.db_date),year(t5.db_date)))) as id, "
					+ " t3.descricao as atributo, "
					+ " coalesce (concat(month(t4.venda_data),'/',year(t4.venda_data)),concat(month(t5.db_date),'/',year(t5.db_date))) as AnoMes, "
					+ " coalesce(sum(t1.livro_qtd),0) as quantidade "
					+ " from grupo_precificacao t3 "
					+ " join livro t2 on t2.grupo_precificacao_id = t3.id "
					+ " left join time_dimension t5 "
					+ " on t5.db_date >= :dataInicio "
					+ " and  t5.db_date <= :dataFim "
					+ " left join venda_detalhe t4  "
					+ " on t4.status_venda_id = 4 "
					+ " and month(t5.db_date) = month(t4.venda_data)  "
					+ " and year(t5.db_date) = year(t4.venda_data) "
					+ " left join venda_item t1 on t4.id = t1.venda_detalhe_id and t2.id = t1.livro_id  "
					+ " group by 3,2,1 "
					+ " order by 2, year(t5.db_date),month(t5.db_date) " ;
		}
		
		
		Query query = sessionFactory.getCurrentSession().createNativeQuery(select,RelatorioVendaLinha.class);
		

		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		
		return query.getResultList();
	}
}
