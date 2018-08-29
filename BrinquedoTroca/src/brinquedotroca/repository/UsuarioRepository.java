package brinquedotroca.repository;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import brinquedotroca.entities.Usuario;

public class UsuarioRepository {

	private EntityManager manager ;
	
	
	public UsuarioRepository (EntityManager manager) {
		this.manager = manager;
	}
	

	public void adicionaUsuario (Usuario usuario) {
		
		this.manager.persist(usuario);
	}
		
}

