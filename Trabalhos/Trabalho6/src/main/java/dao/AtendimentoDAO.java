package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;

public interface AtendimentoDAO extends DaoGenerico<Atendimentos, Long> {
	@RecuperaObjeto
	Atendimentos recuperaAtendimento(int id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Atendimentos> recuperaAtendimentos(Mesas mesa);
}