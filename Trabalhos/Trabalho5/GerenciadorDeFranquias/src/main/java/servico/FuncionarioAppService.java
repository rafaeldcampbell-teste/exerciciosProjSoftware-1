package servico;

import java.util.List;

import dao.FuncionarioDAO;
import dao.LojaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import util.FabricaDeDAOs;
import util.JPAUtil;

public class FuncionarioAppService {

	private static FuncionarioDAO funcionarioDAO = FabricaDeDAOs.getDAO(FuncionarioDAO.class);
	private static LojaDAO lojaDAO = FabricaDeDAOs.getDAO(LojaDAO.class);
	
	public int inclui(Funcionario funcionario) {
		try{
			JPAUtil.beginTransaction();
			int id = funcionarioDAO.inclui(funcionario);
			JPAUtil.commitTransaction();
			return id;
			
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao inserir funcionario!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    	
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
	

	public void exclui(int codigo) throws ObjetoNaoEncontradoException {
		try {
		    JPAUtil.beginTransaction();
		    funcionarioDAO.exclui(codigo);
		    JPAUtil.commitTransaction();
		    
		} catch (ObjetoNaoEncontradoException e) {
		    System.out.println("======> Funcionario não encontrado!");
		    System.out.println("======> Executando rollback da transação!");
		    JPAUtil.rollbackTransaction();
		    throw e;
		} catch (InfraestruturaException e) {
			System.out.println("======> Erro ao excluir funcionario!");
			try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		    
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
	
	public Funcionario recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		try{
			JPAUtil.beginTransaction();
			Funcionario funcionario = funcionarioDAO.recuperaUmFuncionario(codigo);
			JPAUtil.commitTransaction();
			return funcionario;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar funcionario!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("======> Funcionario não encontrado!");
			throw e;
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
	
	public List<Funcionario> recuperaFuncionarios(int id) throws ObjetoNaoEncontradoException{
		try{
			JPAUtil.beginTransaction();
			Loja loja = lojaDAO.recuperaUmaLoja(id);
			List<Funcionario> funcionarios = funcionarioDAO.recuperaFuncionarios(loja);
			JPAUtil.commitTransaction();
			
			if(funcionarios.isEmpty()) {
				System.out.println("======> Nenhum funcionario encontrado para esta loja!");
			}
			return funcionarios;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar funcionarios!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("======> Loja não encontrada!");
			throw e;
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
}
