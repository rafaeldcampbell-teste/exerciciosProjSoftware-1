package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "lojas")
public class Loja {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String endereco;
	
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<Mesa> mesas = new ArrayList<Mesa>();
	
	// ==============Construtores==========================
	public Loja() {
		
	}
	
	public Loja(String endereco) {
		this.endereco = endereco;
	}
	
	//===============Getters================================

	@Column(name = "id")
    public int getId() {
		return id;
    }

    @Column(name = "endereco")
    public String getEndereco() {
    	return endereco;
    }
    
    //================Métodos para Associações================
    
    @OneToMany(mappedBy = "loja")
    @OrderBy
    public List<Funcionario> getFuncionarios() {
    	return funcionarios;
    }
    
    @OneToMany(mappedBy = "loja")
    @OrderBy
    public List<Mesa> getMesas() {
    	return mesas;
    }
	
}

