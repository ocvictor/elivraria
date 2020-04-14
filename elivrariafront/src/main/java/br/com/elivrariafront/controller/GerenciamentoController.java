package br.com.elivrariafront.controller;

import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.FornecedorDAO;
import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.StatusVendaDAO;
import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Fornecedor;
import br.com.elivrariaback.dto.GrupoPrecificacao;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.StatusVenda;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariafront.util.FileUtil;
import br.com.elivrariafront.validador.EstoqueValidador;
import br.com.elivrariafront.validador.LivroValidador;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/gerenciar")
public class GerenciamentoController {

	private static final Logger logger = LoggerFactory.getLogger(GerenciamentoController.class);
	
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;		
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	

	@Autowired
	private BandeiraDAO bandeiraDAO;
	
	@Autowired
	private GrupoPrecificacaoDAO grpPrecificacaoDAO;
	
	@Autowired
	private EstoqueDAO estoqueDAO;
	
	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	@Autowired
	private VendaDetalheDAO vendaDetalheDAO;
	
	@Autowired
	private StatusVendaDAO statusVendaDAO;
	
	@RequestMapping("/livro")
	public ModelAndView gerenciarLivro(@RequestParam(name="success",required=false)String success) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Livro");		
		mv.addObject("ClickGerenciarLivro",true);
		
		Livro nLivro = new Livro();		
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
			new LivroValidador().validate(mLivro, results);
		}
		else {
			if(!mLivro.getFile().getOriginalFilename().equals("")) {
				new LivroValidador().validate(mLivro, results);
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
	
	@ModelAttribute("endereco")
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
	
	@ModelAttribute("livros") 
	public List<Livro> modelLivros() {
		return livroDAO.listaAtivosLivros();
	}
	
	@RequestMapping(value="/estoque")
	public ModelAndView gerenciarEstoque(@RequestParam(name="success",required=false)String success) {
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title","Gerenciar Estoque");
		mv.addObject("ClickGerenciarEstoque",true);
		
		Estoque estoque = new Estoque();
		Livro livro = new Livro();
		
		mv.addObject("estoque", estoque);
		mv.addObject("livros", livroDAO.list());
		mv.addObject("livro", livro);
		
		if(success != null) {
			if(success.equals("estoque")){
				mv.addObject("message", "Estoque Adicionado com Sucesso!");
			}	
		}
			
		return mv;
	}
	
	@ModelAttribute("grupoPrecificacao")
	public List<GrupoPrecificacao> modelGrpPrecificacao() {
		return grpPrecificacaoDAO.list();		
	}
	
	@ModelAttribute("fornecedores")
	public List<Fornecedor> modelFornecedor () {
		return fornecedorDAO.list();
	}
	
	@RequestMapping("/{id}/livro/estoque")
	public ModelAndView gerenciarEstoqueEditar(@PathVariable int id) {		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Estoque");		
		mv.addObject("ClickGerenciarEstoque",true);
		
		Estoque estoque = new Estoque();
		
		
		mv.addObject("estoque", estoque);
		mv.addObject("livro", livroDAO.get(id));

			
		return mv;		
	}
	
	@RequestMapping(value = "/estoque", method=RequestMethod.POST)
	public String managePostProduct(@Valid @ModelAttribute("estoque") Estoque mEstoque, 
			BindingResult results, Model model, HttpServletRequest request) {
		
		
		if(mEstoque.getId() == 0) {
		    Date date = new Date();  
			mEstoque.setDataEntrada(sdf.format(date));
			mEstoque.setFlgZerado(false);
			mEstoque.setTpoOperacao("ENTRADA");

			new EstoqueValidador().validate(mEstoque, results);
		}
		
		if(results.hasErrors()) {
			model.addAttribute("message", "Falha ao Cadastrar Estoque!");
			model.addAttribute("ClickGerenciarEstoque",true);
			return "page";
		}			

		
		if(mEstoque.getId() == 0 ) {
								
			estoqueDAO.add(mEstoque);
			
			//atualiza as informações do livro
			
			Object lstEstoque = estoqueDAO.getByLivroDataZero(mEstoque.getLivroId());
			
			BigDecimal maiorVlrCustoBd = (BigDecimal) lstEstoque;
			
			double maiorVlrCusto = maiorVlrCustoBd.doubleValue();
			
			Livro livro = livroDAO.get(mEstoque.getLivroId());
			int qtdAnt = livro.getQuantidade();
			livro.setQuantidade(qtdAnt + mEstoque.getQuantidade());
			
			GrupoPrecificacao grp = grpPrecificacaoDAO.get(livro.getGrupoPrecificacaoId());
			
			livro.setPrecoUnit(maiorVlrCusto * (grp.getPercentualLucro() + 1));
			
			livroDAO.update(livro);
			
		}
		else {
			estoqueDAO.update(mEstoque);
			
			//atualiza as informações do livro
			
			Object lstEstoque = estoqueDAO.getByLivroDataZero(mEstoque.getLivroId());
			
			BigDecimal maiorVlrCustoBd = (BigDecimal) lstEstoque;
			
			double maiorVlrCusto = maiorVlrCustoBd.doubleValue();
			
			Livro livro = livroDAO.get(mEstoque.getLivroId());
			int qtdAnt = livro.getQuantidade();
			livro.setQuantidade(qtdAnt + mEstoque.getQuantidade());
			
			GrupoPrecificacao grp = grpPrecificacaoDAO.get(livro.getGrupoPrecificacaoId());
			
			livro.setPrecoUnit(maiorVlrCusto * (grp.getPercentualLucro() + 1));
			
			livroDAO.update(livro);
		}		
		
		return "redirect:/gerenciar/estoque?success=estoque";
	}
	
	@RequestMapping(value="/vendas")
	public ModelAndView gerenciarVendas() {		
		logger.info("Chamando controller");
		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Vendas");		
		mv.addObject("ClickGerenciarVendas",true);			
		return mv;
		
	}
	
	@RequestMapping(value="/vendas/{id}/avancar")
	public ModelAndView gerenciarVendasAvancarStatus(@PathVariable int id) {
		logger.info("Chamando controller do avancar");

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Gerenciar Vendas Avançar");		
		mv.addObject("ClickGerenciarVendas",true);
		
		//Pega a venda pelo ID
		VendaDetalhe vd = vendaDetalheDAO.get(id);
		
		//Atualiza para o próximo status da venda
		StatusVenda sv = statusVendaDAO.get(vd.getStatusVenda().getId() + 1);
		
		//Seta o status no model VendaDetalhe
		vd.setStatusVenda(sv);
		
		vendaDetalheDAO.update(vd);	
		
		
		return mv;
	}
	
}

	
