package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dto.GrupoPrecificacao;

@Repository("grupoPrecificacaoDAO")
@Transactional
public class GrupoPrecificacaoDAOImpl implements GrupoPrecificacaoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GrupoPrecificacao> list() {
		
		String selectGrpAtiva = "FROM GrupoPrecificacao";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectGrpAtiva);				
						
		return query.getResultList();
	}


	@Override
	public GrupoPrecificacao get(int id) {

		return sessionFactory.getCurrentSession().get(GrupoPrecificacao.class, Integer.valueOf(id));

	}

	@Override

	public boolean add(GrupoPrecificacao grpPrecificacao) {

		try {
			sessionFactory.getCurrentSession().persist(grpPrecificacao);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}


	@Override
	public boolean update(GrupoPrecificacao grpPrecificacao) {

		try {
			sessionFactory.getCurrentSession().update(grpPrecificacao);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	

}
