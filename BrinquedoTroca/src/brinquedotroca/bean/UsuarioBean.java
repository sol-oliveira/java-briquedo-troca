package brinquedotroca.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.UsuarioRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ManagedBean
public class UsuarioBean {

	private String nome;
	private String senha;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void adiciona() {

		EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");		
		EntityManager manager = factory.createEntityManager();		
		manager.getTransaction().begin();				
		UsuarioRepository usuariorepository = new UsuarioRepository(manager);

		if(this.nome.equals("") || this.senha.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(String.format(" Falha no cadastro. Por favor, preencha todos os campos! ")));			
		}else {
			Usuario usuario = new Usuario();	
			usuario.setNome(nome);		
			usuario.setSenha(senha);

			usuariorepository.adiciona(usuario);		
			usuariorepository.autentica(nome);

			manager.getTransaction().commit();
			manager.close();
			factory.close();

			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(String.format(" Cadastrado realizado com sucesso! Por favor, logar-se! ")));


		}

	}

}
