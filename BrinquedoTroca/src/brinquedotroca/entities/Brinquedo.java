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
public class Brinquedo {
	@Id
	@GeneratedValue
	int idBrinquedo;

	@Column (name="foto", nullable=false, length=100000)
	@Lob
	byte[] foto; 

	@Column (name="descricao", nullable=false)
	String descricao;

	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn( name="usuario_id")  
	Usuario usuario;  
	
	@Column (name="imagem_convertida")
	String imagemConvertida;


	public String getImagemConvertida() {
		return imagemConvertida;
	}

	public int getIdBrinquedo() {
		return idBrinquedo;
	}

	public void setIdBrinquedo(int idBrinquedo) {
		this.idBrinquedo = idBrinquedo;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] in) {
		this.foto = in;
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

	public void setImagemConvertida(String imagemConvertida) {
		this.imagemConvertida = imagemConvertida;
	} 

}
