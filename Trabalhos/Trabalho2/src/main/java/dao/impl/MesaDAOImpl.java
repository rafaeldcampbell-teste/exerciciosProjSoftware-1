package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import dao.MesaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import modelo.Mesas;
import util.JPAUtil;

public class MesaDAOImpl implements MesaDAO {

	@Override
	public int inclui(Mesas mesa) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			em.persist(mesa);
			return mesa.getId();
		}catch(RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();

		    Mesas mesa = (Mesas) em.find(Mesas.class, id, LockModeType.PESSIMISTIC_WRITE);

		    if (mesa == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }

		    em.remove(mesa);
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public Mesas recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();

		    Mesas mesa = (Mesas) em.find(Mesas.class, id);

		    if (mesa == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }

		    return mesa;
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}


	@Override
	public List<Mesas> recuperaMesasPorLoja(Lojas loja) throws ObjetoNaoEncontradoException {
		EntityManager em = JPAUtil.getEntityManager();

		String busca = "select m from Mesas m " + 
		               "where m.loja.id = :idLoja " + 
			       "order by m.numero";

		@SuppressWarnings("unchecked")
		List<Mesas> mesas = em.createQuery(busca).setParameter("idLoja", loja.getId()).getResultList();
		return mesas;
	}

	@Override
	public List<Mesas> recuperaMesasPorFuncionario(Funcionarios funcionario) throws ObjetoNaoEncontradoException {
		EntityManager em = JPAUtil.getEntityManager();

		String busca = "select m from Mesas m " + 
		               "where m.funcionario.codigo = :codigoFuncionario " + 
			       "order by m.numero";

		@SuppressWarnings("unchecked")
		List<Mesas> mesas = em.createQuery(busca).setParameter("codigoFuncionario", funcionario.getCodigo()).getResultList();

		return mesas;
	}

}
