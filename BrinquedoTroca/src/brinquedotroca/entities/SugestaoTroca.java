package brinquedotroca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name="SugestaoTroca")
public class SugestaoTroca {

	@Id
	@GeneratedValue
	int idTroca;

	@Column (name="mensagem", nullable=false)
	String mensagem;

	@Column (name="destinatario", nullable=false)
	String destinatario;  

	@Column (name="brinquedo_destinatario", nullable=false)
	String brinquedo_destinatario; 

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn( name="brinquedo_remetente")  
	Brinquedo brinquedo_remetente;


	public int getIdTroca() {
		return idTroca;
	}


	public void setIdTroca(int idTroca) {
		this.idTroca = idTroca;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Brinquedo getBrinquedo() {
		return brinquedo_remetente;
	}


	public void setBrinquedo(Brinquedo brinquedo) {
		this.brinquedo_remetente = brinquedo;
	}





}
