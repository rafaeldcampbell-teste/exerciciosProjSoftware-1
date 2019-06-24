package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Repository;

import dao.MesaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import modelo.Mesa;
import util.JPAUtil;

@Repository
public class MesaDAOImpl implements MesaDAO {

	@Override
	public int inclui(Mesa mesa) {
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

		    Mesa mesa = (Mesa) em.find(Mesa.class, id, LockModeType.PESSIMISTIC_WRITE);

		    if (mesa == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }

		    em.remove(mesa);
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public Mesa recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException {
		try {
		    EntityManager em = JPAUtil.getEntityManager();

		    Mesa mesa = (Mesa) em.find(Mesa.class, id);

		    if (mesa == null) {
		    	throw new ObjetoNaoEncontradoException();
		    }

		    return mesa;
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}


	@Override
	public List<Mesa> recuperaMesasPorLoja(Loja loja) throws ObjetoNaoEncontradoException {
		EntityManager em = JPAUtil.getEntityManager();

		String busca = "select m from mesas m " + 
		               "where m.loja.id = :idLoja" + 
			       "order by m.numero, m.atendimento";

		@SuppressWarnings("unchecked")
		List<Mesa> mesas = em.createQuery(busca).setParameter("idLoja", loja.getId()).getResultList();

		if (mesas.isEmpty())
		    throw new ObjetoNaoEncontradoException();
		else
		    return mesas;
	}

	@Override
	public List<Mesa> recuperaMesasPorFuncionario(Funcionario funcionario) throws ObjetoNaoEncontradoException {
		EntityManager em = JPAUtil.getEntityManager();

		String busca = "select m from mesas m " + 
		               "where m.funcionario.codigo = :codigoFuncionario" + 
			       "order by m.numero, m.atendimento";

		@SuppressWarnings("unchecked")
		List<Mesa> mesas = em.createQuery(busca).setParameter("codigoFuncionario", funcionario.getCodigo()).getResultList();

		if (mesas.isEmpty())
		    throw new ObjetoNaoEncontradoException();
		else
		    return mesas;
	}

}
