package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.LojaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;

@Service
public class LojaAppService {

	@Autowired
	private LojaDAO lojaDAO;
	
	@Transactional
	public int inclui(Lojas loja) {
		return lojaDAO.inclui(loja);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		lojaDAO.exclui(id);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Lojas recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException {
		return lojaDAO.recuperaUmaLoja(id);
	}
	
	@Transactional
	public List<Lojas> recuperaLojas() {
		List<Lojas> lojas = lojaDAO.recuperaLojas();
		if(lojas.isEmpty()) {
			System.out.println("======> Nenhuma loja encontrada!");
		}
		return lojas;
	}
}
