package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.FuncionarioDAO;
import dao.LojaDAO;
import dao.MesaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import modelo.Mesas;

@Service
public class MesaAppService {

	@Autowired
	private MesaDAO mesaDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private LojaDAO lojaDAO;
	
	@Transactional
	public int inclui(Mesas mesa) {
		return mesaDAO.inclui(mesa);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(int id) throws ObjetoNaoEncontradoException {
		mesaDAO.exclui(id);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Mesas recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException {
		return mesaDAO.recuperaUmaMesa(id);
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Mesas> recuperaMesasPorLoja(int id) throws ObjetoNaoEncontradoException{
		Lojas loja = lojaDAO.recuperaUmaLoja(id);
		List<Mesas> mesas = mesaDAO.recuperaMesasPorLoja(loja);
		
		if(mesas.isEmpty()) {
			System.out.println("======> Nenhuma mesa encontrada para esta loja!");
		}
		return mesas;
	}
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Mesas> recuperaMesasPorFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		Funcionarios funcionario = funcionarioDAO.recuperaUmFuncionario(codigo);
		List<Mesas> mesas = mesaDAO.recuperaMesasPorFuncionario(funcionario);
		
		if(mesas.isEmpty()) {
			System.out.println("======> Nenhuma mesa encontrada para este funcionario!");
		}
		return mesas;
	}
}
