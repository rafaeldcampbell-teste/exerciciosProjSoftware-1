package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.AtendimentoDAO;
import dao.MesaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;

public class AtendimentoAppService {
	@Autowired
	private AtendimentoDAO atendimentoDAO;
	@Autowired
	private MesaDAO mesaDAO;
	
	@Perfil(nomes= {"dba"})
	@Transactional
	public int inclui(Atendimentos atendimento) {
		return atendimentoDAO.inclui(atendimento);
	} 
	
	@Perfil(nomes= {"dba"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		atendimentoDAO.exclui(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Atendimentos recuperaAtendimento(int id) throws ObjetoNaoEncontradoException {
		return atendimentoDAO.recuperaAtendimento(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException {
		atendimentoDAO.atualizaValor(id, valor);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Atendimentos> recuperaAtendimentos(int id) throws ObjetoNaoEncontradoException{
		Mesas mesa = mesaDAO.recuperaUmaMesa(id);
		List<Atendimentos> atendimentos = atendimentoDAO.recuperaAtendimentos(mesa);
		if(atendimentos.isEmpty()) {
			System.out.println("======> Nenhum atendimento encontrado para esta mesa!");
		}
		return atendimentos;
	}
}
