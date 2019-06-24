package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;

public interface FuncionarioDAO {
	
	int inclui(Funcionario funcionario);
	
	void exclui(int codigo) throws ObjetoNaoEncontradoException;
	
	Funcionario recuperaUmFuncionario(int codigo) throws ObjetoNaoEncontradoException;
	
	List<Funcionario> recuperaFuncionarios(Loja loja) throws ObjetoNaoEncontradoException;

}