package br.com.elivrariaback.daoimpl;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.StatusVendaDAO;
import br.com.elivrariaback.dto.StatusVenda;

@Repository("statusVendaDAO")
@Transactional
public class StatusVendaDAOImpl implements StatusVendaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public StatusVenda get(int id) {

		return sessionFactory.getCurrentSession().get(StatusVenda.class, Integer.valueOf(id));

	}	

	

}
