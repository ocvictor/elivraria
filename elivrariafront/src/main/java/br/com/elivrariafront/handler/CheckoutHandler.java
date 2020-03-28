package br.com.elivrariafront.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.elivrariaback.dao.ItemCarrinhoDAO;
import br.com.elivrariaback.dao.LivroDAO;
import br.com.elivrariaback.dao.UsuarioDAO;
import br.com.elivrariaback.dto.Endereco;
import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;
import br.com.elivrariaback.dto.ItemVenda;
import br.com.elivrariaback.dto.Livro;
import br.com.elivrariaback.dto.Usuario;
import br.com.elivrariafront.model.CheckoutModelo;
import br.com.elivrariafront.model.UsuarioModelo;

@Component
public class CheckoutHandler {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutHandler.class);
	
	@Autowired
	private UsuarioDAO userDAO;
	
	@Autowired
	private LivroDAO productDAO;

	@Autowired
	private ItemCarrinhoDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	
	public CheckoutModelo init(String name) throws Exception{

		Usuario user = userDAO.getByEmail(name);
		CheckoutModelo checkoutModel = null;	

		if(user!=null) {
			checkoutModel = new CheckoutModelo();
			checkoutModel.setUser(user);
			checkoutModel.setCart(user.getCarrinho());
			
			double checkoutTotal = 0.0;
			List<ItemCarrinho> cartLines = cartLineDAO.listarDisponivel(user.getCarrinho().getId());

			if(cartLines.size() == 0 ) {
				throw new Exception("There are no products available for checkout now!");
			}
			
			for(ItemCarrinho cartLine: cartLines) {
				checkoutTotal += cartLine.getTotal();
			}
			
			checkoutModel.setCartLines(cartLines);
			checkoutModel.setCheckoutTotal(checkoutTotal);			
		}			
		
		return checkoutModel;
	}
	
	
	public List<Endereco> getShippingAddresses(CheckoutModelo model) {
				
		List<Endereco> addresses = userDAO.listEnderecoEntrega(model.getUser().getId());
		
		if(addresses.size() == 0) {
			addresses = new ArrayList<>();
		}

		addresses.add(addresses.size(), userDAO.getEnderecoCobranca(model.getUser().getId()));			
		
		return addresses;
		
	}
	
	public String saveAddressSelection(CheckoutModelo checkoutModel, int shippingId) {

		String transitionValue = "success";
		
		//logger.info(String.valueOf(shippingId));
		
		Endereco shipping = userDAO.getEndereco(shippingId);		
		
		checkoutModel.setShipping(shipping);
		
		return transitionValue;
		
	}
			
	
	public String saveAddress(CheckoutModelo checkoutModel, Endereco shipping) {

		String transitionValue = "success";
		
		// set the user id
		// set shipping as true
		shipping.setUsuariorId(checkoutModel.getUser().getId());
		shipping.setCobranca(true);
		userDAO.addEndereco(shipping);
		
		// set the shipping id to flowScope object
		checkoutModel.setShipping(shipping);
		
		return transitionValue;
		
	}
		

	public String saveOrder(CheckoutModelo checkoutModel) {
		String transitionValue = "success";	
		
		// create a new order object
		VendaDetalhe orderDetail = new VendaDetalhe();
				
		// attach the user 
		orderDetail.setUser(checkoutModel.getUser());
				
		// attach the shipping address
		orderDetail.setShipping(checkoutModel.getShipping());
		
		// fetch the billing address
		Endereco billing = userDAO.getEnderecoCobranca(checkoutModel.getUser().getId());		
		orderDetail.setBilling(billing);

		List<ItemCarrinho> cartLines = checkoutModel.getCartLines();
		ItemVenda orderItem = null;
		
		double orderTotal = 0.0;
		int orderCount = 0;
		Livro product = null;
		
		for(ItemCarrinho cartLine : cartLines) {
			
			orderItem = new ItemVenda();
			
			orderItem.setBuyingPrice(cartLine.getBuyingPrice());
			orderItem.setProduct(cartLine.getProduct());
			orderItem.setProductCount(cartLine.getProductCount());
			orderItem.setTotal(cartLine.getTotal());
			
			orderItem.setOrderDetail(orderDetail);
			orderDetail.getOrderItems().add(orderItem);
			
			orderTotal += orderItem.getTotal();
			orderCount++;
			
			// update the product
			// reduce the quantity of product
			product = cartLine.getProduct();
			product.setQuantidade(product.getQuantidade() - cartLine.getProductCount());
			product.setCompras(product.getCompras() + cartLine.getProductCount());
			productDAO.update(product);
			
			// delete the cartLine
			cartLineDAO.remove(cartLine);
			

			
		}
		
		orderDetail.setOrderTotal(orderTotal);
		orderDetail.setOrderCount(orderCount);
		orderDetail.setOrderDate(new Date());
		
		// save the order
		cartLineDAO.addVendaDetalhe(orderDetail);

		// set it to the orderDetails of checkoutModel
		checkoutModel.setOrderDetail(orderDetail);

		
		// update the cart
		Carrinho cart = checkoutModel.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
		cart.setCartLines(cart.getItensCarrinho() - orderCount);
		cartLineDAO.updateCarrinho(cart);
		
		// update the cart if its in the session
		UsuarioModelo userModel = (UsuarioModelo) session.getAttribute("userModel");
		if(userModel!=null) {
			userModel.setCarrinho(cart);
		}
		
				
		return transitionValue;		
	}

	
	public VendaDetalhe getOrderDetail(CheckoutModelo checkoutModel) {
		return checkoutModel.getOrderDetail();
	}
	
	
	
}



