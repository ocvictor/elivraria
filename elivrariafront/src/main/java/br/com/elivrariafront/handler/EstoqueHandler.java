package br.com.elivrariafront.handler;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.springframework.validation.BindingResult;

import br.com.elivrariaback.dao.EstoqueDAO;
import br.com.elivrariaback.dao.GrupoPrecificacaoDAO;
import br.com.elivrariaback.dao.LivroDAO;

import br.com.elivrariaback.dto.Estoque;
import br.com.elivrariaback.dto.GrupoPrecificacao;
import br.com.elivrariaback.dto.Livro;

import br.com.elivrariafront.validador.EstoqueValidador;

@Component
public class EstoqueHandler {

@Autowired
private EstoqueDAO estoqueDAO;

 @Autowired
 private LivroDAO livroDAO;
 
 @Autowired
 private GrupoPrecificacaoDAO grpPrecificacaoDAO;
 

private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

 
 public String update(Estoque estoque) {
	 String retorno = "sucesso";
	 
	 try {
		 estoqueDAO.update(estoque);
	 }catch(Exception e) {
			retorno = "erro";
			return retorno;
	}
		
	//atualiza as informações do livro
		
	Object lstEstoque = estoqueDAO.getByLivroDataZero(estoque.getLivroId());
		
	BigDecimal maiorVlrCustoBd = (BigDecimal) lstEstoque;
		
	double maiorVlrCusto = maiorVlrCustoBd.doubleValue();
		
	Livro livro = livroDAO.get(estoque.getLivroId());
	int qtdAnt = livro.getQuantidade();
	livro.setQuantidade(qtdAnt + estoque.getQuantidade());
		
	GrupoPrecificacao grp = grpPrecificacaoDAO.get(livro.getGrupoPrecificacaoId());
		
	livro.setPrecoUnit(maiorVlrCusto * (grp.getPercentualLucro() + 1));
		
	try {
		livroDAO.update(livro);
	}catch(Exception e) {
		retorno = "erro";
	}
	 
	 return retorno;
 }
 
 public String save(Estoque estoque, BindingResult results) {
	 String retorno = "sucesso";

	 
	 Date date = new Date();  
	 estoque.setDataEntrada(sdf.format(date));
	 boolean isZerado = estoque.isFlgZerado();
	 estoque.setFlgZerado(isZerado);
	 estoque.setTpoOperacao("ENTRADA");
	 
	 new EstoqueValidador().validate(estoque, results);

	 if(results.hasErrors()) {
		 retorno="erro";
			 return retorno;
	 }
	 
	 
	estoqueDAO.add(estoque);	 

	//atualiza as informações do livro
		
	Object lstEstoque = estoqueDAO.getByLivroDataZero(estoque.getLivroId());
		
	BigDecimal maiorVlrCustoBd = (BigDecimal) lstEstoque;
		
	double maiorVlrCusto = maiorVlrCustoBd.doubleValue();
		
	Livro livro = livroDAO.get(estoque.getLivroId());
	int qtdAnt = livro.getQuantidade();
	livro.setQuantidade(qtdAnt + estoque.getQuantidade());
		
	GrupoPrecificacao grp = grpPrecificacaoDAO.get(livro.getGrupoPrecificacaoId());
		
	livro.setPrecoUnit(maiorVlrCusto * (grp.getPercentualLucro() + 1));
		
	livroDAO.update(livro);

	 return retorno;
 }
}
