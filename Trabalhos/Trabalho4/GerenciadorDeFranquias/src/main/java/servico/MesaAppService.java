package servico;

import java.util.List;

import dao.FuncionarioDAO;
import dao.LojaDAO;
import dao.MesaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import modelo.Mesa;
import util.FabricaDeDAOs;
import util.JPAUtil;

public class MesaAppService {

	private static MesaDAO mesaDAO = FabricaDeDAOs.getDAO(MesaDAO.class);
	private static FuncionarioDAO funcionarioDAO = FabricaDeDAOs.getDAO(FuncionarioDAO.class);
	private static LojaDAO lojaDAO = FabricaDeDAOs.getDAO(LojaDAO.class);
	
	public int inclui(Mesa mesa) {
		try{
			JPAUtil.beginTransaction();
			int id = mesaDAO.inclui(mesa);
			JPAUtil.commitTransaction();
			return id;
			
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao inserir mesa!");
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
	
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		try {
		    JPAUtil.beginTransaction();
		    mesaDAO.exclui(id);
		    JPAUtil.commitTransaction();
		    
		} catch (ObjetoNaoEncontradoException e) {
		    System.out.println("======> Mesa não encontrado!");
		    System.out.println("======> Executando rollback da transação!");
		    JPAUtil.rollbackTransaction();
		    throw e;
		} catch (InfraestruturaException e) {
			System.out.println("======> Erro ao excluir mesa!");
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
	
	public Mesa recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException {
		try{
			JPAUtil.beginTransaction();
			Mesa mesa = mesaDAO.recuperaUmaMesa(id);
			JPAUtil.commitTransaction();
			return mesa;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar mesa!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("======> Mesa não encontrado!");
			throw e;
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
	
	public List<Mesa> recuperaMesasPorLoja(int id) throws ObjetoNaoEncontradoException{
		try{
			JPAUtil.beginTransaction();
			Loja loja = lojaDAO.recuperaUmaLoja(id);
			List<Mesa> mesas = mesaDAO.recuperaMesasPorLoja(loja);
			JPAUtil.commitTransaction();
			
			if(mesas.isEmpty()) {
				System.out.println("======> Nenhuma mesa encontrada para esta loja!");
			}
			return mesas;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar mesas!");
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
	
	public List<Mesa> recuperaMesasPorFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		try{
			JPAUtil.beginTransaction();
			Funcionario funcionario = funcionarioDAO.recuperaUmFuncionario(codigo);
			List<Mesa> mesas = mesaDAO.recuperaMesasPorFuncionario(funcionario);
			JPAUtil.commitTransaction();
			
			if(mesas.isEmpty()) {
				System.out.println("======> Nenhuma mesa encontrada para este funcionario!");
			}
			return mesas;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar mesas!");
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
}
