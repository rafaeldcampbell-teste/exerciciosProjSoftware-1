
package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	@Column
	private String nome;

	@Column
	private String funcao;
	
	
	private Loja loja;
	private List<Mesa> mesas = new ArrayList<Mesa>();
	
	//===========construtor========================
	
	public Funcionario() {
		
	}
	
	public Funcionario(String nome, String funcao, Loja loja) {
		this.nome = nome;
		this.funcao = funcao;
		this.loja = loja;
	}

	
	//============getters==========================
	
	@Column(name = "codigo")
	public int getCodigo() {
		return codigo;
	}

	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	@Column(name = "funcao")
	public String getFuncao() {
		return funcao;
	}
	
	//=============setters==========================
	
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	
	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}
	

	//============metodos de associacao=============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOJAS_ID")
    public Loja getLoja() {
    	return loja;
    }
    
    @OneToMany(mappedBy = "funcionario")
    @OrderBy
    public List<Mesa> getMesas() {
    	return mesas;
    }

}