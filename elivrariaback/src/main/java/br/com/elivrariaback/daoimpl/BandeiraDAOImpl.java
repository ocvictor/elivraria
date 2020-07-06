package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dto.Bandeira;

@Repository("bandeiraDAO")
@Transactional
public class BandeiraDAOImpl implements BandeiraDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Bandeira> list() {
		
		String selectBandeira = "FROM Bandeira ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectBandeira);
						
		return query.getResultList();
	}

	/*
	 * 
	 */
	@Override
	public Bandeira get(int id) {

		return sessionFactory.getCurrentSession().get(Bandeira.class, Integer.valueOf(id));

	}

	@Override

	public boolean add(Bandeira bandeira) {

		try {
			
			sessionFactory.getCurrentSession().persist(bandeira);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * 
	 */
	@Override
	public boolean update(Bandeira bandeira) {

		try {
			sessionFactory.getCurrentSession().update(bandeira);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
