package dao;

import java.util.List;

import anotacao.RecuperaLista;
import modelo.Atendimentos;
import modelo.Mesas;

public interface AtendimentoDAO extends DaoGenerico<Atendimentos, Long> {

	@RecuperaLista
	List<Atendimentos> recuperaAtendimentos(Mesas mesa);
}