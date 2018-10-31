package brinquedotroca.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

import brinquedotroca.entities.Troca;
import brinquedotroca.entities.Brinquedo;
import brinquedotroca.entities.Usuario;
import brinquedotroca.repository.TrocaRepository;
import brinquedotroca.repository.BrinquedoRepository;

public class TrocaBean {
	
	private int destinario;
	private int remetente;
	
	public int getDestinario() {
		return destinario;
	}

	public void setDestinario(int destinario) {
		this.destinario = destinario;
	}

	public int getRemetente() {
		return remetente;
	}

	public void setRemetente(int remetente) {
		this.remetente = remetente;
	}

	public void submit() throws IOException {
				
		EntityManagerFactory factory = 	Persistence.createEntityManagerFactory("brinquedotroca");			
		EntityManager manager = factory.createEntityManager();			
		manager.getTransaction().begin();
		
		BrinquedoRepository toyrepository = new BrinquedoRepository(manager);
		Brinquedo toy = toyrepository.bucaId(remetente) ;		
		remetente = toy.getId();
				
		TrocaRepository trocarepository = new TrocaRepository(manager);
		
		Troca troca = new Troca();
		troca.setRemetente(toy);		
		trocarepository.adiciona(troca);
		
		manager.getTransaction().commit();
		manager.close();
		factory.close();
}
	
}
