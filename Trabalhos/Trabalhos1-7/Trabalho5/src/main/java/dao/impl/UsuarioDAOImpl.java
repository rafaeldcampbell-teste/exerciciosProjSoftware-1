package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import dao.UsuarioDAO;
import modelo.Perfil;
import modelo.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext
    protected EntityManager em;

	@Override
	public List<Usuario> recuperaListaUsuarios(String conta, String senha) {
		String busca = "select u from Usuario u " + 
	               "where u.conta = :conta and u.senha = :senha";
	
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = em.createQuery(busca).setParameter("conta", conta).setParameter("senha", senha).getResultList();
	
		return usuarios;
	}

	@Override
	public List<Perfil> recuperaPerfis(String conta){
		
		String busca = "select p from Perfil p " + 
	               "where p.usuario.conta = :conta " + 
			       "order by p.perfil";
	
		@SuppressWarnings("unchecked")
		List<Perfil> perfis = em.createQuery(busca).setParameter("conta", conta).getResultList();
	
		return perfis;
	}
	
	
}
