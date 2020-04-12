package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dto.Livro;


@Repository("livroDAO")
@Transactional
public class LivroDAOImpl implements LivroDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public Livro get(int livroId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(Livro.class,Integer.valueOf(livroId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}

	/*
	 * LIST
	 * */
	
	@Override
	public List<Livro> list() {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Livro" , Livro.class)
						.getResultList();
	}

	/*
	 * INSERT
	 * */
	@Override
	public boolean add(Livro livro) {
		try {			
			sessionFactory
					.getCurrentSession()
						.persist(livro);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;
	}

	/*
	 * UPDATE
	 * */
	@Override
	public boolean update(Livro livro) {
		try {			
			sessionFactory
					.getCurrentSession()
						.update(livro);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;		
	}

	
	/*
	 * DELETE
	 * */
	@Override
	public boolean delete(Livro livro) {
		try {
			
			livro.setAtivo(false);
			return this.update(livro);
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;			
	}

	@Override
	public List<Livro> listaAtivosLivros() {
		String selectLivrosAtivos = "FROM Livro WHERE ativo = :ativo";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectLivrosAtivos, Livro.class)
						.setParameter("ativo", true)
							.getResultList();
	}

	@Override
	public List<Livro> listaAtivosLivroCategoria(int categoriaId) {
		String selectActiveProductsByCategory = "FROM Livro WHERE ativo = :ativo AND categoria_id = :categoriaId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectActiveProductsByCategory, Livro.class)
						.setParameter("ativo", true)
						.setParameter("categoriaId",categoriaId)
							.getResultList();
	}

	@Override
	public List<Livro> getUltimosAtivosLivros(int count) {
		return sessionFactory
				.getCurrentSession()
					.createQuery("FROM Livro WHERE ativo = :ativo ORDER BY id", Livro.class)
						.setParameter("ativo", true)
							.setFirstResult(0)
							.setMaxResults(count)
								.getResultList();					
	}

	@Override
	public List<Livro> getLivrosByParam(String param, int count) {
		
		String query = "FROM Livro WHERE ativo = true ORDER BY " + param + " DESC";
		
		return sessionFactory
					.getCurrentSession()
					.createQuery(query,Livro.class)
					.setFirstResult(0)
					.setMaxResults(count)
					.getResultList();
					
		
	}

}
