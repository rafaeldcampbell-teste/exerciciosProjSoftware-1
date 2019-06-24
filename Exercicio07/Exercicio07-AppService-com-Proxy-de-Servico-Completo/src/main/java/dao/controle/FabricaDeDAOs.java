package dao.controle;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDAOs {
    private static ResourceBundle prop;

    static {
	try {
	    prop = ResourceBundle.getBundle("dao");
	}
	catch (MissingResourceException e) {
	    System.out.println("Aquivo dao.properties não encontrado.");
	    throw new RuntimeException(e);
	}
    }

    @SuppressWarnings("unchecked")
    public static <T> T getDAO(Class<T> tipo) {
	Class<?> daoClass = null;
	String nomeDaClasse = null;
	T proxy = null;

	try {
	    nomeDaClasse = prop.getString(tipo.getSimpleName());
	    daoClass = Class.forName(nomeDaClasse);
	    proxy = (T)Enhancer.create(daoClass, new InterceptadorDeDAO());
	}
	catch (ClassNotFoundException e) {
	    System.out.println("Classe " + nomeDaClasse + " não encontrada");
	    throw new RuntimeException(e);
	}
	catch (MissingResourceException e) {
	    System.out.println("Chave " + tipo + " não encontrada em dao.properties");
	    throw new RuntimeException(e);
	}

	return proxy;
    }
}
