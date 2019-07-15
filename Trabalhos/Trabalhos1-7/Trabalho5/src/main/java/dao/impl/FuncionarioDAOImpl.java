package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.FuncionarioDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;

@Repository
public class FuncionarioDAOImpl implements FuncionarioDAO {

	@PersistenceContext
    protected EntityManager em;
	
	@Override
	public int inclui(Funcionarios funcionario) {
		em.persist(funcionario);
		return funcionario.getCodigo();
	}

	@Override
	public void exclui(int codigo) throws ObjetoNaoEncontradoException {
		Funcionarios funcionario = em.find(Funcionarios.class, codigo, LockModeType.PESSIMISTIC_WRITE);
		
		if(funcionario == null) {
			throw new ObjetoNaoEncontradoException();
		}
		
		em.remove(funcionario);

	}

	@Override
	public Funcionarios recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		Funcionarios funcionario = (Funcionarios) em.find(Funcionarios.class, codigo);
		
		if(funcionario == null) {
			throw new ObjetoNaoEncontradoException();
		}
		
		return funcionario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Funcionarios> recuperaFuncionarios(Lojas loja) throws ObjetoNaoEncontradoException {
		return em.createQuery("select f from Funcionarios f order by f.id").getResultList();
	}

}
