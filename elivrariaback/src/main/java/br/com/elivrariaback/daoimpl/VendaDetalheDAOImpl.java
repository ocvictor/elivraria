package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.VendaDetalhe;

@Repository("vendadetalheDAO")
@Transactional
public class VendaDetalheDAOImpl implements VendaDetalheDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<VendaDetalhe> listByUsuario(int usuarioId) {
		
		String selectBandeira = "FROM VendaDetalhe Where usuario_id= :usuarioId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira);
		
		query.setParameter("usuarioId", usuarioId);
						
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> getVendasAprovadas() {
		
		String selectBandeira = "FROM VendaDetalhe Where status_venda_id = :statusId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira);
		
		query.setParameter("statusId", 1);
						
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> getVendasTransporte() {
		
		String selectBandeira = "FROM VendaDetalhe Where status_venda_id = :statusId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira);
		
		query.setParameter("statusId", 3);
						
		return query.getResultList();
	}
	
	@Override
	public List<VendaDetalhe> getVendasEntregues() {
		
		String selectBandeira = "FROM VendaDetalhe Where status_venda_id = :statusId order by venda_data desc";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira);
		
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

	

}
