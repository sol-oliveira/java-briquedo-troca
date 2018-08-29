package brinquedotroca.entities;

import java.io.File;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name="Brinquedo")
public class Brinquedo {
	@Id
	@GeneratedValue
	int idBrinquedo;

	@Column (name="foto", nullable=false)
	File foto;

	@Column (name="descricao", nullable=false)
	String descricao;


	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn( name="usuario_id")  
	Usuario usuario;  


	public int getIdBrinquedo() {
		return idBrinquedo;
	}

	public void setIdBrinquedo(int idBrinquedo) {
		this.idBrinquedo = idBrinquedo;
	}

	public File getFoto() {
		return foto;
	}

	public void setFoto(File foto) {
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

}
