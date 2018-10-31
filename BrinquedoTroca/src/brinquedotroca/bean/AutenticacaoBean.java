package brinquedotroca.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.UsuarioRepository;

@ManagedBean
public class AutenticacaoBean {

	private String nome="";
	private String senha="" ;
	private int idUsuario;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String logar(){

		FacesContext fc = FacesContext.getCurrentInstance();

		if(this.nome == "" || this.senha == "" ){
			FacesMessage fm = new FacesMessage("Usuário e/ou senha inválidos.");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null,fm);
			return "index";
		}

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("brinquedotroca");
		EntityManager manager = factory.createEntityManager();
		UsuarioRepository usuarioRepository = new UsuarioRepository(manager);
		manager.getTransaction().begin();

		Usuario usuarioEntity = usuarioRepository.autentica(this.nome);
		manager.close();

		if(usuarioEntity != null && (usuarioEntity.getSenha().equals(this.senha))){
			factory.close();

			if (this.nome != null) {
				ExternalContext ec = fc.getExternalContext();
				HttpSession session = (HttpSession)ec.getSession(false);
				session.setAttribute("usuario", usuarioEntity);

				return "menu";
			} else {

				FacesMessage fm = new FacesMessage("Usuário e/ou senha inválidos.");
				fm.setSeverity(FacesMessage.SEVERITY_ERROR);
				fc.addMessage(null,fm);

				return "index";
			}
		}		
		return "index";
	}

	public String sair() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		return "index";
	}
}
