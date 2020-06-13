package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.RelatorioVenda;
import br.com.elivrariaback.dto.VendaDetalhe;

@Repository("vendaDetalheDAO")
@Transactional
public class VendaDetalheDAOImpl implements VendaDetalheDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(VendaDetalheDAOImpl.class);

	
	@Override
	public List<RelatorioVenda> relatorioVenda (String dataInicio, String dataFim, String genero, String tipo) {
		String select = "";
		
		if (tipo.equals("Categoria")) {
			//select =  " select sum(livro_qtd)"
			//	    + " from ItemVenda ";
				//	+ " join ItemVenda.livro livro "
				//	+ " join livro.categoria categoria"
				//	+ " join ItemVenda.venda_detalhe venda_detalhe " 
				//	+ " join venda_detalhe.usuario usuario "
				//	+ " where date(venda_detalhe.venda_data) >= :dataInicio "
				//	+ " and   date(venda_detalhe.venda_date) <= :dataFim "
				//	+ " and usuario.genero = :genero "
				//	+ " group by livro.titulo ";
			
			select =  " select t3.id as id, t3.nome as atributo, t5.genero as genero, sum(t1.livro_qtd) as quantidade"
				    + " from venda_item t1 "
					+ " join livro t2 on t2.id = t1.livro_id "
					+ " join categoria t3 on t2.categoria_id = t3.id "
					+ " join venda_detalhe t4 on t4.id = t1.venda_detalhe_id " 
					+ " join usuario t5 on t4.usuario_id = t5.id "
					+ " where date(t4.venda_data) >= :dataInicio "
					+ " and   date(t4.venda_data) <= :dataFim "
					+ " and t5.genero = :genero "
					+ " group by 1,2,3 ";
		}
		logger.info("Montou a query" + select);

		//Query query = sessionFactory.getCurrentSession().createQuery(select);
		Query query = sessionFactory.getCurrentSession().createNativeQuery(select,RelatorioVenda.class);
		
		logger.info("Vai montar os parametros" + query);

		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		query.setParameter("genero", genero);
		
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> listByUsuario(int usuarioId) {
		
		String selectBandeira = "FROM VendaDetalhe Where usuario_id= :usuarioId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira, VendaDetalhe.class);
		
		query.setParameter("usuarioId", usuarioId);
						
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> getVendasAprovadas() {
		
		String selectBandeira = "FROM VendaDetalhe Where status_venda_id = :statusId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira, VendaDetalhe.class);
		
		query.setParameter("statusId", 1);
						
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> getVendasTransporte() {
		
		String selectBandeira = "FROM VendaDetalhe Where status_venda_id = :statusId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira, VendaDetalhe.class);
		
		query.setParameter("statusId", 3);
						
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> getVendasEntregues() {
		
		String selectBandeira = "FROM VendaDetalhe Where status_venda_id = :statusId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira, VendaDetalhe.class);
		
		query.setParameter("statusId", 4);
						
		return query.getResultList();
	}
	/*
	 * 
	 */
	@Override
	public VendaDetalhe get(int id) {

		return sessionFactory.getCurrentSession().get(VendaDetalhe.class, Integer.valueOf(id));

	}
	
	@Override
	public boolean update(VendaDetalhe vendaDetalhe) {
		try {			
			sessionFactory
					.getCurrentSession()
						.update(vendaDetalhe);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;		
	}
	
	

}
