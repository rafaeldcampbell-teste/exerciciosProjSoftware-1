package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Lojas.recuperaLojas", query = "select l from Lojas l order by l.id")})

@Entity
@Table(name = "lojas")
public class Lojas {
	

	private Long id;
	
	private String endereco;
	
	private List<Funcionarios> funcionarios = new ArrayList<Funcionarios>();
	private List<Mesas> mesas = new ArrayList<Mesas>();
	
	// ==============Construtores==========================
	public Lojas() {
		
	}
	
	public Lojas(String endereco) {
		this.endereco = endereco;
	}
	
	//===============Getters================================
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    public Long getId() {
		return id;
    }

    @Column(name = "endereco")
    public String getEndereco() {
    	return endereco;
    }
    
    //====================Setters============================
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public void setEndereco(String endereco) {
    	this.endereco = endereco;
    }
    
    public void setFuncionarios(List<Funcionarios> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void setMesas(List<Mesas> mesas) {
		this.mesas = mesas;
	}
    
    
    //================Métodos para Associações================


	@OneToMany(mappedBy = "loja")
    @OrderBy
    public List<Funcionarios> getFuncionarios() {
    	return funcionarios;
    }
    
    @OneToMany(mappedBy = "loja")
    @OrderBy
    public List<Mesas> getMesas() {
    	return mesas;
    }
	
}

