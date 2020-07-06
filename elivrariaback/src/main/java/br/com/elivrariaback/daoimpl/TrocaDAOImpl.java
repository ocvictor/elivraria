package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.TrocaDAO;
import br.com.elivrariaback.dto.Troca;

@Repository("trocaDAO")
@Transactional
public class TrocaDAOImpl implements TrocaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Troca> list() {
		
		String selectTroca = "FROM Troca ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectTroca);
				
						
		return query.getResultList();
	}


	@Override
	public Troca get(int id) {

		return sessionFactory.getCurrentSession().get(Troca.class, Integer.valueOf(id));

	}

	@Override

	public boolean add(Troca troca) {

		try {
			sessionFactory.getCurrentSession().persist(troca);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Troca troca) {

		try {
			sessionFactory.getCurrentSession().update(troca);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Troca> getSolicitadas() {
		String query = "From Troca Where status_troca_id= :statusTrocaId ";
		return sessionFactory.getCurrentSession().
				createQuery(query, Troca.class)
				.setParameter("statusTrocaId", 4)
				.getResultList();
		
	}
	
	@Override
	public List<Troca> getEmAnalise() {
		String query = "From Troca Where status_troca_id= :statusTrocaId ";
		return sessionFactory.getCurrentSession().
				createQuery(query, Troca.class)
				.setParameter("statusTrocaId", 3)
				.getResultList();
		
	}
	
	@Override
	public List<Troca> getConfirmadas() {
		String query = "From Troca Where status_troca_id= :statusTrocaId ";
		return sessionFactory.getCurrentSession().
				createQuery(query, Troca.class)
				.setParameter("statusTrocaId", 2)
				.getResultList();
		
	}


}
