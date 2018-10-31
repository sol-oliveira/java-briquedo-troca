package brinquedotroca.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="Brinquedo")
public class Brinquedo{	

	@Id
	@GeneratedValue
	int id;

	@Lob
	@Column (name="foto")
	byte[] foto;	

	@Column (name="descricao", nullable=false)
	String descricao;	

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn( name="usuario_id")  
	Usuario usuario; 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	String nomeFoto = "";

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}

	public String getNomeFoto() {
		return this.nomeFoto;
	}

}
