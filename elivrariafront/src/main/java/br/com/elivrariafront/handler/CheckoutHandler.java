package br.com.elivrariafront.handler;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.LocalDataSourceConnectionProvider;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.ItemCarrinhoDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.StatusVendaDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.Bandeira;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.Cartao;
import br.com.elivrariaback.dto.CartaoValidador;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariaback.dto.ItemVenda;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.StatusVenda;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.CartaoModelo;
import br.com.elivrariafront.model.CheckoutModelo;
import br.com.elivrariafront.model.UsuarioModelo;
import br.com.elivrariafront.validador.LivroValidador;
import br.com.elivrariafront.validador.VendaValidador;

@Component
public class CheckoutHandler {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutHandler.class);
    private static final DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private LivroDAO livroDAO;

	@Autowired
	private ItemCarrinhoDAO itemCarrinhoDAO;
	
	@Autowired
	private BandeiraDAO bandeiraDAO;
	
	@Autowired
	private StatusVendaDAO statusVendaDAO;
	
	@Autowired
	private CartaoDAO cartaoDAO;
	
	@Autowired
	private EstoqueDAO estoqueDAO;
	
	@Autowired
	private HttpSession session;
	
	
	public CheckoutModelo init(String nome) throws Exception{

		Usuario usuario = usuarioDAO.getByEmail(nome);
		CheckoutModelo checkoutModelo = null;	

		if(usuario!=null) {
			checkoutModelo = new CheckoutModelo();
			checkoutModelo.setUsuario(usuario);
			checkoutModelo.setCarrinho(usuario.getCarrinho());
			
			double checkoutTotal = 0.0;
			List<ItemCarrinho> itensCarrinho = itemCarrinhoDAO.listarDisponivel(usuario.getCarrinho().getId());

			if(itensCarrinho.size() == 0 ) {
				throw new Exception("Não há carrinhos para finalização da Compra!");
			}
			
			for(ItemCarrinho itemCarrinho: itensCarrinho) {
				checkoutTotal += itemCarrinho.getTotal();
			}
			
			checkoutModelo.setItemCarrinho(itensCarrinho);
			checkoutModelo.setCheckoutTotal(checkoutTotal);			
		}			
		
		return checkoutModelo;
	}
	
	
	public List<Endereco> getEnderecoEntrega(CheckoutModelo modelo) {
				
		List<Endereco> enderecos = usuarioDAO.listEnderecoEntrega(modelo.getUsuario().getId());
		
		if(enderecos.size() == 0) {
			enderecos = new ArrayList<>();
		}

		enderecos.add(enderecos.size(), usuarioDAO.getEnderecoCobranca(modelo.getUsuario().getId()));			
		
		return enderecos;
		
	}
	
	public String salvarEnderecoSelecionado(CheckoutModelo checkoutModel, int enderecoId) {

		String transitionValue = "success";
		
		
		Endereco enderecoEntrega = usuarioDAO.getEndereco(enderecoId);		
		
		checkoutModel.setEndereco(enderecoEntrega);
		
		return transitionValue;
		
	}
			
	
	public String salvarEndereco(CheckoutModelo checkoutModelo, Endereco endereco) {

		String transitionValue = "success";
		
		endereco.setUsuarioId(checkoutModelo.getUsuario().getId());
		endereco.setEntrega(true);
		usuarioDAO.addEndereco(endereco);
		
		checkoutModelo.setEndereco(endereco);
		
		return transitionValue;
		
	}
	
	public List<Cartao> getCartao(CheckoutModelo modelo) {
		
		List<Cartao> cartoes = usuarioDAO.listCartao(modelo.getUsuario().getId());				
		
		if(cartoes.size() == 0) {
			cartoes = new ArrayList<>();
		}

		return cartoes;
	}
	
	public List<Bandeira> getBandeiras() {
		
		List<Bandeira> bandeiras = bandeiraDAO.list();
		
		if(bandeiras.size() == 0) {
			bandeiras = new ArrayList<>();
		}

		return bandeiras;
	}
	
	public String salvarCartaoSelecionado(CheckoutModelo checkoutModelo, int cartaoId) {

		String transitionValue = "success";
		
		
		Cartao cartao = usuarioDAO.getCartao(cartaoId);
		
		checkoutModelo.setCartao(cartao);
		
		return transitionValue;
		
	}
			
	
	public String salvarCartao(CheckoutModelo checkoutModelo, CartaoModelo cartaoModelo) {

		String transitionValue = "success";
		
		Cartao cartao = new Cartao();
		
		Bandeira bandeira = bandeiraDAO.get(cartaoModelo.getBandeiraId());
		 
		cartao.setBandeira(bandeira);
		cartao.setUsuarioId(checkoutModelo.getUsuario().getId());
		cartao.setAnoVencimento(cartaoModelo.getAnoVencimento());
		cartao.setCcv(cartaoModelo.getCcv());
		cartao.setMesVencimento(cartaoModelo.getMesVencimento());
		cartao.setNomeCartao(cartaoModelo.getNomeCartao());
		cartao.setNumeroCartao(cartaoModelo.getNumeroCartao());

		usuarioDAO.addCartao(cartao);
		
		checkoutModelo.setCartao(cartao);
		
		return transitionValue;
		
	}
	

	public String salvarPedido(CheckoutModelo checkoutModelo) {
		String transitionValue = "success";
		BindingResult results;
		int status_venda;
		
		
		VendaDetalhe detalhePedido = new VendaDetalhe();
				
		detalhePedido.setUsuario(checkoutModelo.getUsuario());
		detalhePedido.setCartao(checkoutModelo.getCartao());
				
		detalhePedido.setEnderecoEntrega(checkoutModelo.getEndereco());
		
		Endereco cobranca = usuarioDAO.getEnderecoCobranca(checkoutModelo.getUsuario().getId());		
		detalhePedido.setEnderecoCobranca(cobranca);
			
		List<ItemCarrinho> itensCarrinho = checkoutModelo.getItemCarrinho();
		
		ItemVenda itemVenda = null;
		
		double vendaTotal = 0.0;
		int vendaQtd = 0;
		Livro livro = null;
		
		for(ItemCarrinho itemCarrinho : itensCarrinho) {
			
			itemVenda = new ItemVenda();
			
			itemVenda.setCompraPreco(itemCarrinho.getPrecoCompra());
			itemVenda.setLivro(itemCarrinho.getLivro());
			itemVenda.setQtdLivro(itemCarrinho.getLivroQtd());
			itemVenda.setTotal(itemCarrinho.getTotal());
			
			itemVenda.setVendaDetalhe(detalhePedido);
			detalhePedido.getItemVenda().add(itemVenda);
			
			vendaTotal += itemVenda.getTotal();
			vendaQtd++;			
		}
		

		detalhePedido.setTotalVenda(vendaTotal);
		detalhePedido.setQtdVenda(vendaQtd);
		
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")); 
		String dataString = ldt.format(sdf);
		detalhePedido.setDataVenda(dataString);	
		
		List<CartaoValidador> lCartaoValidador= cartaoDAO.validar(checkoutModelo.getCartao().getBandeira().getId(),
				checkoutModelo.getCartao().getNomeCartao(),checkoutModelo.getCartao().getNumeroCartao(),
				checkoutModelo.getCartao().getMesVencimento(),checkoutModelo.getCartao().getAnoVencimento(), 
				checkoutModelo.getCartao().getCcv());
		
		if(lCartaoValidador.isEmpty()) {
			StatusVenda sv = statusVendaDAO.get(2);
			detalhePedido.setStatusVenda(sv);			
			itemCarrinhoDAO.addVendaDetalhe(detalhePedido);
			return "errorCartao";			
		}
		
		CartaoValidador cartaoValidador = lCartaoValidador.get(0);		
		checkoutModelo.setCartaoValidador(cartaoValidador);
				
		
		String ReturnErroCartao = new VendaValidador().validate(checkoutModelo);	
		logger.info("Cartao validado");
		
		if(ReturnErroCartao == "error") {
			StatusVenda sv = statusVendaDAO.get(2);
			detalhePedido.setStatusVenda(sv);
			
			itemCarrinhoDAO.addVendaDetalhe(detalhePedido);
			return "errorCartao";
		}		

		for(ItemCarrinho itemCarrinho : itensCarrinho) {

			livro = itemCarrinho.getLivro();
			livro.setQuantidade(livro.getQuantidade() - itemCarrinho.getLivroQtd());
			livro.setCompras(livro.getCompras() + itemCarrinho.getLivroQtd());
			livroDAO.update(livro);
			
			Estoque estoque = new Estoque();
			
			estoque.setDataEntrada(dataString);
			estoque.setFornecedorId(1);
			estoque.setLivroId(livro.getId());
			estoque.setQuantidade(itemCarrinho.getLivroQtd());
			estoque.setTpoOperacao("SAIDA");
			
			if(livro.getQuantidade() <= 0) {
				estoque.setFlgZerado(true);
			}			
			estoque.setValorCusto(Double.valueOf(0.0));
			estoqueDAO.add(estoque);
			
			itemCarrinhoDAO.remove(itemCarrinho);
		}	
		

		StatusVenda sv = statusVendaDAO.get(1);		
		detalhePedido.setStatusVenda(sv);
		
		itemCarrinhoDAO.addVendaDetalhe(detalhePedido);

		checkoutModelo.setVendaDetalhe(detalhePedido);

		
		Carrinho carrinho = checkoutModelo.getCarrinho();
		carrinho.setTotal(carrinho.getTotal() - vendaTotal);
		carrinho.setItens(carrinho.getItens() - vendaQtd);
		itemCarrinhoDAO.updateCarrinho(carrinho);
		
		UsuarioModelo usuarioModelo = (UsuarioModelo) session.getAttribute("usuarioModelo");
		if(usuarioModelo!=null) {
			usuarioModelo.setCarrinho(carrinho);
		}
		
				
		return transitionValue;		
	}

	
	public VendaDetalhe getVendaDetalhe(CheckoutModelo checkoutModelo) {
		return checkoutModelo.getVendaDetalhe();
	}
	
	
	
}



