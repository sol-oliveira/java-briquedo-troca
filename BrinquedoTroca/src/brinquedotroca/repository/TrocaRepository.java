package brinquedotroca.repository;
import javax.persistence.EntityManager;
import brinquedotroca.entities.Troca;


public class TrocaRepository {

	private EntityManager manager ;	

	public TrocaRepository(EntityManager manager) {
		this.manager=manager;

	}

	public void adiciona(Troca troca) {
		this.manager.persist(troca);
	}

	public Troca buscaId(int id) {
		return this.manager.find (Troca.class, id);
	}
}
