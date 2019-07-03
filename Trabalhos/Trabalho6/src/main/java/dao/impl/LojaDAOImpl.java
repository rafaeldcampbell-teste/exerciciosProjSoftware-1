package dao.impl;

import dao.LojaDAO;
import modelo.Lojas;

public abstract class LojaDAOImpl extends JPADaoGenerico<Lojas, Long> implements LojaDAO {
	public LojaDAOImpl() {
		super(Lojas.class);
	}
}
