package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.ItemCarrinhoDAO;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;

@Repository("itemCarrinhoDAO")
@Transactional
public class ItemCarrinhoDAOImpl implements ItemCarrinhoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ItemCarrinho getPorCarrinhoProduto(int carinhoId, int livroId) {
		String query = "FROM ItemCarrinho WHERE carrinho_id = :carrinhoId AND livro_id = :livroId";
		try {
			
			return sessionFactory.getCurrentSession()
									.createQuery(query,ItemCarrinho.class)
										.setParameter("carrinhoId", carinhoId)
										.setParameter("livroId", livroId)
											.getSingleResult();
			
		}catch(Exception ex) {
			return null;	
		}
		
	}

	@Override
	public boolean add(ItemCarrinho itemCarrinho) {
		try {
			sessionFactory.getCurrentSession().persist(itemCarrinho);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(ItemCarrinho itemCarrinho) {
		try {
			sessionFactory.getCurrentSession().update(itemCarrinho);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(ItemCarrinho itemCarrinho) {	
		try {			
			sessionFactory.getCurrentSession().delete(itemCarrinho);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}


	@Override
	public List<ItemCarrinho> list(int carinhoId) {
		String query = "FROM ItemCarrinho WHERE carrinho_id = :carrinhoId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, ItemCarrinho.class)
									.setParameter("carrinhoId", carinhoId)
										.getResultList();		
	}

	@Override
	public ItemCarrinho get(int id) {		
		return sessionFactory.getCurrentSession().get(ItemCarrinho.class, Integer.valueOf(id));
	}

	@Override
	public boolean updateCarrinho(Carrinho carrinho) {
		try {			
			sessionFactory.getCurrentSession().update(carrinho);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<ItemCarrinho> listarDisponivel(int carrinhoId) {
		String query = "FROM ItemCarrinho WHERE carrinho_id = :carrinhoId AND disponivel = :disponivel";
		return sessionFactory.getCurrentSession()
								.createQuery(query, ItemCarrinho.class)
									.setParameter("carrinhoId", carrinhoId)
									.setParameter("disponivel", true)
										.getResultList();
	}

	@Override
	public boolean addVendaDetalhe(VendaDetalhe vendaDetalhe) {
		try {			
			sessionFactory.getCurrentSession().persist(vendaDetalhe);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
		
}
