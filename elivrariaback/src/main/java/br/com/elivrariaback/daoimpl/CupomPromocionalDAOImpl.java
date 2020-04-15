package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.CupomPromocionalDAO;
import br.com.elivrariaback.dto.CupomPromocional;

@Repository("cupomPromocionalDAO")
@Transactional
public class CupomPromocionalDAOImpl implements CupomPromocionalDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CupomPromocional> getByDescricao(String descricao) {
		
		String selectCuponsUsuario = "FROM CupomPromocional WHERE descricao= :descricao and ativo = :ativo";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectCuponsUsuario);
		
		query.setParameter("descricao", descricao);
		query.setParameter("ativo", true);
						
		return query.getResultList();
	}


	@Override
	public CupomPromocional get(int id) {

		return sessionFactory.getCurrentSession().get(CupomPromocional.class, Integer.valueOf(id));

	}

	@Override
	public boolean add(CupomPromocional cupomPromocional) {

		try {
			sessionFactory.getCurrentSession().persist(cupomPromocional);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}


	@Override
	public boolean delete(CupomPromocional cupomPromocional) {
		
		cupomPromocional.setAtivo(false);
		
		try {
			sessionFactory.getCurrentSession().update(cupomPromocional);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
