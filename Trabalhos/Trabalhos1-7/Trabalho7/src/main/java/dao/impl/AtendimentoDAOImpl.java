package dao.impl;

import dao.AtendimentoDAO;
import modelo.Atendimentos;


public abstract class AtendimentoDAOImpl extends JPADaoGenerico<Atendimentos, Long> implements AtendimentoDAO {
	public AtendimentoDAOImpl() {
		super(Atendimentos.class);
	}
}
