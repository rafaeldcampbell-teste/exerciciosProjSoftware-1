
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@NamedQueries({	@NamedQuery(name = "Funcionarios.recuperaFuncionarios", query = "select f from Funcionarios f order by f.id")})


@Entity
@Table(name = "funcionarios")
public class Funcionarios {
	

	private Long codigo;
	
	private String nome;

	private String funcao;
	
	
	private Lojas loja;
	private List<Mesas> mesas = new ArrayList<Mesas>();
	
	//===========construtor========================
	
	public Funcionarios() {
		
	}
	
	public Funcionarios(String nome, String funcao, Lojas loja) {
		this.nome = nome;
		this.funcao = funcao;
		this.loja = loja;
	}

	
	//============getters==========================
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	public Long getCodigo() {
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
	
	public void setLoja(Lojas loja) {
		this.loja = loja;
	}
	
	public void setMesas(List<Mesas> mesas) {
		this.mesas = mesas;
	}
	

    public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	//============metodos de associacao=============6

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOJAS_ID")
    public Lojas getLoja() {
    	return loja;
    }
    
    @OneToMany(mappedBy = "funcionario")
    @OrderBy
    public List<Mesas> getMesas() {
    	return mesas;
    }

}