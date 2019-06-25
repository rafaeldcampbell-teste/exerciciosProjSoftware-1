package servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.FuncionarioDAO;
import dao.LojaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;

@Service
public class FuncionarioAppService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private LojaDAO lojaDAO;
	
	@Transactional
	public int inclui(Funcionarios funcionario) {
		return funcionarioDAO.inclui(funcionario);
	}
	

	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(int codigo) throws ObjetoNaoEncontradoException {
		funcionarioDAO.exclui(codigo);
	}
	
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Funcionarios recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException {
		return funcionarioDAO.recuperaUmFuncionario(codigo);
	}
	
	
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Funcionarios> recuperaFuncionarios(int id) throws ObjetoNaoEncontradoException{
		Lojas loja = lojaDAO.recuperaUmaLoja(id);
		List<Funcionarios> funcionarios = funcionarioDAO.recuperaFuncionarios(loja);
		
		if(funcionarios.isEmpty()) {
			System.out.println("======> Nenhum funcionario encontrado para esta loja!");
		}
		return funcionarios;
	}
}
