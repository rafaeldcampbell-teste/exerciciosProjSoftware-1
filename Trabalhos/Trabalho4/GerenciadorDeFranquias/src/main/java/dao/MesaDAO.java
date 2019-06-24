package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import modelo.Mesa;

public interface MesaDAO {

	int inclui(Mesa mesa);
	
	void exclui(int id) throws ObjetoNaoEncontradoException;
	
	Mesa recuperaUmaMesa(int id) throws ObjetoNaoEncontradoException;
	
	List<Mesa> recuperaMesasPorLoja(Loja loja) throws ObjetoNaoEncontradoException;

	List<Mesa> recuperaMesasPorFuncionario(Funcionario funcionario) throws ObjetoNaoEncontradoException;
}