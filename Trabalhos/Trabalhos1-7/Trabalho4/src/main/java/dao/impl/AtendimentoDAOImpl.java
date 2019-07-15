package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.AtendimentoDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;

@Repository
public class AtendimentoDAOImpl implements AtendimentoDAO {
	
	@PersistenceContext
    protected EntityManager em;
	
	@Override
	public int inclui(Atendimentos atendimento) {
	    em.persist(atendimento);
	    return atendimento.getId();
	}

	@Override
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		
	    Atendimentos atendimento = em.find(Atendimentos.class, id, LockModeType.PESSIMISTIC_WRITE);
	    if (atendimento == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }
	    em.remove(atendimento);
	}

	@Override
	public Atendimentos recuperaAtendimento(int id) throws ObjetoNaoEncontradoException {
		
	    Atendimentos atendimento = (Atendimentos) em.find(Atendimentos.class, id);

	    if (atendimento == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }

	    return atendimento;
	}

	@Override
	public void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException {
		Atendimentos atendimento = em.find(Atendimentos.class, id, LockModeType.PESSIMISTIC_WRITE);

	    if (atendimento == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }
	    
	    atendimento.adicionaValor(valor);
	    em.merge(atendimento);
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimentos> recuperaAtendimentos(Mesas mesa) {
		String busca = "select a from Atendimentos a "
					  +"where a.mesa.id = :idMesa "
					  +"order by a.id";
		
		return em.createQuery(busca).setParameter("idMesa", mesa.getId()).getResultList();
	}

}
