package dao;



import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;

public interface FuncionarioDAO extends DaoGenerico<Funcionarios, Long> {
	@RecuperaObjeto
	Funcionarios recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Funcionarios> recuperaFuncionarios(Lojas loja) throws ObjetoNaoEncontradoException;

}