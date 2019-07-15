package servico;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.AtendimentoDAO;
import dao.MesaDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.ValorMinimoException;
import modelo.Atendimentos;
import modelo.Mesas;

public class AtendimentoAppService {

	private AtendimentoDAO atendimentoDAO = null;

	private MesaDAO mesaDAO = null;
	
	
	public void setAtendimentoDAO(AtendimentoDAO atendimentoDAO) {
		this.atendimentoDAO = atendimentoDAO;
	}

	public void setMesaDAO(MesaDAO mesaDAO) {
		this.mesaDAO = mesaDAO;
	}

	@Perfil(nomes= {"dba"})
	@Transactional
	public Atendimentos inclui(Atendimentos umAtendimento) {
		if(umAtendimento.getValorTotalConta()<10.0f) {
			throw new ValorMinimoException("Abaixo do valor minimo");
		}
		Atendimentos atendimento = atendimentoDAO.inclui(umAtendimento);
		return atendimento;
	} 
	
	@Perfil(nomes= {"dba"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(Long id) throws ObjetoNaoEncontradoException {
		Atendimentos umAtendimento = atendimentoDAO.getPorId(id);
		atendimentoDAO.exclui(umAtendimento);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Atendimentos recuperaAtendimento(Long id) throws ObjetoNaoEncontradoException {
		return atendimentoDAO.getPorIdComLock(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Atendimentos> recuperaAtendimentos(Long id) throws ObjetoNaoEncontradoException{
		Mesas mesa = mesaDAO.getPorId(id);
		List<Atendimentos> atendimentos = atendimentoDAO.recuperaAtendimentos(mesa);
		return atendimentos;
	}
}
