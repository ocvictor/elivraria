package br.com.elivrariafront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.elivrariaback.dao.CupomTrocaDAO;
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.TrocaDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dao.VendaDetalheDAO;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.CupomTroca;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.Troca;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariaback.dto.VendaDetalhe;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	


	@Autowired
	private LivroDAO livroDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private EstoqueDAO estoqueDAO;
	
	@Autowired
	private VendaDetalheDAO vendaDetalheDAO;
	
	@Autowired
	private TrocaDAO trocaDAO;
	
	@Autowired
	private CupomTrocaDAO cupomTrocaDAO;
	
	@RequestMapping("/admin/todos/livros")
	@ResponseBody
	public List<Livro> getTodosLivrosLista() {		
		return livroDAO.list();
				
	}	
	
	
	@RequestMapping("/todos/livros")
	@ResponseBody
	public List<Livro> getTodosLivros() {
		
		return livroDAO.listaAtivosLivros();
				
	}
	
	@RequestMapping("/categoria/{id}/livros")
	@ResponseBody
	public List<Livro> getLivrosPorCategoria(@PathVariable int id) {
		
		return livroDAO.listaAtivosLivroCategoria(id);
				
	}
	
	
	@RequestMapping("/mv/livros")
	@ResponseBody
	public List<Livro> getLivrosMaisVistos() {		
		return livroDAO.getLivrosByParam("visualizacoes", 5);				
	}
		
	@RequestMapping("/mc/livros")
	@ResponseBody
	public List<Livro> getLivrosMaisComprados() {		
		return livroDAO.getLivrosByParam("compras", 5);				
	}
	
	@RequestMapping("/admin/todos/usuarios")
	@ResponseBody
	public List<Usuario> getTodosUsuariosLista() {		
		return usuarioDAO.listUsuarios();
				
	}
	
	@RequestMapping("/admin/todos/estoques")
	@ResponseBody
	public List<Estoque> getTodosEstoquesLista() {
		return estoqueDAO.listarTodos();				
	}
	
	@RequestMapping("/admin/vendas/aprovadas")
	@ResponseBody	
	public List<VendaDetalhe> getVendasAprovadasJson() {
		return vendaDetalheDAO.getVendasAprovadas();				
	}
	
	@RequestMapping("/admin/vendas/entregues")
	@ResponseBody
	public List<VendaDetalhe> getVendasEntregues() {
		return vendaDetalheDAO.getVendasEntregues();				
	}
	
	@RequestMapping("/admin/vendas/transporte")
	@ResponseBody
	public List<VendaDetalhe> getVendasTransporte() {
		return vendaDetalheDAO.getVendasTransporte();				
	}
	
	@RequestMapping("/meuperfil/{id}/pedidos")
	@ResponseBody
	public List<VendaDetalhe> getPedidosCliente(@PathVariable int id) {
		return vendaDetalheDAO.listByUsuario(id);
	}
	
	@RequestMapping("/meuperfil/{id}/cupons")
	@ResponseBody
	public List<CupomTroca> getCuponsCliente(@PathVariable int id) {
		return cupomTrocaDAO.listByUsuario(id);
	}
	
	@RequestMapping("/meuperfil/{id}/enderecos")
	@ResponseBody
	public List<Endereco> getEnderecoCliente(@PathVariable int id) {
		return usuarioDAO.listEnderecos(id);
	}
	
	@RequestMapping("/meuperfil/{id}/cartoes")
	@ResponseBody
	public List<Cartao> getCartoesCliente(@PathVariable int id) {
		return usuarioDAO.listCartao(id);
	}
	
	@RequestMapping("/admin/trocas/solicitadas")
	@ResponseBody
	public List<Troca> getTrocasSolicitadas() {
		return trocaDAO.getSolicitadas();
	}
	
	@RequestMapping("/admin/trocas/analise")
	@ResponseBody
	public List<Troca> getTrocasAnalise() {
		return trocaDAO.getEmAnalise();
	}
	
	@RequestMapping("/admin/trocas/confirmadas")
	@ResponseBody
	public List<Troca> getTrocasConfirmadas() {
		return trocaDAO.getConfirmadas();
	}
}
