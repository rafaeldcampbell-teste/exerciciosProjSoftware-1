package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;

public interface AtendimentoDAO {

	int inclui(Atendimentos atendimento);
	
	void exclui(int id) throws ObjetoNaoEncontradoException;
	
	Atendimentos recuperaAtendimento(int id) throws ObjetoNaoEncontradoException;
	
	void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException;
	
	List<Atendimentos> recuperaAtendimentos(Mesas mesa);
}