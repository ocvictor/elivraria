package br.com.elivrariafront.handler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import br.com.elivrariaback.dao.BandeiraDAO;
import br.com.elivrariaback.dao.CartaoDAO;
import br.com.elivrariaback.dao.CupomPromocionalDAO;
import br.com.elivrariaback.dao.CupomTrocaDAO;
import br.com.elivrariaback.dao.EnderecoDAO;
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
import br.com.elivrariaback.dto.CupomPromocional;
import br.com.elivrariaback.dto.CupomTroca;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariaback.dto.ItemVenda;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.StatusVenda;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.CartaoModelo;
import br.com.elivrariafront.model.CheckoutModelo;
import br.com.elivrariafront.model.DoisCartoesModelo;
import br.com.elivrariafront.model.ErroModelo;
import br.com.elivrariafront.model.FreteModelo;
import br.com.elivrariafront.model.UsuarioModelo;
import br.com.elivrariafront.validador.DoisCartaoValidador;
import br.com.elivrariafront.validador.VendaValidador;

@Component
public class CheckoutHandler {

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
	
	@Autowired
	private CupomTrocaDAO cupomTrocaDAO;
	
	@Autowired
	private CupomPromocionalDAO cupomPromocionalDAO;
	
	@Autowired
	private EnderecoDAO enderecoDAO;
	
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
	
	public String salvarEnderecoSelecionado(CheckoutModelo checkoutModelo, int enderecoId) {

		String transitionValue = "success";
		
		
		Endereco enderecoEntrega = enderecoDAO.getEndereco(enderecoId);		
		
		checkoutModelo.setEndereco(enderecoEntrega);
		
		Double dValorFrete = 0.0;
		Double pesoItens = 0.0;
		
		for (ItemCarrinho itens : checkoutModelo.getItemCarrinho()) {
			
			pesoItens += itens.getLivro().getPeso() * itens.getLivroQtd();
			
			
		}
		FreteModelo freteModelo = new FreteModelo();
		
		freteModelo.setnCdEmpresa("");
		freteModelo.setsDsSenha("");
		freteModelo.setnCdServico("04014");
		freteModelo.setsCepOrigem("01001001");
		freteModelo.setsCepDestino(checkoutModelo.getEndereco().getCep().replace("-", ""));
		freteModelo.setnVlPeso(String.valueOf(pesoItens));				
		freteModelo.setnCdFormato("3");
		freteModelo.setsCdMaoPropria("N");
		freteModelo.setnVlValorDeclarado("0");				
		freteModelo.setsCdAvisoRecebimento("S");
		freteModelo.setStrRetorno("xml");
		

		String valorFrete = new FreteHandler().calcularFrete(freteModelo);
		
		dValorFrete = Double.valueOf(valorFrete.replace(",","."));
		
		checkoutModelo.setValorFrete(dValorFrete);
		checkoutModelo.setCheckoutTotal(checkoutModelo.getCheckoutTotal() + checkoutModelo.getValorFrete());
		
		return transitionValue;
		
	}
			
	
	public String salvarEndereco(CheckoutModelo checkoutModelo, Endereco endereco) {

		String transitionValue = "success";
		
		endereco.setUsuarioId(checkoutModelo.getUsuario().getId());
		endereco.setEntrega(true);
		enderecoDAO.addEndereco(endereco);
		
		checkoutModelo.setEndereco(endereco);
		
		String valorFrete = "0";
		Double dValorFrete = 0.0;
		
		for (ItemCarrinho itens : checkoutModelo.getItemCarrinho()) {
			FreteModelo freteModelo = new FreteModelo();
			
			freteModelo.setnCdEmpresa("");
			freteModelo.setsDsSenha("");
			freteModelo.setnCdServico("04014");
			freteModelo.setsCepOrigem("01001001");
			freteModelo.setsCepDestino(checkoutModelo.getEndereco().getCep());
			freteModelo.setnVlPeso(String.valueOf(itens.getLivro().getPeso()));				
			freteModelo.setnCdFormato("1");
			freteModelo.setnVlComprimento(String.valueOf(itens.getLivro().getProfundidade()));				
			freteModelo.setnVlAltura(String.valueOf(itens.getLivro().getAltura()));				
			freteModelo.setnVlLargura(String.valueOf(itens.getLivro().getLargura()));				
			freteModelo.setnVlDiametro("0");
			freteModelo.setsCdMaoPropria("N");
			freteModelo.setnVlValorDeclarado("0");				
			freteModelo.setsCdAvisoRecebimento("S");
			freteModelo.setStrRetorno("xml");
			
			valorFrete = new FreteHandler().calcularFrete(freteModelo);
			dValorFrete = dValorFrete + (Double.valueOf(valorFrete) * itens.getLivroQtd());
			
		}
		checkoutModelo.setValorFrete(dValorFrete);
		checkoutModelo.setCheckoutTotal(checkoutModelo.getCheckoutTotal() + checkoutModelo.getValorFrete());
		
		return transitionValue;
		
	}
	
	public List<Cartao> getCartao(CheckoutModelo modelo) {
		
		List<Cartao> cartoes = usuarioDAO.listCartao(modelo.getUsuario().getId());				
		
		if(cartoes.size() == 0) {
			cartoes = new ArrayList<>();
		}		
		
		return cartoes;
	}
	
public List<CartaoModelo> getCartaoDoisCartoes(CheckoutModelo modelo) {
		
		List<Cartao> cartoes = usuarioDAO.listCartao(modelo.getUsuario().getId());				
		
		if(cartoes.size() == 0) {
			cartoes = new ArrayList<>();
		}
		
		List<CartaoModelo> lstCartaoModelo = new ArrayList<CartaoModelo>();
		
		for (Cartao cartao : cartoes) {
			CartaoModelo cartaoModelo = new CartaoModelo();
			cartaoModelo.setCartaoId(cartao.getId());
			cartaoModelo.setBandeiraId(cartao.getBandeira().getId());
			cartaoModelo.setAnoVencimento(cartao.getAnoVencimento());
			cartaoModelo.setCcv(cartao.getCcv());
			String finalNumero = String.valueOf(cartao.getNumeroCartao()).substring(12, 16);
			cartaoModelo.setDescricao(cartao.getBandeira().getDescricao() + " - XXXX-XXXX-XXXX-" + finalNumero);
			lstCartaoModelo.add(cartaoModelo);
		}
		
		return lstCartaoModelo;
	}
	
	public List<Bandeira> getBandeiras() {
		
		List<Bandeira> bandeiras = bandeiraDAO.list();
		
		if(bandeiras.size() == 0) {
			bandeiras = new ArrayList<>();
		}

		return bandeiras;
	}
	
	public List<CupomTroca> getCupomTroca(CheckoutModelo modelo) {
		
		List<CupomTroca> cupons = cupomTrocaDAO.listByUsuario(modelo.getUsuario().getId());				
		
		if(cupons.size() == 0) {
			cupons = new ArrayList<>();
		}

		return cupons;
	}
	
	
	public String salvarCartaoSelecionado(CheckoutModelo checkoutModelo, int cartaoId) {

		String transitionValue = "success";
		
		
		Cartao cartao = cartaoDAO.getCartao(cartaoId);
		
		checkoutModelo.setCartaoUm(cartao);
		
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

		cartaoDAO.addCartao(cartao);
		
		return transitionValue;
		
	}
	
	public String salvarCupomTroca(CheckoutModelo checkoutModelo, int cupomTrocaId) {
		String transitionValue = "success";
		double vlrRestante = 0.0;
		
		CupomTroca cupomTroca = cupomTrocaDAO.get(cupomTrocaId);
		checkoutModelo.setCupomTroca(cupomTroca);
		
		
		if(checkoutModelo.getCheckoutTotal() < cupomTroca.getValorCupom()) {
			checkoutModelo.setCheckoutTotal(0.0);
			
			
			
			transitionValue = "totalZerado";
			return transitionValue;
			
		}
		else {
			vlrRestante = checkoutModelo.getCheckoutTotal() - cupomTroca.getValorCupom();
			checkoutModelo.setCheckoutTotal(vlrRestante);			
			return transitionValue;
		}
			
	}
	
	public String salvarCupomPromocional(CheckoutModelo checkoutModelo, String cupomPromocionalDesc, MessageContext error) {
		String transitionValue = "success";
		cupomPromocionalDesc = cupomPromocionalDesc.toUpperCase();
		
		List<CupomPromocional> lCupons = cupomPromocionalDAO.getByDescricao(cupomPromocionalDesc);
		
		if(lCupons.isEmpty() || lCupons == null)
		{
			ErroModelo er = new ErroModelo();
			
			er.setMsg("Cupom Inválido");
			
			error.addMessage(new MessageBuilder().error().source(
				      "cupompromocional").defaultText("Cupom Inválido").build());
			return "falha";
		}
		
		CupomPromocional cp = lCupons.get(0);
		checkoutModelo.setCupomPromocional(cp);

		
		double vlrRestante = 0.0;		
		
		
		if(checkoutModelo.getCheckoutTotal() < cp.getValorCupom()) {
			checkoutModelo.setCheckoutTotal(0.0);		
			transitionValue = "totalZerado";
			return transitionValue;
			
		}
		else {
			vlrRestante = checkoutModelo.getCheckoutTotal() - cp.getValorCupom();
			checkoutModelo.setCheckoutTotal(vlrRestante);			
			return transitionValue;
		}
			
	}
	
	public String processarDoisCartoes(CheckoutModelo checkoutModelo, DoisCartoesModelo doisCartoes, MessageContext error) {
		String transitionValue = "success";
		String results = "";
		
		results = new DoisCartaoValidador().validar(doisCartoes, results);
		
		
		
		if (!results.isEmpty()) {
			if(results.equals("erroCartao")) {
				error.addMessage(new MessageBuilder().error().source(
					      "segundoCartao.id").defaultText("Cartões devem ser diferentes").build());
					    transitionValue = "error";
					    return transitionValue;
			}
			if(results.equals("erroValorPrimeiro")) {
				error.addMessage(new MessageBuilder().error().source(
					      "valorPrimeiroCartao").defaultText("Valor para pagamento deve ser superior a R$ 10,00").build());
					    transitionValue = "error";
					    return transitionValue;

			}
			if(results.equals("erroValorSegundo")) {
				error.addMessage(new MessageBuilder().error().source(
					      "valorSegundoCartao").defaultText("Valor para pagamento deve ser superior a R$ 10,00").build());
					    transitionValue = "error";
					    return transitionValue;

			}
		}
		
		if (doisCartoes.getValorPrimeiroCartao() > checkoutModelo.getCheckoutTotal())
		{
			error.addMessage(new MessageBuilder().error().source(
				      "valorPrimeiroCartao").defaultText("Valor para pagamento maior que o valor da venda!").build());
				    transitionValue = "error";
				    return transitionValue;			
		}
		
		Cartao cartao = cartaoDAO.getCartao(doisCartoes.getPrimeiroCartao().getId());
		
		
		List<CartaoValidador> lCartaoValidador= cartaoDAO.validar(cartao.getBandeira().getId(),
				cartao.getNomeCartao(),cartao.getNumeroCartao(),
				cartao.getMesVencimento(),cartao.getAnoVencimento(), 
				cartao.getCcv());
		
		
		if(lCartaoValidador.isEmpty()) {
			error.addMessage(new MessageBuilder().error().source(
				      "primeiroCartao.id").defaultText("Cartão Inválido").build());
				    transitionValue = "error";
				    return transitionValue;		
		}
		
		cartao = cartaoDAO.getCartao(doisCartoes.getSegundoCartao().getId());
		
		lCartaoValidador= cartaoDAO.validar(cartao.getBandeira().getId(),
				cartao.getNomeCartao(),cartao.getNumeroCartao(),
				cartao.getMesVencimento(),cartao.getAnoVencimento(), 
				cartao.getCcv());
		
		if(lCartaoValidador.isEmpty()) {
			error.addMessage(new MessageBuilder().error().source(
				      "segundoCartao.id").defaultText("Cartão Inválido").build());
				    transitionValue = "error";
				    return transitionValue;			
		}
		
		checkoutModelo.setCartaoUm(cartaoDAO.getCartao(doisCartoes.getPrimeiroCartao().getId()));
		checkoutModelo.setCartaoDois(cartaoDAO.getCartao(doisCartoes.getSegundoCartao().getId()));
		
		return transitionValue;
	}
	
	public String salvarPedido(CheckoutModelo checkoutModelo) {
		String transitionValue = "success";
		
		VendaDetalhe detalhePedido = new VendaDetalhe();
				
		detalhePedido.setUsuario(checkoutModelo.getUsuario());
		detalhePedido.setCartaoUm(checkoutModelo.getCartaoUm());
		detalhePedido.setCartaoDois(checkoutModelo.getCartaoDois());
		detalhePedido.setCupomTroca(checkoutModelo.getCupomTroca());
		detalhePedido.setCupomPromocional(checkoutModelo.getCupomPromocional());
		detalhePedido.setEnderecoEntrega(checkoutModelo.getEndereco());
		detalhePedido.setValorFrete(checkoutModelo.getValorFrete());

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
		
		//caso valor cupom de troca maior que valor total venda, calcula diferenca e gera novo cupom
		
		if(detalhePedido.getCupomTroca() != null) {
			if(detalhePedido.getCupomTroca().getValorCupom() > (vendaTotal + detalhePedido.getValorFrete())) {
				Double vlrRestante = detalhePedido.getCupomTroca().getValorCupom() - (vendaTotal + detalhePedido.getValorFrete());
				
				//cria novo cupom de troca com valor da diferença
				CupomTroca novoCupomTroca = new CupomTroca();
				novoCupomTroca.setUsuario(checkoutModelo.getUsuario());
				novoCupomTroca.setValorCupom(vlrRestante);
				novoCupomTroca.setDescricao("CUPOM DIFERENCA - R$ " + String.valueOf(vlrRestante));
				novoCupomTroca.setAtivo(true);			
				cupomTrocaDAO.add(novoCupomTroca);
				
				//Inativa o cupom atual
				cupomTrocaDAO.delete(detalhePedido.getCupomTroca());
			}
			else {
				
				cupomTrocaDAO.delete(detalhePedido.getCupomTroca());
			}
		}
		

		detalhePedido.setTotalVenda(checkoutModelo.getCheckoutTotal());
		detalhePedido.setQtdVenda(vendaQtd);
		
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")); 
		String dataString = ldt.format(sdf);
		detalhePedido.setDataVenda(dataString);	
		
		if(checkoutModelo.getCheckoutTotal() > 0 && checkoutModelo.getCartaoDois() == null)
		{
			List<CartaoValidador> lCartaoValidador= cartaoDAO.validar(checkoutModelo.getCartaoUm().getBandeira().getId(),
					checkoutModelo.getCartaoUm().getNomeCartao(),checkoutModelo.getCartaoUm().getNumeroCartao(),
					checkoutModelo.getCartaoUm().getMesVencimento(),checkoutModelo.getCartaoUm().getAnoVencimento(), 
					checkoutModelo.getCartaoUm().getCcv());
			
			if(lCartaoValidador.isEmpty()) {
				StatusVenda sv = statusVendaDAO.get(2);
				detalhePedido.setStatusVenda(sv);			
				itemCarrinhoDAO.addVendaDetalhe(detalhePedido);
				return "errorCartao";			
			}
			
			CartaoValidador cartaoValidador = lCartaoValidador.get(0);		
			checkoutModelo.setCartaoValidador(cartaoValidador);
					
			
			String ReturnErroCartao = new VendaValidador().validate(checkoutModelo);	
			
			if(ReturnErroCartao == "error") {
				StatusVenda sv = statusVendaDAO.get(2);
				detalhePedido.setStatusVenda(sv);
				
				itemCarrinhoDAO.addVendaDetalhe(detalhePedido);
				return "errorCartao";
			}		
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



