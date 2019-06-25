package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import dao.AtendimentoDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;
import util.JPAUtil;

public class AtendimentoDAOImpl implements AtendimentoDAO {

	@Override
	public int inclui(Atendimentos atendimento) {
		try {
			
		    EntityManager em = JPAUtil.getEntityManager();
		    em.persist(atendimento);
		    return atendimento.getId();
		    
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		try {
		    
			EntityManager em = JPAUtil.getEntityManager();
		    Atendimentos atendimento = em.find(Atendimentos.class, id, LockModeType.PESSIMISTIC_WRITE);
		    if (atendimento == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }
		    em.remove(atendimento);
		    
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public Atendimentos recuperaAtendimento(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();
		    Atendimentos atendimento = (Atendimentos) em.find(Atendimentos.class, id);

		    if (atendimento == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }

		    return atendimento;
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();

		    Atendimentos atendimento = em.find(Atendimentos.class, id, LockModeType.PESSIMISTIC_WRITE);

		    if (atendimento == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }
		    
		    atendimento.adicionaValor(valor);
		    em.merge(atendimento);
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimentos> recuperaAtendimentos(Mesas mesa) {
		EntityManager em = JPAUtil.getEntityManager();
		String busca = "select a from Atendimentos a "
					  +"where a.mesa.id = :idMesa "
					  +"order by a.id";
		
		return em.createQuery(busca).setParameter("idMesa", mesa.getId()).getResultList();
	}

}
