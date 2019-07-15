package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "perfis")
public class Perfil {
	private int id;
	private Usuario usuario;
	private String perfil;
	
	public Perfil() {
		
	}
	
	public Perfil(int id, Usuario usuario, String perfil) {
		this.id = id;
		this.usuario = usuario;
		this.perfil = perfil;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}
	
	@Column(name = "perfil")
	public String getPerfil() {
		return perfil;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTA_ID")
    public Usuario getUsuario() {
    	return this.usuario;
    }
}
