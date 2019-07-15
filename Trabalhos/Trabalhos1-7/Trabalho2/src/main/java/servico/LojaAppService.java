package servico;

import java.util.List;

import dao.LojaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;
import util.FabricaDeDAOs;
import util.JPAUtil;

public class LojaAppService {

	private static LojaDAO lojaDAO = FabricaDeDAOs.getDAO(LojaDAO.class);
	
	public int inclui(Lojas loja) {
		try{
			JPAUtil.beginTransaction();
			int id = lojaDAO.inclui(loja);
			JPAUtil.commitTransaction();
			return id;
			
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao inserir loja!");
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
		    lojaDAO.exclui(id);
		    JPAUtil.commitTransaction();
		    
		} catch (ObjetoNaoEncontradoException e) {
		    System.out.println("======> Loja não encontrada!");
		    System.out.println("======> Executando rollback da transação!");
		    JPAUtil.rollbackTransaction();
		    throw e;
		} catch (InfraestruturaException e) {
			System.out.println("======> Erro ao excluir loja!");
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
	
	public Lojas recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException {
		try{
			JPAUtil.beginTransaction();
			Lojas loja = lojaDAO.recuperaUmaLoja(id);
			JPAUtil.commitTransaction();
			return loja;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar loja!");
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
	
	public List<Lojas> recuperaLojas() {
		try{
			JPAUtil.beginTransaction();
			//System.out.println("======> abre a transação!");
			//System.out.println("======> vai recuperar as lojas!");
			List<Lojas> lojas = lojaDAO.recuperaLojas();
			//System.out.println("======> recuperou as lojas !");

			//System.out.println("======> vai fechar a transação!");
			JPAUtil.commitTransaction();
			//System.out.println("======> fechou a transação!");
			
			if(lojas.isEmpty()) {
				System.out.println("======> Nenhuma loja encontrada!");
			}
			return lojas;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar funcionarios!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} finally {
			//System.out.println("======> vai fechar o entity manager!");
		    JPAUtil.closeEntityManager();
			//System.out.println("======> fechou o entity manager!");
		}
	}
}
