package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionarios;
import modelo.Lojas;
import modelo.Mesas;

public interface MesaDAO {

	int inclui(Mesas mesa);
	
	void exclui(int id) throws ObjetoNaoEncontradoException;
	
	Mesas recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException;
	
	List<Mesas> recuperaMesasPorLoja(Lojas loja) throws ObjetoNaoEncontradoException;

	List<Mesas> recuperaMesasPorFuncionario(Funcionarios funcionario) throws ObjetoNaoEncontradoException;
}