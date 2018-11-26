package brinquedotroca.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import brinquedotroca.entities.Troca;


public class TrocaRepository {

	private EntityManager manager ;	

	public TrocaRepository(EntityManager manager) {
		this.manager=manager;
	}

	public void adiciona(Troca troca) {
		this.manager.persist(troca);
	}

	@SuppressWarnings("unchecked")
	public List<Troca> buscaMinhaTroca (int id) {
		Query query;
	/*	String buscatroca = "SELECT "
				+ "t.*, rb.*, db.* "
				+ "FROM troca t "
				+ "INNER JOIN brinquedo rb ON t.remetente = rb.id "
				+ "INNER JOIN brinquedo db ON t.destinatario = db.id "
				+ "and rb.id != ?"; */	
		
		String buscatroca = "Select "
				+ "rb.id as id2, rb.descricao as descricao2, rb.foto as foto2, rb.nomeFoto as nomeFoto2," 
				+" eb.*, t.id as id3, t.finalizada as finalizada "  
				+" FROM troca t " 
				+" INNER JOIN brinquedo rb ON t.remetente = rb.id"  
				+" INNER JOIN brinquedo eb ON t.destinatario = eb.id" 
				+" and rb.id != ? ";	
		

		try {
			query = this.manager.createNativeQuery(buscatroca).setParameter("usuario_id", id); 
		} catch (Exception e) {
			return new  ArrayList<>();
		}

		return query.getResultList();
	}
}
