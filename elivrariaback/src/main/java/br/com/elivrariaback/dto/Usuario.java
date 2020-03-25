package br.com.elivrariaback.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Insira o nome!")
	@Column(name = "nome")
	private String nome;
	
	@NotBlank(message = "Insira o sobrenome!")
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@NotBlank(message = "Insira o gênero!")
	@Column(name = "genero")
	private String genero;
	
	@NotBlank(message = "Insira a data de nascimento!")
	@Column(name = "dtnascimento")
	private String dtNascimento;
	
	@NotBlank(message = "Insira o email!")	
	private String email;
	
	@NotBlank(message = "Insira o DDD do Telefone!")
	@Column(name = "ddd_telefone")
	private String dddTelefone;
	
	@NotBlank(message = "Insira o telefone de contato!")
	@Column(name = "telefone")
	private String telefone;
	
	private String role;
	@NotBlank(message = "Insira a senha!")
	private String senha;
	private boolean ativo = true;
	@Transient
	private String confirmaSenha;

	
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	
	
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getDddTelefone() {
		return dddTelefone;
	}
	public void setDddTelefone(String dddTelefone) {
		this.dddTelefone = dddTelefone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", genero=" + genero 
				+ ", dtnascimento=" + dtNascimento + ", email=" + email + ", ddd_telefone=" + dddTelefone
				+ ", telefone=" + telefone + ", role=" + role + ", senha=" + senha + ", ativo="
				+ ativo + "]";
	}
	
	
	@OneToOne(mappedBy="usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Carrinho carrinho;
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
}
