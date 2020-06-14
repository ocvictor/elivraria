package br.com.elivrariafront.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.common.collect.*;
 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

import br.com.elivrariafront.model.RelatorioModelo;
import br.com.elivrariafront.model.TrocaModelo;
import br.com.elivrariafront.model.UsuarioModelo;
import br.com.elivrariafront.validador.EstoqueValidador;
import br.com.elivrariafront.validador.RelatorioValidador;
import br.com.elivrariafront.validador.TrocaValidador;
import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CategoriaDAO;
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.ItemVendaDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.RelatorioVendaDAO;
import br.com.elivrariaback.dao.StatusVendaDAO;
import br.com.elivrariaback.dao.TrocaDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Categoria;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.ItemVenda;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.RelatorioVendaLinha;
import br.com.elivrariaback.dto.RelatorioVendaBarra;
import br.com.elivrariaback.dto.StatusVenda;
import br.com.elivrariaback.dto.Troca;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariafront.exception.LivroNotFoundException;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
    private static final SimpleDateFormat sdfRelatorio = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfRelatorioAnoMes = new SimpleDateFormat("MM/yyyy");



	
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
	
	@Autowired
	private VendaDetalheDAO vendaDetalheDAO;
	
	@Autowired
	private ItemVendaDAO itemVendaDAO;
	
	@Autowired
	private TrocaDAO trocaDAO;
	
	@Autowired
	private StatusVendaDAO statusVendaDAO;
	
	@Autowired
	private RelatorioVendaDAO relatorioVendaDAO;

	
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
	public ModelAndView meuPerfil(@RequestParam(name="success",required=false)String success) {
		Usuario usuario = usuarioDAO.get(((UsuarioModelo)session.getAttribute("usuarioModelo")).getId());
		ModelAndView mv = new ModelAndView("page");		
		mv.addObject("titulo","Meu Perfil");
		mv.addObject("ClickMeuPerfil",true);
		mv.addObject("usuario", usuario);
		
		if(success != null) {
			if(success.equals("troca")){
				mv.addObject("message", "Troca Solicitada com sucesso!");
			}	
		}
		
	    
		return mv;				
	}
	
	
	
	@RequestMapping(value = "/mostrar/{id}/pedido") 
	public ModelAndView MostrarUnicoPedido(@PathVariable int id)  {
		
		ModelAndView mv = new ModelAndView("page");
		
		VendaDetalhe vendaDetalhe = vendaDetalheDAO.get(id);		
	
		//---------------------------
		
		mv.addObject("vendaDetalhe", vendaDetalhe);
		
		mv.addObject("ClickMostrarPedido", true);
		
		
		return mv;
		
	}
	@RequestMapping(value = "/solicitar/troca/{id}") 
	public ModelAndView SolicitarTroca(@PathVariable int id)  {
		
		ModelAndView mv = new ModelAndView("page");
		
		ItemVenda itemVenda = itemVendaDAO.get(id);
		VendaDetalhe vendaDetalhe = itemVenda.getVendaDetalhe();
		TrocaModelo troca = new TrocaModelo();
	
		//---------------------------
		
		mv.addObject("itemVenda", itemVenda);
		mv.addObject("vendaDetalhe", vendaDetalhe);
		mv.addObject("troca", troca);
		
		mv.addObject("ClickSolicitarTroca", true);
		
		
		return mv;
		
	}
	@RequestMapping(value = "/troca", method=RequestMethod.POST)
	public String salvarTroca(@Valid @ModelAttribute("troca") TrocaModelo mTroca, 
			BindingResult results, Model model, HttpServletRequest request) {
		
		
		Date date = new Date();  
		mTroca.setDataSolicitacao(sdf.format(date));
		mTroca.setStatusTrocaId(4); // 4 - SOLICITADA
		
		//Alimenta o Dominio que vai para o banco
		Troca troca = new Troca();
		
		troca.setDataSolicitacao(mTroca.getDataSolicitacao());
		troca.setItemVenda(itemVendaDAO.get(mTroca.getItemVendaId()));
		troca.setLivroId(mTroca.getLivroId());
		troca.setMotivo(mTroca.getMotivo());
		troca.setQtdTroca(mTroca.getQtdTroca());
		troca.setStatusTrocaId(mTroca.getStatusTrocaId());
		troca.setVendaDetalhe(troca.getItemVenda().getVendaDetalhe());
		
		VendaDetalhe vendaDetalhe = troca.getItemVenda().getVendaDetalhe();
		new TrocaValidador().validate(troca, results);
	
		
		if(results.hasErrors()) {
			model.addAttribute("message", "Quantidade para troca inválida!");
			model.addAttribute("ClickSolicitarTroca",true);
			model.addAttribute("itemVenda", troca.getItemVenda());
			model.addAttribute("vendaDetalhe", troca.getVendaDetalhe());
			return "page";
		}
		
		logger.info("Troca= " + troca);
		StatusVenda statusVenda = statusVendaDAO.get(5);
		vendaDetalhe.setStatusVenda(statusVenda);
		
		trocaDAO.add(troca);
		vendaDetalheDAO.update(vendaDetalhe);		

		return "redirect:/meuPerfil?success=troca";

		
	}
	
	@RequestMapping(value = "/gerenciar/relatorios")
	public ModelAndView gerenciarRelatorios() {
		ModelAndView mv = new ModelAndView("page");
		RelatorioModelo relatorioModelo = new RelatorioModelo();
		mv.addObject("title", "Gerenciar Relatorios");
		mv.addObject("relatorioModelo", relatorioModelo);
		mv.addObject("ClickGerenciarRelatorios", true);		
		return mv;
	}
	
	@RequestMapping(value = "/gerenciar/relatorios", method=RequestMethod.POST)
	public String gerarRelatorio(@Valid @ModelAttribute("relatorioModelo") RelatorioModelo mRelatorioModelo, 
			BindingResult results, Model model, HttpServletRequest request) {
		

		
		new RelatorioValidador().validate(mRelatorioModelo, results);	
		
		if(results.hasErrors()) {
			model.addAttribute("message", "Data inicial maior que a data final!");
			model.addAttribute("ClickGerenciarRelatorios",true);
			model.addAttribute("relatorioModelo", mRelatorioModelo);
			return "page";
		}

		if (mRelatorioModelo.getTipo().equals("Barra")) {
			List <RelatorioVendaBarra> lstRelatorioVendaBarra = relatorioVendaDAO.relatorioVendaBarra(mRelatorioModelo.getDataInicial(),
					mRelatorioModelo.getDataFinal(), mRelatorioModelo.getIndicador());

			Map<String, Integer> graficoMap = new LinkedHashMap<>();
			
			for (RelatorioVendaBarra reg : lstRelatorioVendaBarra) {
				graficoMap.put('"'+ reg.getAtributo().toString()+'"', reg.getQuantidade());
			}
			
			model.addAttribute("relatorioModelo", mRelatorioModelo);
			model.addAttribute("graficoMap", graficoMap);
			model.addAttribute("ClickGraficoBarra", true);
			
			return "page";	
		}
		else {
			List <RelatorioVendaLinha> lstRelatorioVendaLinha = relatorioVendaDAO.relatorioVendaLinha(mRelatorioModelo.getDataInicial(),
					mRelatorioModelo.getDataFinal(), mRelatorioModelo.getIndicador());
			
			
			Calendar beginCalendar = Calendar.getInstance();
	        Calendar finishCalendar = Calendar.getInstance();
	        
	        try {
	            beginCalendar.setTime(sdfRelatorio.parse(mRelatorioModelo.getDataInicial()));
	            finishCalendar.setTime(sdfRelatorio.parse(mRelatorioModelo.getDataFinal()));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	        ArrayList<String> anoMeses = new ArrayList<>();
	        
	        while (beginCalendar.compareTo(finishCalendar) <= 0) {

	            anoMeses.add('"' + sdfRelatorioAnoMes.format(beginCalendar.getTime()).toUpperCase() + '"');
	            beginCalendar.add(Calendar.MONTH, 1);
	            
	            if(beginCalendar.compareTo(finishCalendar) > 0) {
	            	if (!anoMeses.contains('"' + sdfRelatorioAnoMes.format(beginCalendar.getTime()).toUpperCase() + '"')) {
	            		anoMeses.add('"' + sdfRelatorioAnoMes.format(beginCalendar.getTime()).toUpperCase() + '"');
	            	}
	            }
	        }
			
			Map<String, List<Integer>> graficoMapTemp = new HashMap<>();
			

			
			
			for (RelatorioVendaLinha regLinha : lstRelatorioVendaLinha) {				
					
					if(graficoMapTemp.containsKey(regLinha.getAtributo())) {
						graficoMapTemp.get(regLinha.getAtributo()).add(regLinha.getQuantidade());
					}else {
						List<Integer> tempList = new ArrayList<Integer>();
						tempList.add(regLinha.getQuantidade());
						graficoMapTemp.put(regLinha.getAtributo(), tempList);
						
					}

					
				}
				
			ListMultimap<String, Object> graficoMap = ArrayListMultimap.create() ;
			
			StringBuilder sb = new StringBuilder();
			int count = 0;
			
			for (Map.Entry<String, List<Integer>> entry : graficoMapTemp.entrySet()) {
				if (count == 0){
					sb.append("{");

				}else
				{
					sb.append(",{");

				}
				sb.append("name: ");
				sb.append('"'+ entry.getKey() + '"');
				sb.append(", ");
				sb.append("data: ");
				sb.append(entry.getValue());
				sb.append("}");
				count++;
			}
			
        
			model.addAttribute("anoMeses", anoMeses);
			model.addAttribute("relatorioModelo", mRelatorioModelo);
			model.addAttribute("graficoMap", graficoMap);
			model.addAttribute("ClickGraficoLinha", true);
			model.addAttribute("series", sb);
			
			return "page";
		}
		
	
	}
	
}
