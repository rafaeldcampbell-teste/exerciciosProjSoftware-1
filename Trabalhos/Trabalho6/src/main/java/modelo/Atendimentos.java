package modelo;


import java.util.Calendar;

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
import javax.persistence.Table;
import javax.persistence.Transient;

@NamedQueries({ @NamedQuery(name = "Atendimentos.recuperaAtendimentos", query = "select a from Atendimentos a where a.mesa.id = ?1 order by a.id")})


@Entity
@Table(name = "atendimentos")
public class Atendimentos {

	private Long id;
	
	private Calendar inicioDoAtendimento;
	
	private Calendar fimDoAtendimento;
	
	private float valorTotalConta;
	
	private Mesas mesa;
	
	//===============construtor=================================
	
	public Atendimentos () {
		
	}
	
	public Atendimentos(Calendar inicioDoAtendimento, Calendar fimDoAtendimento, float valorTotalConta, Mesas mesa) {
		this.inicioDoAtendimento = inicioDoAtendimento;
		this.fimDoAtendimento = fimDoAtendimento;
		this.valorTotalConta = valorTotalConta;
		this.mesa = mesa;
	}

	
	//================getters====================================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	@Column(name = "inicioDoAtendimento")
	public Calendar getInicioDoAtendimento() {
		return inicioDoAtendimento;
	}

	
	@Column(name = "fimDoAtendimento")
	public Calendar getFimDoAtendimento() {
		return fimDoAtendimento;
	}

	@Column(name = "valorTotalConta")
	public float getValorTotalConta() {
		return valorTotalConta;
	}

	//================setters=========================================
	

	public void setInicioDoAtendimento(Calendar inicioDoAtendimento) {
		this.inicioDoAtendimento = inicioDoAtendimento;
	}

	public void setFimDoAtendimento(Calendar fimDoAtendimento) {
		this.fimDoAtendimento = fimDoAtendimento;
	}

	public void setValorTotalConta(float valorTotalConta) {
		this.valorTotalConta = valorTotalConta;
	}

	public void setMesa(Mesas mesa) {
		this.mesa = mesa;
	}
	
	@Transient
	public void adicionaValor(float valor) {
		this.valorTotalConta += valor;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	//================metodos de associacao==============================
	


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESAS_ID")
    public Mesas getMesa() {
    	return mesa;
    }
	

}
