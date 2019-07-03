package dao;

import java.util.List;

import anotacao.RecuperaLista;
import modelo.Perfil;
import modelo.Usuario;

public interface UsuarioDAO extends DaoGenerico<Usuario, Long> {
	
	@RecuperaLista
	List<Usuario> recuperaListaUsuarios(String conta, String senha);
	@RecuperaLista
	List<Perfil> recuperaPerfis(String conta);
}
