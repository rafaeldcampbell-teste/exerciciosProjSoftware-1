package servico;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.LojaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;


public class LojaAppService {

	private LojaDAO lojaDAO = null;
	
	public void setLojaDAO(LojaDAO lojaDAO) {
		this.lojaDAO = lojaDAO;
	}

	@Perfil(nomes= {"dba"})
	@Transactional
	public Lojas inclui(Lojas umaLoja) {
		Lojas loja = lojaDAO.inclui(umaLoja);
		return loja;
	}
	
	@Perfil(nomes= {"dba"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(Long id) throws ObjetoNaoEncontradoException {
		Lojas loja = lojaDAO.getPorId(id);
		lojaDAO.exclui(loja);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Lojas recuperaUmaLoja(Long id) throws ObjetoNaoEncontradoException {
		return lojaDAO.getPorIdComLock(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional
	public List<Lojas> recuperaLojas() {
		List<Lojas> lojas = lojaDAO.recuperaLojas();
		return lojas;
	}
}
