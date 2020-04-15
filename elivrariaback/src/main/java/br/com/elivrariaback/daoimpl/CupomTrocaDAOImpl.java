package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.CupomTrocaDAO;
import br.com.elivrariaback.dto.CupomTroca;

@Repository("cupomTrocaDAO")
@Transactional
public class CupomTrocaDAOImpl implements CupomTrocaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CupomTroca> listByUsuario(int usuarioId) {
		
		String selectCuponsUsuario = "FROM CupomTroca WHERE usuario_id= :usuarioId and ativo = :ativo";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectCuponsUsuario);
		
		query.setParameter("usuarioId", usuarioId);
		query.setParameter("ativo", true);
						
		return query.getResultList();
	}


	@Override
	public CupomTroca get(int id) {

		return sessionFactory.getCurrentSession().get(CupomTroca.class, Integer.valueOf(id));

	}

	@Override
	public boolean add(CupomTroca cupomTroca) {

		try {
			sessionFactory.getCurrentSession().persist(cupomTroca);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}


	@Override
	public boolean delete(CupomTroca cupomTroca) {
		
		cupomTroca.setAtivo(false);
		
		try {
			sessionFactory.getCurrentSession().update(cupomTroca);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
