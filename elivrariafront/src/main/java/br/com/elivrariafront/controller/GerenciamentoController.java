package br.com.elivrariafront.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
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
import br.com.elivrariaback.dao.CupomTrocaDAO;
import br.com.elivrariaback.dao.EnderecoDAO;
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.FornecedorDAO;
import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.StatusVendaDAO;
import br.com.elivrariaback.dao.TrocaDAO;
import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.CupomTroca;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Fornecedor;
import br.com.elivrariaback.dto.GrupoPrecificacao;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.StatusVenda;
import br.com.elivrariaback.dto.Troca;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariafront.handler.EstoqueHandler;
import br.com.elivrariafront.util.FileUtil;
import br.com.elivrariafront.validador.EstoqueValidador;
import br.com.elivrariafront.validador.LivroValidador;
import br.com.elivrariafront.validador.RegistroValidador;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/gerenciar")
public class GerenciamentoController {

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
	
	//@Autowired
	//private EstoqueDAO estoqueDAO;
	
	@Autowired
	private FornecedorDAO fornecedorDAO;
	
	@Autowired
	private VendaDetalheDAO vendaDetalheDAO;
	
	@Autowired
	private StatusVendaDAO statusVendaDAO;
	
	@Autowired
	private TrocaDAO trocaDAO;
	
	@Autowired
	private CupomTrocaDAO cupomTrocaDAO;
	
	@Autowired
	private EnderecoDAO enderecoDAO;
	
	@Autowired
	private CartaoDAO cartaoDAO;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 private EstoqueHandler estoqueHandler;
	
	 @Autowired // adicione tudo isso
	 
	 public GerenciamentoController(EstoqueHandler estoqueHandler) {
	        this.estoqueHandler = estoqueHandler;
	    }
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
				mv.addObject("message", "Livro Cadastrado com sucesso!");
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
	public String GerenciarLivroPost(@Valid @ModelAttribute("livro") Livro mLivro, 
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
	public String GerenciarLivroAtivacao(@PathVariable int id) {		
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
		mv.addObject("ClickGerenciarUsuario",true);
		
		mv.addObject("usuario", usuarioDAO.get(id));

			
		return mv;
		
	}
	
	
	@RequestMapping(value = "/usuario", method=RequestMethod.POST)
	public String managePostUsuario(@Valid @ModelAttribute("usuario") Usuario mUsuario, Endereco mEndereco, Cartao mCartao,
			BindingResult results, Model model, HttpServletRequest request, MessageContext error) {
		
		if(mUsuario.getId() == 0 ) {
			mUsuario.setRole("ADMIN");
			usuarioDAO.add(mUsuario);
		}
		else {
			String validacaoRegistro = new RegistroValidador().validate(mUsuario);
			  
			if(validacaoRegistro.equals("erroConfirmarSenha")) {
				model.addAttribute("message", "Senhas não correspondem!");
				model.addAttribute("ClickGerenciarUsuario",true);
				return "page";		        
			}	 
			
			 
			if(validacaoRegistro.equals("senha8caracteres")) {	
				 model.addAttribute("message", "Senha deve conter mais que 8 caractéres!");
				 model.addAttribute("ClickGerenciarUsuario",true);
				 return "page";	
			}
			 
			if (validacaoRegistro.equals("senhaMinusculo")) {			 
				 model.addAttribute("message", "Senha deve conter pelo menos 1 caracter minúsculo!");
				 model.addAttribute("ClickGerenciarUsuario",true);
				 return "page";	
				   
			}
			if(validacaoRegistro.equals("senhaMaiusculo")) {
				 model.addAttribute("message", "Senha deve conter pelo menos 1 caracter maiúsculo!");
				 model.addAttribute("ClickGerenciarUsuario",true);
				 return "page";	
				   
			}
			if(validacaoRegistro.equals("senhaEspecial")) {
				 model.addAttribute("message", "Senha deve conter pelo menos 1 caracter especial!");
				 model.addAttribute("ClickGerenciarUsuario",true);
				 return "page";	
			}
			mUsuario.setSenha(passwordEncoder.encode(mUsuario.getSenha()));

			usuarioDAO.update(mUsuario);
			
		}
		
		return "redirect:/gerenciar/usuario?success=usuario";
	}

	
	@RequestMapping(value = "/usuario/{id}/ativacao", method=RequestMethod.GET)
	@ResponseBody
	public String managePostUsuarioActivacao(@PathVariable int id) {		
		Usuario usuario = usuarioDAO.get(id);
		boolean isAtivo = usuario.isAtivo();
		usuario.setAtivo(!isAtivo);
		usuarioDAO.update(usuario);		
		return (isAtivo)? "Usuario Desativado com Sucesso!": "Usuario Ativado com Sucesso";
	}
			

	@RequestMapping(value = "/endereco", method=RequestMethod.POST)
	public String GerenciarEndereco(@ModelAttribute("endereco") Endereco mEndereco, HttpServletRequest request) {					
		enderecoDAO.addEndereco(mEndereco);		
		return "redirect:" + request.getHeader("Referer") + "?success=endereco";
	}
	
	@RequestMapping(value = "/cartao", method=RequestMethod.POST)
	public String GerenciarCartao(@ModelAttribute("cartao") Cartao mCartao, HttpServletRequest request) {					
		cartaoDAO.addCartao(mCartao);		
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
	public String gerenciarEstoquePost(@Valid @ModelAttribute("estoque") Estoque mEstoque, 
			BindingResult results, Model model, HttpServletRequest request) {
		
		
		if(mEstoque.getId() == 0) {
			String salvar = estoqueHandler.save(mEstoque, results);
			
			if(salvar=="erro") {
				model.addAttribute("message", "Falha ao Cadastrar Estoque!");
				model.addAttribute("ClickGerenciarEstoque",true);
				return "page";
			}	
		   
		}
		else {
			String atualizar = new EstoqueHandler().update(mEstoque);
			if(atualizar=="erro") {
				model.addAttribute("message", "Falha ao Atualizar Estoque!");
				model.addAttribute("ClickGerenciarEstoque",true);
				return "page";
			}	
		}		
		
		return "redirect:/gerenciar/estoque?success=estoque";
	}
	
	@RequestMapping(value="/vendas")
	public ModelAndView gerenciarVendas() {		
		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Vendas");		
		mv.addObject("ClickGerenciarVendas",true);			
		return mv;
		
	}
	
	@RequestMapping(value="/vendas/{id}/avancar")
	public String gerenciarVendasAvancarStatus(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Gerenciar Vendas Avançar");		
		mv.addObject("ClickGerenciarVendas",true);
		
		//Pega a venda pelo ID
		VendaDetalhe vd = vendaDetalheDAO.get(id);
		StatusVenda sv = new StatusVenda();
		
		//Atualiza para o próximo status da venda
		
		
		if (vd.getStatusVenda().getId() ==  1)
		{
			 sv = statusVendaDAO.get(vd.getStatusVenda().getId() + 2);
		} 
		if (vd.getStatusVenda().getId() == 3) {
			 sv = statusVendaDAO.get(vd.getStatusVenda().getId() + 1);
		}
		
		
		
		//Seta o status no model VendaDetalhe
		vd.setStatusVenda(sv);
		
		vendaDetalheDAO.update(vd);	
		
		
		return "redirect:/gerenciar/vendas";
	}
	
	@RequestMapping(value="/trocasCancelamentos")
	public ModelAndView gerenciarTrocasCancelamentos(@RequestParam(name="success",required=false)String success) {		
		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Gerenciar Trocas e Cancelamentos");		
		mv.addObject("ClickGerenciarTrocasCancelamentos",true);
		
		if(success != null) {
			if(success.equals("troca")){
				mv.addObject("message", "Troca Confirmada com sucesso!");
			}	
		}
		return mv;
		
	}
	
	@RequestMapping(value="/troca/{id}/analisar")
	public ModelAndView gerenciarTrocaAnalisar(@PathVariable int id) {		
	
		ModelAndView mv = new ModelAndView("page");
		
		Troca troca = trocaDAO.get(id);
		
		
		if (troca.getStatusTrocaId() == 4) {
			troca.setStatusTrocaId(3);
			trocaDAO.update(troca);
		}
		
		mv.addObject("troca", troca);
		mv.addObject("ClickAnalisarTroca", true);

		
		return mv;
		
	}
	
	@RequestMapping(value="/troca/{id}/confirmar")
	public String gerenciarTrocaConfirmar(@PathVariable int id) {		
	
		
		Troca troca = trocaDAO.get(id);
		
		troca.setStatusTrocaId(2);
		
		trocaDAO.update(troca);
		
		//dar entrada em estoque do produto trocado		
		
		Estoque estoque = new Estoque();
		Date date = new Date();
		estoque.setDataEntrada(sdf.format(date));
		estoque.setFornecedorId(1);
		estoque.setValorCusto(0);
		estoque.setFlgZerado(false);
		estoque.setLivroId(troca.getLivroId());
		estoque.setQuantidade(troca.getQtdTroca());
		estoque.setTpoOperacao("ENTRADA");		
	//	estoqueDAO.add(estoque);
		
		//atualiza quantidade total do livro trocado
		Livro livro = livroDAO.get(troca.getLivroId());
		int qtdAtual = livro.getQuantidade();
		
		livro.setQuantidade(qtdAtual + troca.getQtdTroca());
		livroDAO.update(livro);
		
		//gera cupom de troca para cliente
		CupomTroca cupomTroca = new CupomTroca();
		cupomTroca.setAtivo(true);
		cupomTroca.setDescricao("CUPOM TROCA PEDIDO: " + troca.getVendaDetalhe().getId());
		cupomTroca.setUsuario(troca.getVendaDetalhe().getUsuario());
		cupomTroca.setValorCupom(troca.getItemVenda().getCompraPreco() * troca.getQtdTroca());
		cupomTrocaDAO.add(cupomTroca);
		
		//atualiza o status da venda para trocado
		
		VendaDetalhe vendaDetalhe = troca.getVendaDetalhe();
		StatusVenda statusVenda = statusVendaDAO.get(6);
		vendaDetalhe.setStatusVenda(statusVenda);
		vendaDetalheDAO.update(vendaDetalhe);

		
		return "redirect:/gerenciar/trocasCancelamentos?success=troca";
		
	}

}

	
