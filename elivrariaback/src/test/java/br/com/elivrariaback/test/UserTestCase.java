package br.com.elivrariaback.test;

import org.junit.BeforeClass;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Usuario;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UsuarioDAO usuarioDAO;
	private Usuario usuario = null;
	private Carrinho carrinho = null;
	private Endereco endereco = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("br.com.elivrariaback");
		context.refresh();
		
		usuarioDAO = (UsuarioDAO) context.getBean("usuarioDAO");
	}


	
}
