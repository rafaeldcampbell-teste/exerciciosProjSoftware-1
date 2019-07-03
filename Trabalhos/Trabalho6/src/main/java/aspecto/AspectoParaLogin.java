package aspecto;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import anotacao.Perfil;
import excecao.PermissaoNegadaException;
import util.SingletonPerfis;

@Aspect
public class AspectoParaLogin {
	
	@Around("execution(* *(..))")
	public Object doIntercept(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		if(methodSignature.getMethod().isAnnotationPresent(Perfil.class)){
			String[] perfisAceitosMetodo = methodSignature.getMethod().getAnnotation(Perfil.class).nomes();
			SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
			String[] perfisUsuario = singletonPerfis.getPerfis();
			for(String sm : perfisAceitosMetodo) {
				for(String su : perfisUsuario) {
					if(sm.contentEquals(su)) {
						return proceedingJoinPoint.proceed();
					}
				}
			}
			throw new PermissaoNegadaException();
		}
		return proceedingJoinPoint.proceed();
    }
}
