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
		
	public Usuario buscaAutenticacao(String usuario) {
		Usuario result = null;
		try{
			result = (Usuario)manager
	                .createQuery(
	                            "SELECT u from Usuario u where u.nome = :nome" )
	                .setParameter("nome", usuario).getSingleResult();
		}catch(Exception e){
			return null;
		}
		
		return result;	
	}
	
	public Usuario buscaIdUsuario (int id) {
		return this.manager.find (Usuario.class, id);
	}
	
}

