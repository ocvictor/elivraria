package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dto.Categoria;

@Repository("categoriaDAO")
@Transactional
public class CategoriaDAOImpl implements CategoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Categoria> list() {
		
		String selectCategoriaAtiva = "FROM Categoria WHERE ativo = :ativo";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectCategoriaAtiva);
				
		query.setParameter("ativo", true);
						
		return query.getResultList();
	}

	/*
	 * Getting single category based on id
	 */
	@Override
	public Categoria get(int id) {

		return sessionFactory.getCurrentSession().get(Categoria.class, Integer.valueOf(id));

	}

	@Override

	public boolean add(Categoria categoria) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(categoria);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * Updating a single category
	 */
	@Override
	public boolean update(Categoria categoria) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().update(categoria);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Categoria categoria) {
		
		categoria.setAtivo(false);
		
		try {
			sessionFactory.getCurrentSession().update(categoria);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
