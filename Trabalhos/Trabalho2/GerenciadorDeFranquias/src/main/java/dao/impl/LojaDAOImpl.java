package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import dao.LojaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Loja;
import util.JPAUtil;

public class LojaDAOImpl implements LojaDAO {

	@Override
	public int inclui(Loja loja) {
		try {
		    EntityManager em = JPAUtil.getEntityManager();
		    em.persist(loja);
		    return loja.getId();
		    
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();
		    Loja loja = em.find(Loja.class, id, LockModeType.PESSIMISTIC_WRITE);
		    if (loja == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }
		    em.remove(loja);
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public Loja recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();
		    Loja loja = (Loja) em.find(Loja.class, id);
		    if (loja == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }
		    return loja;
		    
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Loja> recuperaLojas() {
		EntityManager em = JPAUtil.getEntityManager();
		return em.createQuery("select l from Lojas l order by l.id").getResultList();
	}

}
