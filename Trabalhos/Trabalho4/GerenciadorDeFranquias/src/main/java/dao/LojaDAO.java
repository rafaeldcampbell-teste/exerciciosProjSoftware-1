package dao;


import modelo.Loja;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;


public interface LojaDAO {

	int inclui(Loja loja);
	
	void exclui(int id) throws ObjetoNaoEncontradoException;
	
	Loja recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException;
	
	List<Loja> recuperaLojas();
}
