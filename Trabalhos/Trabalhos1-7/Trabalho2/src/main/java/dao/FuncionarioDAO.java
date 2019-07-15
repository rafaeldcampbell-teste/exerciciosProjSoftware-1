package dao;



import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;

public interface FuncionarioDAO {
	
	int inclui(Funcionarios funcionario);
	
	void exclui(int codigo) throws ObjetoNaoEncontradoException;
	
	Funcionarios recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException;
	
	List<Funcionarios> recuperaFuncionarios(Lojas loja) throws ObjetoNaoEncontradoException;

}