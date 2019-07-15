package aspecto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.reflections.Reflections;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import anotacao.ConstraintViolada;
import excecao.LojaComEndereçoSemRua;
import excecao.ValorMinimoException;
import excecao.ViolacaoDeConstraintDesconhecidaException;

@Aspect
public class AspectoAround {
    private static Map<String, Class<?>> map = new HashMap<String, Class<?>>();
    private static List<String> listaDeNomesDeConstraints;

    static {
	Reflections reflections = new Reflections("excecao");

	Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ConstraintViolada.class);
	for (Class<?> classe : classes) {
	    map.put(classe.getAnnotation(ConstraintViolada.class).nome(), classe);
	}
	
	listaDeNomesDeConstraints = new ArrayList<String>(map.keySet());
    }

    @Pointcut("call(* servico.*.*(..))")
    public void traduzExcecaoAround() {
    }

    @Around("traduzExcecaoAround()")
    public Object traduzExcecaoAround(ProceedingJoinPoint joinPoint) throws Throwable {
	try {
	    return joinPoint.proceed();
	}
	catch (DataAccessException e) {
	    Throwable t = e;

	    if (e instanceof DataIntegrityViolationException) {
			t = t.getCause();
			while (t != null && !(t instanceof SQLException)) {
			    t = t.getCause();
			}
	
			String msg = (t.getMessage() != null) ? t.getMessage() : "";
	
			for (String constraint : listaDeNomesDeConstraints) {
	
			    if (msg.indexOf(constraint) > 0) {
				throw ((Exception)map.get(constraint).newInstance());
			    }
			}
			throw new ViolacaoDeConstraintDesconhecidaException("Alguma constraint de banco de dados foi violada - msg: " + msg);
		    }
	    else {
	    	throw e;
	    }
	}
	catch (ValorMinimoException e) {
		System.out.println("===> O valor mínimo do atendimento é de R$10,00");
		throw e;
	}
	catch (LojaComEndereçoSemRua e) {
		System.out.println("===> O endereço deve incluir uma rua");
		throw e;
	}
    }
}