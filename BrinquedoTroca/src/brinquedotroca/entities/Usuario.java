package brinquedotroca.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue
	private int id;

	@Column (name="nome", nullable=false)
	private String nome;

	@Column (name="senha", nullable=false)
	private String senha;

	public int getIdUsuario() {
		return id;
	}

	public void setIdUsuario(int idUsuario) {
		this.id = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
