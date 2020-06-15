package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.CartaoValidador;
import br.com.elivrariaback.dto.Categoria;

@Repository("cartaoDAO")
@Transactional
public class CartaoDAOImpl implements CartaoDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CartaoDAOImpl.class);


	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CartaoValidador> validar(int bandeiraId, String nome, long numero, int mes, int ano, int ccv) {
		
		String selectCartaoValidar = "FROM CartaoValidador WHERE bandeira_id= :bandeiraId "
				+ "AND nome= :nome AND numero= :numero AND vencimento_mes= :mes AND vencimento_ano= :ano AND ccv= :ccv";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectCartaoValidar);
				
		query.setParameter("bandeiraId", bandeiraId);
		query.setParameter("nome", nome);
		query.setParameter("numero", numero);
		query.setParameter("mes", mes);
		query.setParameter("ano", ano);
		query.setParameter("ccv", ccv);
		
		logger.info("Aqui está a query: " + query);

		return query.getResultList();
	}
	
	@Override
	public Cartao getCartao(int cartaoId) {
		try {			
			return sessionFactory.getCurrentSession().get(Cartao.class, cartaoId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
