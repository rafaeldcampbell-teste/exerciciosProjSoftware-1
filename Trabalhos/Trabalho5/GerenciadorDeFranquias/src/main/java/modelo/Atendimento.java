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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "atendimentos")
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Calendar inicioDoAtendimento;
	
	private Calendar fimDoAtendimento;
	
	private float valorTotalConta;
	
	private Mesa mesa;
	
	//===============construtor=================================
	
	public Atendimento () {
		
	}
	
	public Atendimento(Calendar inicioDoAtendimento, Calendar fimDoAtendimento, float valorTotalConta, Mesa mesa) {
		this.inicioDoAtendimento = inicioDoAtendimento;
		this.fimDoAtendimento = fimDoAtendimento;
		this.valorTotalConta = valorTotalConta;
		this.mesa = mesa;
	}

	
	//================getters====================================
	
	@Column(name = "id")
	public int getId() {
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

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	@Transient
	public void adicionaValor(float valor) {
		this.valorTotalConta += valor;
	}
	
	//================metodos de associacao==============================
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESAS_ID")
    public Mesa getMesa() {
    	return mesa;
    }
	

}
