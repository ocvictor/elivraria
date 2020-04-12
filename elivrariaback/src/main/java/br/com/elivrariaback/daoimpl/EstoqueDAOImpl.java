package br.com.elivrariaback.daoimpl;

import br.com.elivrariaback.dto.Livro;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.Estoque;

@Repository("estoqueDAO")
@Transactional
public class EstoqueDAOImpl implements EstoqueDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(EstoqueDAOImpl.class);

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Estoque> listarTodos() {
		
		String selectCategoriaAtiva = "FROM Estoque ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectCategoriaAtiva, Estoque.class).setMaxResults(5);
		
		return query.getResultList();
	}
	
	@Override
	public Object getByLivroDataZero(int livroId) {
		String selectGetMaiorVlrCusto = "SELECT max(valor_custo) as valor_custo"
				+ " FROM Estoque JOIN" + 
				"(" + 
				"select coalesce (MAX(data_entrada),'1900-01-01 00:00:00') as dt FROM Estoque" + 
				"				WHERE livro_id= :livroId" + 
				"				AND tipo_operacao= :tipoOperacao" + 
				"				AND flg_zerado= :flgZerado) t1" + 
				" WHERE livro_id = :livroId AND data_entrada > t1.dt ";
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(selectGetMaiorVlrCusto);
		
		query.setParameter("livroId", livroId);
		query.setParameter("tipoOperacao", "SAIDA");
		query.setParameter("flgZerado", true);

		return query.getSingleResult();
	}
	
	@Override	
	public List<Estoque> getByLivro(int livroId) {
		
		String selectEstoquePorLivro= "FROM Estoque WHERE livro_id = :livroId";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectEstoquePorLivro);
		
		query.setParameter("livroId",livroId);
		
						
		return query.getResultList();
	}
	
	/*
	 * INSERT
	 * */
	
	
	@Override
	public boolean add(Estoque estoque) {
		try {			
			sessionFactory
					.getCurrentSession()
						.persist(estoque);
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
	public boolean update(Estoque estoque) {
		try {			
			sessionFactory
					.getCurrentSession()
						.update(estoque);
			return true;
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}		
		return false;		
	}

	
	
	
	@Override
	public Estoque get(int estoqueId) {
		try {			
			return sessionFactory
					.getCurrentSession()
						.get(Estoque.class,Integer.valueOf(estoqueId));			
		}
		catch(Exception ex) {		
			ex.printStackTrace();			
		}
		return null;
	}

}
