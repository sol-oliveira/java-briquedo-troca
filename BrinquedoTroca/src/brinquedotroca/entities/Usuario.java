package brinquedotroca.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name="Usuario")
public class Usuario {
	
	@Id
	@GeneratedValue
	int idUsuario;
	
	@Column (name="nome", nullable=false)
	String nome;
	
	@Column (name="email", nullable=false)
	String email;
	
	@Column (name="senha", nullable=false)
	String senha;

	/*
	@OneToMany
	List<Brinquedo>brinquedo; */
	
	//depois que resolver o login, vc apaga isso aqui
	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

/*	public List<Brinquedo> getBrinquedo() {
		return brinquedo;
	}

	public void setBrinquedo(List<Brinquedo> brinquedo) {
		this.brinquedo = brinquedo;
	} 
*/



}
