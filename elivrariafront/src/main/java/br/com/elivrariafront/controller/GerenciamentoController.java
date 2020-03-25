package br.com.elivrariafront.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.util.FileUtil;
import br.com.elivrariafront.validator.LivroValidator;

@Controller
@RequestMapping("/gerenciar")
public class GerenciamentoController {

	private static final Logger logger = LoggerFactory.getLogger(GerenciamentoController.class);

	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;		
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Autowired
	private BandeiraDAO bandeiraDAO;
	
	
	@RequestMapping("/livro")
	public ModelAndView gerenciarLivro(@RequestParam(name="success",required=false)String success) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Livro");		
		mv.addObject("ClickGerenciarLivro",true);
		
		Livro nLivro = new Livro();
		
		nLivro.setFornecedorId(1);
		nLivro.setAtivo(true);

		mv.addObject("livro", nLivro);

		
		if(success != null) {
			if(success.equals("livro")){
				mv.addObject("message", "Produto Cadastrado com sucesso!");
			}	
			else if (success.equals("categoria")) {
				mv.addObject("message", "Categoria Cadastrada com sucesso!");
			}
		}
			
		return mv;
		
	}

	
	@RequestMapping("/{id}/livro")
	public ModelAndView gerenciarLivroEditar(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Livro");		
		mv.addObject("ClickGerenciarLivro",true);
		
		mv.addObject("livro", livroDAO.get(id));

			
		return mv;
		
	}
	
	
	@RequestMapping(value = "/livro", method=RequestMethod.POST)
	public String managePostProduct(@Valid @ModelAttribute("livro") Livro mLivro, 
			BindingResult results, Model model, HttpServletRequest request) {
		
		if(mLivro.getId() == 0) {
			new LivroValidator().validate(mLivro, results);
		}
		else {
			if(!mLivro.getFile().getOriginalFilename().equals("")) {
				new LivroValidator().validate(mLivro, results);
			}			
		}
		
		if(results.hasErrors()) {
			model.addAttribute("message", "Falha ao Cadastrar Produto!");
			model.addAttribute("ClickGerenciarLivro",true);
			return "page";
		}			

		
		if(mLivro.getId() == 0 ) {
			livroDAO.add(mLivro);
		}
		else {
			livroDAO.update(mLivro);
		}
	
		 if(!mLivro.getFile().getOriginalFilename().equals("") ){
			FileUtil.uploadFile(request, mLivro.getFile(), mLivro.getISBN()); 
		 }
		
		return "redirect:/gerenciar/livro?success=livro";
	}

	
	@RequestMapping(value = "/livro/{id}/ativacao", method=RequestMethod.GET)
	@ResponseBody
	public String managePostProductActivation(@PathVariable int id) {		
		Livro livro = livroDAO.get(id);
		boolean isAtivo = livro.isAtivo();
		livro.setAtivo(!isAtivo);
		livroDAO.update(livro);		
		return (isAtivo)? "Livro Desativado com Sucesso!": "Livro Ativado com Sucesso";
	}
			

	@RequestMapping(value = "/categoria", method=RequestMethod.POST)
	public String GerenciarCategoria(@ModelAttribute("categoria") Categoria mCategoria, HttpServletRequest request) {					
		categoriaDAO.add(mCategoria);		
		return "redirect:" + request.getHeader("Referer") + "?success=categoria";
	}
			
	
	
	@ModelAttribute("categorias") 
	public List<Categoria> modelCategorias() {
		return categoriaDAO.list();
	}
	
	@ModelAttribute("categoria")
	public Categoria modelCategoria() {
		return new Categoria();
	}
	
//----------------------
	@RequestMapping("/usuario")
	public ModelAndView gerenciarUsuario(@RequestParam(name="success",required=false)String success) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Usuario");		
		mv.addObject("ClickGerenciarUsuario",true);
		
		Usuario nUsuario = new Usuario();
		
		nUsuario.setAtivo(true);

		mv.addObject("usuario", nUsuario);

		
		if(success != null) {
			if(success.equals("usuario")){
				mv.addObject("message", "Usuário Cadastrado com sucesso!");
			}
		}
			
		return mv;
		
	}

	
	@RequestMapping("/{id}/usuario")
	public ModelAndView gerenciarUsuarioEditar(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Usuario");		
		mv.addObject("ClickGerenciarUsuaio",true);
		
		mv.addObject("usuario", usuarioDAO.get(id));

			
		return mv;
		
	}
	
	
	@RequestMapping(value = "/usuario", method=RequestMethod.POST)
	public String managePostUsuario(@Valid @ModelAttribute("usuario") Usuario mUsuario, Endereco mEndereco, Cartao mCartao,
			BindingResult results, Model model, HttpServletRequest request) {
		
		if(mUsuario.getId() == 0 ) {
			usuarioDAO.add(mUsuario);
		}
		else {
			usuarioDAO.updateEndereco(mEndereco);
			usuarioDAO.updateCartao(mCartao);
		}
		
		return "redirect:/gerenciar/usuario?success=usuario";
	}

	
	@RequestMapping(value = "/usuario/{id}/ativacao", method=RequestMethod.GET)
	@ResponseBody
	public String managePostUsuarioActivacao(@PathVariable int id) {		
		Usuario usuario = usuarioDAO.get(id);
		boolean isAtivo = usuario.isAtivo();
		usuario.setAtivo(!isAtivo);
		usuarioDAO.updateUsuario(usuario);		
		return (isAtivo)? "Usuario Desativado com Sucesso!": "usuario Ativado com Sucesso";
	}
			

	@RequestMapping(value = "/endereco", method=RequestMethod.POST)
	public String GerenciarEndereco(@ModelAttribute("endereco") Endereco mEndereco, HttpServletRequest request) {					
		usuarioDAO.addEndereco(mEndereco);		
		return "redirect:" + request.getHeader("Referer") + "?success=endereco";
	}
	
	@RequestMapping(value = "/cartao", method=RequestMethod.POST)
	public String GerenciarCartao(@ModelAttribute("cartao") Cartao mCartao, HttpServletRequest request) {					
		usuarioDAO.addCartao(mCartao);		
		return "redirect:" + request.getHeader("Referer") + "?success=cartao";
	}
		
	@ModelAttribute("enderecos") 
	public List<Endereco> modelEnderecos(Usuario usuario) {
		return usuarioDAO.listEnderecoEntrega(usuario.getId());
	}
	
	@ModelAttribute("enderecos")
	public Endereco modelEnderecos() {
		return new Endereco();
	}
	
	@ModelAttribute("cartoes") 
	public List<Cartao> modelCartoes(Usuario usuario) {
		return usuarioDAO.listCartao(usuario.getId());
	}
	
	@ModelAttribute("cartoes")
	public Cartao modelCartoes() {
		return new Cartao();
	}
	
	@ModelAttribute("bandeiras") 
	public List<Bandeira> modelBandeiras() {
		return bandeiraDAO.list();
	}
	
	@ModelAttribute("bandeira")
	public Bandeira modelBandeira() {
		return new Bandeira();
	}
}

	
