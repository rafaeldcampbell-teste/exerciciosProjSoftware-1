package dao;

import java.util.List;

import anotacao.RecuperaLista;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import modelo.Mesas;

public interface MesaDAO extends DaoGenerico<Mesas, Long> {

	@RecuperaLista
	List<Mesas> recuperaMesasPorLoja(Lojas loja) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Mesas> recuperaMesasPorFuncionario(Funcionarios funcionario) throws ObjetoNaoEncontradoException;
}