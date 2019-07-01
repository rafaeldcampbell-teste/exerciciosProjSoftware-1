package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.LojaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;

@Repository
public class LojaDAOImpl implements LojaDAO {

	
	@PersistenceContext
    protected EntityManager em;
	
	@Override
	public int inclui(Lojas loja) {
	    em.persist(loja);
	    return loja.getId();
	}

	@Override
	public void exclui(int id) throws ObjetoNaoEncontradoException {
	    Lojas loja = em.find(Lojas.class, id, LockModeType.PESSIMISTIC_WRITE);
	    if (loja == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }
	    em.remove(loja);
	}

	@Override
	public Lojas recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException {
	    Lojas loja = (Lojas) em.find(Lojas.class, id);
	    if (loja == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }
	    return loja;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lojas> recuperaLojas() {
		return em.createQuery("select l from Lojas l order by l.id").getResultList();
	}

}
