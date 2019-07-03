package modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Usuario.recuperaListaUsuarios", query = "select u from Usuario u where u.conta = :conta and u.senha = :senha"),
	@NamedQuery(name = "Usuario.recuperaPerfis", query = "select p from Perfil p where p.usuario.conta = :conta order by p.perfil")})

@Entity
@Table(name = "usuarios")
public class Usuario {
	private String conta;
	private String senha;
	private List<Perfil> perfis;
	
	public Usuario() {
		
	}
	
	public Usuario(String conta, String senha) {
		this.conta = conta;
		this.senha = senha;
	}

	@Id
	@Column(name = "conta")
	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	@Column(name = "senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	@OneToMany(mappedBy = "usuario")
    @OrderBy
    public List<Perfil> getPerfis() {
    	return this.perfis;
    }

}
