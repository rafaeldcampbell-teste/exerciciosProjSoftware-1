
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

@NamedQueries({ @NamedQuery(name = "Mesas.recuperaMesasPorLoja", query = "select m from Mesas m where m.loja = ?1 order by m.numero"),
	@NamedQuery(name = "Mesas.recuperaMesasPorFuncionario", query = "select m from Mesas m where m.funcionario = ?1 order by m.numero")})


@Entity
@Table(name = "mesas")
public class Mesas {
	

	private Long id;
	
	private int numero;
	
	private Funcionarios funcionario;
	private Lojas loja;
	private List<Atendimentos> atendimentos = new ArrayList<Atendimentos>();
	
	//===========construtor========================
	
	public Mesas() {
		
	}
	
	public Mesas(Lojas loja, Funcionarios funcionario, int numero) {
		this.loja = loja;
		this.funcionario = funcionario;
		this.numero = numero;
	}

	
	//============getters==========================
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}
	
	@Column(name = "numero")
	public int getNumero() {
		return numero;
	}
	
	//===========setters============================
	
    
    public void setFuncionario(Funcionarios funcionario) {
		this.funcionario = funcionario;
	}

	public void setLoja(Lojas loja) {
		this.loja = loja;
	}

	public void setAtendimentos(List<Atendimentos> atendimentos) {
		this.atendimentos = atendimentos;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	//============metodos de associacao=============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOJAS_ID")
    public Lojas getLoja() {
    	return loja;
    }

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCIONARIOS_ID")
    public Funcionarios getFuncionario() {
    	return funcionario;
    }
    
    @OneToMany(mappedBy = "mesa")
    @OrderBy
    public List<Atendimentos> getAtendimentos() {
    	return atendimentos;
    }

}