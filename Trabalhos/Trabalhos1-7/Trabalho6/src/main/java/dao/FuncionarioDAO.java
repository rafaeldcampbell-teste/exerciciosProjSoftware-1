package dao;

import java.util.List;

import anotacao.RecuperaLista;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;

public interface FuncionarioDAO extends DaoGenerico<Funcionarios, Long> {
	
	@RecuperaLista
	List<Funcionarios> recuperaFuncionarios(Lojas loja) throws ObjetoNaoEncontradoException;

}