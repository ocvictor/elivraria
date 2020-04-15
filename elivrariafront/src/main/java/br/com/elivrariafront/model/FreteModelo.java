package br.com.elivrariafront.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.com.elivrariaback.dto.Carrinho;

public class FreteModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nCdEmpresa = "";
	private String sDsSenha = ""; 
	private String nCdServico = "41106";
	private String sCepOrigem = "09920175";
	private String sCepDestino = "72151613";
	private String nVlPeso = "0.300";
	private String nCdFormato = "1";
	private String nVlComprimento = "20";
	private String nVlAltura = "5";
	private String nVlLargura = "15";
	private String nVlDiametro = "0";
	private String sCdMaoPropria = "s";
	private String nVlValorDeclarado = "200";
	private String sCdAvisoRecebimento = "s";
	private String StrRetorno = "xml";
	
	public String getnCdEmpresa() {
		
		return nCdEmpresa;
	}
	public void setnCdEmpresa(String nCdEmpresa) {
		this.nCdEmpresa = nCdEmpresa;
	}
	public String getsDsSenha() {
		return sDsSenha;
	}
	public void setsDsSenha(String sDsSenha) {
		this.sDsSenha = sDsSenha;
	}
	public String getnCdServico() {
		return nCdServico;
	}
	public void setnCdServico(String nCdServico) {
		this.nCdServico = nCdServico;
	}
	public String getsCepOrigem() {
		return sCepOrigem;
	}
	public void setsCepOrigem(String sCepOrigem) {
		this.sCepOrigem = sCepOrigem;
	}
	public String getsCepDestino() {
		return sCepDestino;
	}
	public void setsCepDestino(String sCepDestino) {
		this.sCepDestino = sCepDestino;
	}
	public String getnVlPeso() {
		return nVlPeso;
	}
	public void setnVlPeso(String nVlPeso) {
		this.nVlPeso = nVlPeso;
	}
	public String getnCdFormato() {
		return nCdFormato;
	}
	public void setnCdFormato(String nCdFormato) {
		this.nCdFormato = nCdFormato;
	}
	public String getnVlComprimento() {
		return nVlComprimento;
	}
	public void setnVlComprimento(String nVlComprimento) {
		this.nVlComprimento = nVlComprimento;
	}
	public String getnVlAltura() {
		return nVlAltura;
	}
	public void setnVlAltura(String nVlAltura) {
		this.nVlAltura = nVlAltura;
	}
	public String getnVlLargura() {
		return nVlLargura;
	}
	public void setnVlLargura(String nVlLargura) {
		this.nVlLargura = nVlLargura;
	}
	public String getnVlDiametro() {
		return nVlDiametro;
	}
	public void setnVlDiametro(String nVlDiametro) {
		this.nVlDiametro = nVlDiametro;
	}
	public String getsCdMaoPropria() {
		return sCdMaoPropria;
	}
	public void setsCdMaoPropria(String sCdMaoPropria) {
		this.sCdMaoPropria = sCdMaoPropria;
	}
	public String getnVlValorDeclarado() {
		return nVlValorDeclarado;
	}
	public void setnVlValorDeclarado(String nVlValorDeclarado) {
		this.nVlValorDeclarado = nVlValorDeclarado;
	}
	public String getsCdAvisoRecebimento() {
		return sCdAvisoRecebimento;
	}
	public void setsCdAvisoRecebimento(String sCdAvisoRecebimento) {
		this.sCdAvisoRecebimento = sCdAvisoRecebimento;
	}
	public String getStrRetorno() {
		return StrRetorno;
	}
	public void setStrRetorno(String strRetorno) {
		StrRetorno = strRetorno;
	}  
	
	@Override
	public String toString() {
		return " FreteModelo[ nCdEmpresa=" + nCdEmpresa + ", sDsSenha=" + sDsSenha + ", nCdServico=" + nCdServico + 
				", sCepOrigem=" + sCepOrigem + ", sCepDestino=" + sCepDestino + ", nVlPeso=" + nVlPeso + 
				", nCdFormato=" + nCdFormato + ", nVlComprimento=" + nVlComprimento + ", nVlAltura=" + nVlAltura + 
				", nVlLargura=" + nVlLargura + ", nVlDiametro=" + nVlDiametro + ", sCdMaoPropria=" + sCdMaoPropria + 
				", nVlValorDeclarado=" + nVlValorDeclarado + ", sCdAvisoRecebimento=" + sCdAvisoRecebimento + 
				", StrRetorno=" + StrRetorno + " ]";
	}
}
