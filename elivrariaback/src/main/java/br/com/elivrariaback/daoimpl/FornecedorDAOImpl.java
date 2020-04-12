package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.FornecedorDAO;
import br.com.elivrariaback.dto.Fornecedor;

@Repository("fornecedorDAO")
@Transactional
public class FornecedorDAOImpl implements FornecedorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Fornecedor> list() {
		
		String selectFornecedor = "FROM Fornecedor ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectFornecedor);
				
				
		return query.getResultList();
	}

	@Override
	public Fornecedor get(int id) {

		return sessionFactory.getCurrentSession().get(Fornecedor.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Fornecedor fornecedor) {

		try {
			sessionFactory.getCurrentSession().persist(fornecedor);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}


	@Override
	public boolean update(Fornecedor fornecedor) {

		try {
			sessionFactory.getCurrentSession().update(fornecedor);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
