package servico;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.FuncionarioDAO;
import dao.LojaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;

public class FuncionarioAppService {


	private FuncionarioDAO funcionarioDAO = null;

	private LojaDAO lojaDAO = null;
	
	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	public void setLojaDAO(LojaDAO lojaDAO) {
		this.lojaDAO = lojaDAO;
	}

	@Perfil(nomes= {"dba"})
	@Transactional
	public Funcionarios inclui(Funcionarios umFuncionario) {
		Funcionarios funcionario = funcionarioDAO.inclui(umFuncionario);
		return funcionario;
	}
	
	@Perfil(nomes= {"dba"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(Long codigo) throws ObjetoNaoEncontradoException {
		Funcionarios funcionario = funcionarioDAO.getPorId(codigo);
		funcionarioDAO.exclui(funcionario);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Funcionarios recuperaUmFuncionario(Long codigo) throws ObjetoNaoEncontradoException {
		return funcionarioDAO.getPorIdComLock(codigo);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Funcionarios> recuperaFuncionarios(Long id) throws ObjetoNaoEncontradoException{
		Lojas loja = lojaDAO.getPorId(id);
		List<Funcionarios> funcionarios = funcionarioDAO.recuperaFuncionarios(loja);
		return funcionarios;
	}
}
