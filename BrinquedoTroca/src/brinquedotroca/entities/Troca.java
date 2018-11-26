package brinquedotroca.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name="Troca")
public class Troca {

	@Id
	@GeneratedValue
	private int id;	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="remetente", nullable=false)
	private Brinquedo remetente; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="destinatario", nullable=false)
	private Brinquedo destinatario; 

	private String finalizada = "";

	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}

	public String getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(String finalizada) {
		this.finalizada = finalizada;
	}

	public Brinquedo getRemetente() {
		return remetente;
	}

	public void setRemetente(Brinquedo remetente) {
		this.remetente = remetente;
	}

	public Brinquedo getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Brinquedo destinatario) {
		this.destinatario = destinatario;
	}

}
