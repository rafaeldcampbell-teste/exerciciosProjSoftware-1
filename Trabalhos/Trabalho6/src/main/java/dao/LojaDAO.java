package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;

public interface LojaDAO extends DaoGenerico<Lojas, Long> {
	@RecuperaObjeto
	Lojas recuperaUmaLoja(int id) throws ObjetoNaoEncontradoException;
	@RecuperaLista
	List<Lojas> recuperaLojas();
}
