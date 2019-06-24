package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Repository;

import dao.FuncionarioDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import util.JPAUtil;

@Repository
public class FuncionarioDAOImpl implements FuncionarioDAO {

	@Override
	public int inclui(Funcionario funcionario) {
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
			
			Funcionario funcionario = em.find(Funcionario.class, codigo, LockModeType.PESSIMISTIC_WRITE);
			
			if(funcionario == null) {
				throw new ObjetoNaoEncontradoException();
			}
			
			em.remove(funcionario);
		}catch(RuntimeException e) {
		    throw new InfraestruturaException(e);
		}

	}

	@Override
	public Funcionario recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			Funcionario funcionario = (Funcionario) em.find(Funcionario.class, codigo);
			
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
	public List<Funcionario> recuperaFuncionarios(Loja loja) throws ObjetoNaoEncontradoException {
		EntityManager em = JPAUtil.getEntityManager();

		return em.createQuery("select f from Funcionarios f order by f.id").getResultList();
	}

}
