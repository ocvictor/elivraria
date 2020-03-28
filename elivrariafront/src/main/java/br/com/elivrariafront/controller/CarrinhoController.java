package br.com.elivrariafront.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.elivrariafront.service.CarrinhoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

	private final static Logger logger = LoggerFactory.getLogger(CarrinhoController.class);
	
	@Autowired
	private CarrinhoService carrinhoService;
	@RequestMapping("/mostrar")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Carrinho de Compras");
		mv.addObject("ClickMostrarCarrinho", true);
		
		if(result!=null) {
			switch(result) {
				case "added":
					mv.addObject("message", "Produto adicionado ao Carrinho!");					
					carrinhoService.validarItemCarrinho();
					break;
				case "unavailable":
					mv.addObject("message", "Quantidade do produto não disponível!");					
					break;
				case "updated":
					mv.addObject("message", "Carrinho atualizado!");					
					carrinhoService.validarItemCarrinho();
					break;
				case "modified":
					mv.addObject("message", "Um ou mais itens do carrinho foram modificados!");
					break;
				case "maximum":
					mv.addObject("message", "Limite máximo para este item atingido!");
					break;
				case "deleted":
					mv.addObject("message", "Item removido!");
					break;

			}
		}
		else {
			String response = carrinhoService.validarItemCarrinho();
			if(response.equals("result=modified")) {
				mv.addObject("message", "Um ou mais itens do carrinho foram modificados!");
			}
		}

		mv.addObject("itemCarrinho", carrinhoService.getItensCarrinho());
		return mv;
		
	}
	

	@RequestMapping("/{itemCarrinhoId}/atualizar")
	public String udpateItemCarrinho(@PathVariable int itemCarrinhoId, @RequestParam int count) {
		String response = carrinhoService.gerenciarItemCarrinho(itemCarrinhoId, count);		
		return "redirect:/carrinho/mostrar?"+response;		
	}
	
	@RequestMapping("/adicionar/{livroId}/livro")
	public String addItemCarrinho(@PathVariable int livroId) {
		String response = carrinhoService.addItemCarrinho(livroId);
		return "redirect:/carrinho/mostrar?"+response;
	}
	
	@RequestMapping("/{itemCarrinhoId}/remover")
	public String removeItemCarrinho(@PathVariable int itemCarrinhoId) {
		String response = carrinhoService.removeItemCarrinho(itemCarrinhoId);
		return "redirect:/carrinho/mostrar?"+response;
	}
	
	/* after validating it redirect to checkout
	 * if result received is success proceed to checkout 
	 * else display the message to the user about the changes in cart page
	 * */	
	@RequestMapping("/validar")
	public String validarCarrinho() {	
		String response = carrinhoService.validarItemCarrinho();
		if(!response.equals("result=success")) {
			return "redirect:/carrinho/mostrar?"+response;
		}
		else {
			return "redirect:/carrinho/checkout";
		}
	}	
}
