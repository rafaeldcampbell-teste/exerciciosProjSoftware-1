package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Repository;

import dao.AtendimentoDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimento;
import modelo.Mesa;
import util.JPAUtil;

@Repository
public class AtendimentoDAOImpl implements AtendimentoDAO {

	@Override
	public int inclui(Atendimento atendimento) {
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
		    Atendimento atendimento = em.find(Atendimento.class, id, LockModeType.PESSIMISTIC_WRITE);
		    if (atendimento == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }
		    em.remove(atendimento);
		    
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public Atendimento recuperaAtendimento(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();
		    Atendimento atendimento = (Atendimento) em.find(Atendimento.class, id);

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

		    Atendimento atendimento = em.find(Atendimento.class, id, LockModeType.PESSIMISTIC_WRITE);

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
	public List<Atendimento> recuperaAtendimentos(Mesa mesa) {
		EntityManager em = JPAUtil.getEntityManager();
		String busca = "select a from Atendimento a"
					  +"where a.mesa.id = :idMesa"
					  +"order by a.id";
		
		return em.createQuery(busca).setParameter("idMesa", mesa.getId()).getResultList();
	}

}
