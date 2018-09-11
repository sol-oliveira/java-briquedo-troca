package brinquedotroca.repository;

import javax.persistence.EntityManager;

import brinquedotroca.entities.Brinquedo;
import brinquedotroca.entities.Usuario;

public class BrinquedoRepository {
	
	
	private EntityManager manager ;
	
	
	public BrinquedoRepository (EntityManager manager) {
		this.manager = manager;
	}
	

	public void adicionaBrinquedo (Brinquedo brinquedo) {
		
		this.manager.persist(brinquedo);
	}
	
		

}
