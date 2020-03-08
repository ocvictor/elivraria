package br.com.elivrariaback.dao;

import java.util.List;

import br.com.elivrariaback.dto.Carrinho;
import br.com.elivrariaback.dto.ItemCarrinho;
import br.com.elivrariaback.dto.VendaDetalhe;

public interface ItemCarrinhoDAO {

	public List<ItemCarrinho> list(int carrinhoId);
	public ItemCarrinho get(int id);	
	public boolean add(ItemCarrinho itemCarrinho);
	public boolean update(ItemCarrinho itemCarrinho);
	public boolean remove(ItemCarrinho itemCarrinho);
	
	public ItemCarrinho getPorCarrinhoProduto(int carrinhoId, int LivroId);		
		
	boolean updateCarrinho(Carrinho carrinho);
	
	public List<ItemCarrinho> listarDisponivel(int carrinhoId);
	
	boolean addVendaDetalhe(VendaDetalhe vendaDetalhe);

	
}
