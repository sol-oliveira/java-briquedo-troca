package brinquedotroca.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import brinquedotroca.entities.Brinquedo;

public class BrinquedoRepository {

	private EntityManager manager ;

	public BrinquedoRepository(EntityManager manager) {
		this.manager = manager ;
	}	

	public void adiciona (Brinquedo toy) {
		this.manager.persist(toy);
	}

	public Brinquedo buscaId (int id) {
		return this.manager.find (Brinquedo.class , id);
	}

	@SuppressWarnings("unchecked")
	public List<Brinquedo> busca (int id) {
		Query query;
		try {
			query = this.manager.createQuery("SELECT b FROM Brinquedo b where b.usuario.id = :usuario_id").setParameter("usuario_id", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new  ArrayList<>();
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Brinquedo> buscaTodos(int id) {
		Query query;
		try {
			query = this.manager.createQuery("SELECT b FROM Brinquedo b where b.usuario.id != :usuario_id").setParameter("usuario_id", id);

		}catch (Exception e) {
			System.out.println(e.getMessage());
			return new  ArrayList<>();
		}
		return query.getResultList();
	}

	public void deletarBrinquedo(int id) {
		// TODO Auto-generated method stub		 
		Brinquedo b = this.manager.find (Brinquedo.class, id);
		this.manager.remove(b);

	}
}


