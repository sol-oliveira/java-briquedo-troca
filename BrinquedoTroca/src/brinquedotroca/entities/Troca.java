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
	int id;

	String finalizada = "";

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="destinatario", nullable=false)
	Brinquedo destinatario; 

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="remetente", nullable=false)
	Brinquedo remetente;

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


	public int getIdTroca() {
		return id;
	}

	public void setIdTroca(int idTroca) {
		this.id = idTroca;
	}

	public Brinquedo getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Brinquedo destinatario) {
		this.destinatario = destinatario;
	}

	public Brinquedo getRemetente() {
		return remetente;
	}

	public void setRemetente(Brinquedo remetente) {
		this.remetente = remetente;
	}

}
