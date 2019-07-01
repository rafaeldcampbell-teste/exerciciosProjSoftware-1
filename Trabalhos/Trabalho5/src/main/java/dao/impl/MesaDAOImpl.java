package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.MesaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import modelo.Mesas;

@Repository
public class MesaDAOImpl implements MesaDAO {

	@PersistenceContext
    protected EntityManager em;
	
	@Override
	public int inclui(Mesas mesa) {
		em.persist(mesa);
		return mesa.getId();
	}

	@Override
	public void exclui(int id) throws ObjetoNaoEncontradoException {
	    Mesas mesa = (Mesas) em.find(Mesas.class, id, LockModeType.PESSIMISTIC_WRITE);

	    if (mesa == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }

	    em.remove(mesa);
	}

	@Override
	public Mesas recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException {
	    Mesas mesa = (Mesas) em.find(Mesas.class, id);

	    if (mesa == null) {
	    	throw new ObjetoNaoEncontradoException();
	    }

	    return mesa;
	}


	@Override
	public List<Mesas> recuperaMesasPorLoja(Lojas loja) throws ObjetoNaoEncontradoException {
		String busca = "select m from Mesas m " + 
		               "where m.loja.id = :idLoja " + 
			       "order by m.numero";

		@SuppressWarnings("unchecked")
		List<Mesas> mesas = em.createQuery(busca).setParameter("idLoja", loja.getId()).getResultList();
		return mesas;
	}

	@Override
	public List<Mesas> recuperaMesasPorFuncionario(Funcionarios funcionario) throws ObjetoNaoEncontradoException {
		String busca = "select m from Mesas m " + 
		               "where m.funcionario.codigo = :codigoFuncionario " + 
			       "order by m.numero";

		@SuppressWarnings("unchecked")
		List<Mesas> mesas = em.createQuery(busca).setParameter("codigoFuncionario", funcionario.getCodigo()).getResultList();

		return mesas;
	}

}
