package dao;

import java.util.List;

import excecao.ObjetoNaoEncontradoException;
import modelo.Mesa;
import modelo.Atendimento;

public interface AtendimentoDAO {

	int inclui(Atendimento atendimento);
	
	void exclui(int id) throws ObjetoNaoEncontradoException;
	
	Atendimento recuperaAtendimento(int id) throws ObjetoNaoEncontradoException;
	
	void atualizaValor(int id, float valor) throws ObjetoNaoEncontradoException;
	
	List<Atendimento> recuperaAtendimentos(Mesa mesa);
}