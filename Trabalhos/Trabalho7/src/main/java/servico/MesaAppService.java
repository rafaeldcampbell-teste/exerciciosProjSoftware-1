package servico;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import anotacao.Perfil;
import dao.FuncionarioDAO;
import dao.LojaDAO;
import dao.MesaDAO;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import modelo.Mesas;

public class MesaAppService {

	private MesaDAO mesaDAO = null;
	private FuncionarioDAO funcionarioDAO = null;
	private LojaDAO lojaDAO = null;
	
	public void setMesaDAO(MesaDAO mesaDAO) {
		this.mesaDAO = mesaDAO;
	}

	public void setFuncionarioDAO(FuncionarioDAO funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}

	public void setLojaDAO(LojaDAO lojaDAO) {
		this.lojaDAO = lojaDAO;
	}

	
	@Perfil(nomes= {"dba"})
	@Transactional
	public Mesas inclui(Mesas umaMesa) {
		Mesas mesa = mesaDAO.inclui(umaMesa);
		return mesa;
	}
	
	@Perfil(nomes= {"dba"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public void exclui(Long id) throws ObjetoNaoEncontradoException {
		Mesas mesa = mesaDAO.getPorId(id);
		mesaDAO.exclui(mesa);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public Mesas recuperaUmaMesa(Long id) throws ObjetoNaoEncontradoException {
		return mesaDAO.getPorIdComLock(id);
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Mesas> recuperaMesasPorLoja(Long id) throws ObjetoNaoEncontradoException{
		Lojas loja = lojaDAO.getPorIdComLock(id);
		List<Mesas> mesas = mesaDAO.recuperaMesasPorLoja(loja);
		return mesas;
	}
	
	@Perfil(nomes= {"adm"})
	@Transactional(rollbackFor={ObjetoNaoEncontradoException.class})
	public List<Mesas> recuperaMesasPorFuncionario(Long codigo) throws ObjetoNaoEncontradoException {
		Funcionarios funcionario = funcionarioDAO.getPorIdComLock(codigo);
		List<Mesas> mesas = mesaDAO.recuperaMesasPorFuncionario(funcionario);
		return mesas;
	}
}
