package servico;

import java.util.List;

import dao.AtendimentoDAO;
import dao.MesaDAO;
import excecao.InfraestruturaException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimento;
import modelo.Mesa;
import util.FabricaDeDAOs;
import util.JPAUtil;

public class AtendimentoAppService {
	private static AtendimentoDAO atendimentoDAO = FabricaDeDAOs.getDAO(AtendimentoDAO.class);
	private static MesaDAO mesaDAO = FabricaDeDAOs.getDAO(MesaDAO.class);
	
	public int inclui(Atendimento atendimento) {
		try{
			JPAUtil.beginTransaction();
			int id = atendimentoDAO.inclui(atendimento);
			JPAUtil.commitTransaction();
			return id;
			
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao inserir atendimento!");
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
		    atendimentoDAO.exclui(id);
		    JPAUtil.commitTransaction();
		} catch (ObjetoNaoEncontradoException e) {
		    System.out.println("======> Atendimento não encontrado!");
		    System.out.println("======> Executando rollback da transação!");
		    JPAUtil.rollbackTransaction();
		    throw e;
		} catch (InfraestruturaException e) {
			System.out.println("======> Erro ao excluir atendimento!");
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
	
	public Atendimento recuperaAtendimento(int id) throws ObjetoNaoEncontradoException {
		try{
			JPAUtil.beginTransaction();
			Atendimento atendimento = atendimentoDAO.recuperaAtendimento(id);
			JPAUtil.commitTransaction();
			return atendimento;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar atendimento!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("======> Atendimento não encontrado!");
			throw e;
		
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
	
	public void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException {
		try{
			JPAUtil.beginTransaction();
			atendimentoDAO.atualizaValor(id, valor);
			JPAUtil.commitTransaction();
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao atualizar atendimento!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		}  catch (ObjetoNaoEncontradoException e) {
			System.out.println("======> Atendimento não encontrado!");
			throw e;
		
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
	
	
	public List<Atendimento> recuperaAtendimentos(int id) throws ObjetoNaoEncontradoException{
		try{
			JPAUtil.beginTransaction();
			Mesa mesa = mesaDAO.recuperaUmaMesa(id);
			List<Atendimento> atendimentos = atendimentoDAO.recuperaAtendimentos(mesa);
			JPAUtil.commitTransaction();
			
			if(atendimentos.isEmpty()) {
				System.out.println("======> Nenhum atendimento encontrado para esta mesa!");
			}
			return atendimentos;
		}catch (InfraestruturaException e) {
		    System.out.println("======> Erro ao recuperar atendimentos!");
		    try {
			    System.out.println("======> Executando rollback da transação!");
		    	JPAUtil.rollbackTransaction();
		    } catch (InfraestruturaException ie) {
		    	throw ie;
		    }
		    throw e;
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("======> Mesa não encontrada!");
			throw e;
		} finally {
		    JPAUtil.closeEntityManager();
		}
	}
}
