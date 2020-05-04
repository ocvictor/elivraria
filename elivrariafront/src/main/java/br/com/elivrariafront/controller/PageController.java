package br.com.elivrariafront.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.elivrariafront.model.UsuarioModelo;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.exception.LivroNotFoundException;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	@Autowired
	private BandeiraDAO bandeiraDAO;
	
	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private EstoqueDAO estoqueDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index(@RequestParam(name="logout",required=false)String logout) {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","Home");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		mv.addObject("categorias", categoriaDAO.list());
		
		
		if(logout!=null) {
			mv.addObject("message", "Deslogado com Sucesso!");			
		}
		
		mv.addObject("ClickHome",true);
		return mv;				
	}
	
	@RequestMapping(value = "/sobre")
	public ModelAndView about() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","Sobre");
		mv.addObject("ClickSobre",true);
		return mv;				
	}	
	
	@RequestMapping(value = "/contato")
	public ModelAndView contact() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("titulo","Fale Conosco");
		mv.addObject("ClickContato",true);
		return mv;				
	}	
	
	
	/*
	 * Carrega todos os produtos e categorias
	 * */
	
	@RequestMapping(value = "/mostrar/todos/livros")
	public ModelAndView showAllProducts() {		
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("title","Todos Livros");
		
		mv.addObject("categorias", categoriaDAO.list());
		
		mv.addObject("ClickTodosLivros",true);
		return mv;				
	}	
	
	@RequestMapping(value = "/mostrar/categoria/{id}/livros")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {		
		ModelAndView mv = new ModelAndView("page");
		
		Categoria categoria = null;
		
		categoria = categoriaDAO.get(id);
		
		mv.addObject("title",categoria.getNome());
		
		mv.addObject("categorias", categoriaDAO.list());
		
		mv.addObject("categoria", categoria);
		
		mv.addObject("ClickCategoriaLivros",true);
		return mv;				
	}	
	
	
	/*
	 * Mostrar 1 livro
	 * */
	
	@RequestMapping(value = "/mostrar/{id}/livro") 
	public ModelAndView MostrarUnicoLivro(@PathVariable int id) throws LivroNotFoundException {
		
		ModelAndView mv = new ModelAndView("page");
		
		Livro livro = livroDAO.get(id);
		
		if(livro == null) throw new LivroNotFoundException();
		
		livro.setVisualizacoes(livro.getVisualizacoes() + 1);	
		
		livroDAO.update(livro);
		//---------------------------
		
		mv.addObject("titulo", livro.getTitulo());
		mv.addObject("livro", livro);
		
		mv.addObject("ClickMostrarLivro", true);
		
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/registro")
	public ModelAndView register() {
		ModelAndView mv= new ModelAndView("page");
		
		mv.addObject("bandeiras", bandeiraDAO.list());
		logger.info("Page Controller membership called!");
		
		return mv;
	}
	
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,
			@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv= new ModelAndView("login");
		mv.addObject("title", "Login");
		if(error!=null) {
			mv.addObject("message", "Usuario e/ou Senha Invalida!");
		}
		if(logout!=null) {
			mv.addObject("logout", "Deslogado com Sucesso!");
		}
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}	
	
	
	@RequestMapping(value="/acesso-negado")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Falha Login.");		
		mv.addObject("errorDescription", "Voce nao tem autorização para entrar nesta pagina!");		
		mv.addObject("title", "403 Acesso Negado");		
		return mv;
	}	
	@ModelAttribute("bandeiras") 
	public List<Bandeira> modelBandeiras() {
		return bandeiraDAO.list();
	}
	
	@ModelAttribute("bandeira")
	public Bandeira modelBandeira() {
		return new Bandeira();
	}
	
	@RequestMapping(value = "/meuPerfil")
	public ModelAndView meuPerfil() {
		Usuario usuario = usuarioDAO.get(((UsuarioModelo)session.getAttribute("usuarioModelo")).getId());
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("titulo","Meu Perfil");
		mv.addObject("ClickMeuPerfil",true);
		mv.addObject("usuario", usuario);
		
	    
		return mv;				
	}
}
