package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;

public interface LojaDAO {

	int inclui(Lojas loja);
	
	void exclui(int id) throws ObjetoNaoEncontradoException;
	
	Lojas recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException;
	
	List<Lojas> recuperaLojas();
}
