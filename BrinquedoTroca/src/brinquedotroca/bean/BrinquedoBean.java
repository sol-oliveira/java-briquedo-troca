package brinquedotroca.bean;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import brinquedotroca.entities.Brinquedo;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.BrinquedoRepository;
import brinquedotroca.repository.UsuarioRepository;

@ManagedBean
public class BrinquedoBean {
	
	
	File foto;
	String descricao;
	private int idUsuario;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public File getFoto() {
		return foto;
	}
	public void setFoto(File foto) {
		this.foto = foto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public String cadastrarBrinquedo() {
		
		EntityManagerFactory factory =	Persistence.createEntityManagerFactory("brinquedotroca");
		
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		
		BrinquedoRepository brinquedorepository = new BrinquedoRepository(manager);
		
		UsuarioRepository usuariorepository = new UsuarioRepository(manager);
		
		Usuario usuario = usuariorepository.buscaIdUsuario(idUsuario);
		
		Brinquedo brinquedo = new Brinquedo();
		
		brinquedo.setDescricao(descricao);
		brinquedo.setFoto(foto);	
		brinquedo.setUsuario(usuario);
		
	
		brinquedorepository.adicionaBrinquedo(brinquedo);	
			
		manager.getTransaction().commit();
		manager.close();
		factory.close();			
		
		return "home";
	}
	

}
