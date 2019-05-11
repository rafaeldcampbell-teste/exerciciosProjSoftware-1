
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
@Table(name = "mesas")
public class Mesa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int numero;
	
	private Funcionario funcionario;
	private Loja loja;
	private List<Atendimento> atendimentos = new ArrayList<Atendimento>();
	
	//===========construtor========================
	
	public Mesa() {
		
	}
	
	public Mesa(Loja loja, Funcionario funcionario, int numero) {
		this.loja = loja;
		this.funcionario = funcionario;
		this.numero = numero;
	}

	
	//============getters==========================
	
	@Column(name = "id")
	public int getId() {
		return this.id;
	}
	
	@Column(name = "numero")
	public int getNumero() {
		return numero;
	}
	
	//===========setters============================
	
    
    public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	
	//============metodos de associacao=============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOJAS_ID")
    public Loja getLoja() {
    	return loja;
    }

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCIONARIOS_ID")
    public Funcionario getFuncionario() {
    	return funcionario;
    }
    
    @OneToMany(mappedBy = "mesa")
    @OrderBy
    public List<Atendimento> getAtendimentos() {
    	return atendimentos;
    }

}