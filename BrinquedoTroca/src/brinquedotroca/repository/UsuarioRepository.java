package brinquedotroca.repository;

import javax.persistence.EntityManager;

import brinquedotroca.entities.Usuario;

public class UsuarioRepository {

	private EntityManager manager ;

	public UsuarioRepository (EntityManager manager) {
		this.manager = manager;
	}

	public void adiciona (Usuario usuario) {

		this.manager.persist(usuario);
	}

	public Usuario autentica(String usuario) {
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

	public Usuario buscaId (int id) {

		return this.manager.find (Usuario.class, id);
	}

}

