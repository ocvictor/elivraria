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
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariafront.util.FileUtil;
import br.com.elivrariafront.validator.ProductValidator;

@Controller
@RequestMapping("/gerenciar")
public class GerenciamentoController {

	private static final Logger logger = LoggerFactory.getLogger(GerenciamentoController.class);

	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;		

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
			new ProductValidator().validate(mLivro, results);
		}
		else {
			if(!mLivro.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mLivro, results);
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
	
	@ModelAttribute("categorias")
	public Categoria modelCategoria() {
		return new Categoria();
	}
	
	
}

	
