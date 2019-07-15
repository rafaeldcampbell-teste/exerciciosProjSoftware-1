package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.LojaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;


public class LojaAppService {

	@Autowired
	private LojaDAO lojaDAO;
	
	@Perfil(nomes= {"dba"})
	@Transactional
	public int inclui(Lojas loja) {
		return lojaDAO.inclui(loja);
	}
	
	@Perfil(nomes= {"dba"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		lojaDAO.exclui(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Lojas recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException {
		return lojaDAO.recuperaUmaLoja(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional
	public List<Lojas> recuperaLojas() {
		List<Lojas> lojas = lojaDAO.recuperaLojas();
		if(lojas.isEmpty()) {
			System.out.println("======> Nenhuma loja encontrada!");
		}
		return lojas;
	}
}
