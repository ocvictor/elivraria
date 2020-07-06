package br.com.elivrariaback.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.ItemVendaDAO;
import br.com.elivrariaback.dto.ItemVenda;

@Repository("itemVendaDAO")
@Transactional
public class ItemVendaDAOImpl implements ItemVendaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	/*
	 * 
	 */
	@Override
	public ItemVenda get(int id) {

		return sessionFactory.getCurrentSession().get(ItemVenda.class, Integer.valueOf(id));

	}
	


}
