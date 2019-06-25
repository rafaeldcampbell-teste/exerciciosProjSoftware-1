package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import dao.FuncionarioDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import util.JPAUtil;

public class FuncionarioDAOImpl implements FuncionarioDAO {

	@Override
	public int inclui(Funcionarios funcionario) {
		try {
		    EntityManager em = JPAUtil.getEntityManager();

		    em.persist(funcionario);

		    return funcionario.getCodigo();
		} catch (RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@Override
	public void exclui(int codigo) throws ObjetoNaoEncontradoException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			Funcionarios funcionario = em.find(Funcionarios.class, codigo, LockModeType.PESSIMISTIC_WRITE);
			
			if(funcionario == null) {
				throw new ObjetoNaoEncontradoException();
			}
			
			em.remove(funcionario);
		}catch(RuntimeException e) {
		    throw new InfraestruturaException(e);
		}

	}

	@Override
	public Funcionarios recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			Funcionarios funcionario = (Funcionarios) em.find(Funcionarios.class, codigo);
			
			if(funcionario == null) {
				throw new ObjetoNaoEncontradoException();
			}
			
			return funcionario;
		}catch(RuntimeException e) {
		    throw new InfraestruturaException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionarios> recuperaFuncionarios(Lojas loja) throws ObjetoNaoEncontradoException {
		EntityManager em = JPAUtil.getEntityManager();

		return em.createQuery("select f from Funcionarios f order by f.id").getResultList();
	}

}
