package brinquedotroca.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Persistence;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.UsuarioRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@ManagedBean
@RequestScoped
public class UsuarioBean {
	
	String nome;
	String email;
	String senha;
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
	
	
	public String cadastrarUsuario() {
		
		EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();		
		
		UsuarioRepository usuariorepository = new UsuarioRepository(manager);
		
		Usuario usuario = new Usuario();	
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		usuariorepository.adicionaUsuario(usuario);		
		usuariorepository.buscaAutenticacao(nome);
		
		manager.getTransaction().commit();

		manager.close();

		factory.close();	
	
		
		return "usuariocadastrado";
	}

	
}
