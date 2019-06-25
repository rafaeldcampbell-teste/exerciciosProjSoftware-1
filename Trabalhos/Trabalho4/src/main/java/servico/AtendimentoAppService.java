package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AtendimentoDAO;
import dao.MesaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;

@Service
public class AtendimentoAppService {
	@Autowired
	private AtendimentoDAO atendimentoDAO;
	@Autowired
	private MesaDAO mesaDAO;
	
	@Transactional
	public int inclui(Atendimentos atendimento) {
		return atendimentoDAO.inclui(atendimento);
	} 
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		atendimentoDAO.exclui(id);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Atendimentos recuperaAtendimento(int id) throws ObjetoNaoEncontradoException {
		return atendimentoDAO.recuperaAtendimento(id);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException {
		atendimentoDAO.atualizaValor(id, valor);
	}
	
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
