 package servico.controle;

import java.lang.reflect.Field;
import java.util.Set;

import org.reflections.Reflections;

import anotacao.Autowired;
import dao.controle.FabricaDeDAOs;
import net.sf.cglib.proxy.Enhancer;

public class FabricaDeServico {
//    private static ResourceBundle prop;
//
//    static {
//	try {
//	    prop = ResourceBundle.getBundle("servico");
//	}
//	catch (MissingResourceException e) {
//	    System.out.println("Aquivo servico.properties não encontrado.");
//	    throw new RuntimeException(e);
//	}
//    }

    // Esse método pode ser executado de 2 formas:
    // 1. produtoAppService =
    // FabricaDeServico.<ProdutoAppService>getServico(ProdutoAppService.class);
    // 2. produtoAppService = FabricaDeServico.getServico(ProdutoAppService.class);

    @SuppressWarnings("unchecked")
    public static <T> T getServico(Class<T> tipo) {

	Reflections reflections = new Reflections("servico.impl");
	
	Set<Class<? extends T>> classes = reflections.getSubTypesOf(tipo);
	
	if (classes.size() > 1) {
	    throw new RuntimeException
	       ("Não pode existir mais de uma classe implementado "
	      + "a inteface " + tipo.getName());
	}
	
	Class<?> classe = classes.iterator().next();
	    
	T proxy = (T)Enhancer.create(classe, new InterceptadorDeServico());

	Field[] campos = classe.getDeclaredFields();
	for (Field campo : campos) {
	    if (campo.isAnnotationPresent(Autowired.class)) {
		campo.setAccessible(true);
		try {
		    campo.set(proxy, FabricaDeDAOs.getDAO(campo.getType()));
		}
		catch (IllegalArgumentException | IllegalAccessException e) {
		    throw new RuntimeException(e);
		}
	    }
	}
	    
	return proxy;
    }
}