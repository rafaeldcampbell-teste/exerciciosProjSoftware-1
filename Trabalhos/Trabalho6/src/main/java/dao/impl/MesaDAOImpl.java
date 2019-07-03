package dao.impl;

import dao.MesaDAO;
import modelo.Mesas;

public abstract class MesaDAOImpl extends JPADaoGenerico<Mesas, Long> implements MesaDAO {
	public MesaDAOImpl() {
		super(Mesas.class);
	}

}
