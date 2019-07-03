package dao.impl;

import dao.FuncionarioDAO;
import modelo.Funcionarios;

public abstract class FuncionarioDAOImpl  extends JPADaoGenerico<Funcionarios, Long> implements FuncionarioDAO {
	public FuncionarioDAOImpl() {
		super(Funcionarios.class);
	}
}
