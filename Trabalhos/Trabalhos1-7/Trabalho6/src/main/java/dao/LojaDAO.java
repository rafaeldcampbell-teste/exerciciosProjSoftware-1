package dao;

import java.util.List;

import anotacao.RecuperaLista;
import modelo.Lojas;

public interface LojaDAO extends DaoGenerico<Lojas, Long> {

	@RecuperaLista
	List<Lojas> recuperaLojas();
}
