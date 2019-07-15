package dao;

import java.util.List;

import modelo.Perfil;
import modelo.Usuario;

public interface UsuarioDAO {

	List<Usuario> recuperaListaUsuarios(String conta, String senha);
	
	List<Perfil> recuperaPerfis(String conta);
}
