package br.com.elivrariaback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Usuario;


@Repository("usuarioDAO")
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Usuario getByEmail(String email) {
		String selectQuery = "FROM Usuario WHERE email = :email";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Usuario.class)
						.setParameter("email",email)
							.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
							
	}

	@Override
	public boolean add(Usuario user) {
		try {			
			sessionFactory.getCurrentSession().persist(user);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
		
	@Override
	public List<Cartao> listCartao(int usuarioId) {
		String selectQuery = "FROM Cartao WHERE usuarioId = :usuarioId ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Cartao.class)
						.setParameter("usuarioId", usuarioId)
							.getResultList();
		
	}
	
	@Override
	public List<Endereco> listEnderecos(int usuarioId) {
		String selectQuery = "FROM Endereco WHERE usuarioId = :usuarioId ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Endereco.class)
						.setParameter("usuarioId", usuarioId)
							.getResultList();
		
	}

	@Override
	public List<Endereco> listEnderecoEntrega(int usuarioId) {
		String selectQuery = "FROM Endereco WHERE usuarioId = :usuarioId AND entrega = :isentrega ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Endereco.class)
						.setParameter("usuarioId", usuarioId)
						.setParameter("isentrega", true)
							.getResultList();
		
	}

	@Override
	public Endereco getEnderecoCobranca(int usuarioId) {
		String selectQuery = "FROM Endereco WHERE usuarioId = :usuarioId AND cobranca = :isCobranca";
		try{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Endereco.class)
						.setParameter("usuarioId", usuarioId)
						.setParameter("isCobranca", true)
						.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public Usuario get(int id) {
		try {			
			return sessionFactory.getCurrentSession().get(Usuario.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@Override
	public boolean update(Usuario usuario) {
		try {			
			sessionFactory.getCurrentSession().update(usuario);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}	
	
	@Override
	public List<Usuario> listUsuarios() {
		return sessionFactory
				.getCurrentSession()
					.createNativeQuery(" Select * FROM Usuario Where id > 1 ", Usuario.class)
						.getResultList();
	}

}
