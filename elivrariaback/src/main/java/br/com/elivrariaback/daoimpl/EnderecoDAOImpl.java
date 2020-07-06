package br.com.elivrariaback.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import br.com.elivrariaback.dao.EnderecoDAO;
import br.com.elivrariaback.dto.Endereco;

@Repository("enderecoDAO")
@Transactional
public class EnderecoDAOImpl implements EnderecoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addEndereco(Endereco endereco) {
		try {			
			sessionFactory.getCurrentSession().persist(endereco);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public boolean updateEndereco(Endereco endereco) {
		try {			
			sessionFactory.getCurrentSession().update(endereco);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	@Override
	public Endereco getEndereco(int enderecoId) {
		try {			
			return sessionFactory.getCurrentSession().get(Endereco.class, enderecoId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
