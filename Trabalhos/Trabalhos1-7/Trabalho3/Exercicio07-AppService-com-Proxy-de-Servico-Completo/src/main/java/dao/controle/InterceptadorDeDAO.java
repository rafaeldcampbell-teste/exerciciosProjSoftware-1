package dao.controle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import anotacao.PersistenceContext;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import servico.controle.JPAUtil;

public class InterceptadorDeDAO implements MethodInterceptor {

    public Object intercept(Object objeto, Method metodo, Object[] args, MethodProxy metodoDoProxy) throws Throwable {
   
		try {
	    	Field[] campos = objeto.getClass().getSuperclass().getDeclaredFields();
	    	System.out.println("campos "+campos.toString());
	    	for(Field campo : campos) {
	    		System.out.println("avaliando o campo: "+campo.toString());
	    		if(campo.isAnnotationPresent(PersistenceContext.class)) {
	    			System.out.println("===>>Setando acesso para "+campo.toString());
	    			campo.setAccessible(true);
	    			System.out.println("==>>Setando EntityManager para o campo "+campo.toString());
	    			campo.set(objeto, JPAUtil.getEntityManager());
	    		}
		    }
		    System.out.println("====>>Invocando o método "+metodo.getName());
		    Object obj = metodoDoProxy.invokeSuper(objeto, args);
		    return obj;
		}catch (IllegalArgumentException | IllegalAccessException e) {
		    throw new RuntimeException(e);
		}catch(Exception e) {
			System.out.println("mensagem do erro"+e.getMessage());
			throw new Exception(e);
		}
    }
}
