package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import excecao.InfraestruturaException;

public class JPAUtil {
    private static EntityManagerFactory emf;
    private static ThreadLocal<EntityManager> entityManager = new ThreadLocal<EntityManager>();
    private static ThreadLocal<EntityTransaction> transaction = new ThreadLocal<EntityTransaction>();
    private static ThreadLocal<Integer> transactionCounter = new ThreadLocal<Integer>();;

    static {
	try {
	    emf = Persistence.createEntityManagerFactory("exercicio");
	} catch (Throwable e) {
	    e.printStackTrace();
	    System.out.println(">>>>>>>>>> Mensagem de erro: " + e.getMessage());
	    throw e;
	}
    }

    public static void beginTransaction() { // System.out.println("Vai criar transacao");
		
    	if(entityManager.get() == null) {
			getEntityManager();
		}
		transactionCounter.set(transactionCounter.get() + 1);
		if(transactionCounter.get() == 1) {
			try {
			    if (transaction.get() == null) {
			    	transaction.set(entityManager.get().getTransaction()); //garantindo que a variável tem uma transação
			    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Criar transação");
			    }
			    transaction.get().begin();
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Abrir transação");
			} catch (RuntimeException ex) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Erro ao abrir transação");
			    throw new InfraestruturaException(ex);
			}
		}else {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Transação já aberta");
		}
    }

    public static EntityManager getEntityManager() { // System.out.println("Abriu ou recuperou sessão");
		// Abre uma nova Sessão, se for a primeira execução
		try {
		    if (entityManager.get() == null) {
		    	entityManager.set(emf.createEntityManager());
		    	transactionCounter.set(0);
		    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Criar entity manager");
		    }
		} catch (RuntimeException ex) {
		    throw new InfraestruturaException(ex);
		}
		return entityManager.get();
    }

    public static void commitTransaction() {
		if(transactionCounter.get() == 1) {
			try {
			    if (transaction.get() != null && transaction.get().isActive()) {
			    	transaction.get().commit();
			    	transaction.set(null);
			    	transactionCounter.set(transactionCounter.get() - 1);
			    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Comitada transacao");
			    }
			} catch (RuntimeException ex) {
			    try {
			    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Erro ao fechar transação, executando rollback");
			    	rollbackTransaction();
			    } catch (RuntimeException e) {
			    }
		
			    throw new InfraestruturaException(ex);
			}
		}else {
			transactionCounter.set(transactionCounter.get() - 1);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Aguardando outras chamadas pela transação");
		}
    }

    public static void rollbackTransaction() {
		System.out.println("Efetuando rollback de transacao");
		try {
		    if (transaction.get() != null && transaction.get().isActive()) {
		    	transaction.get().rollback();
		    	transaction.set(null);
		    	transactionCounter.set(null);
		    }
		} catch (RuntimeException ex) {
		    throw new InfraestruturaException(ex);
		} finally {
		    closeEntityManager();
		}
    }

    public static void closeEntityManager() { // System.out.println("Vai fechar sessão");
		if(transactionCounter.get() == 0) {
			try {
			    if (entityManager.get() != null && entityManager.get().isOpen()) {
			    	entityManager.get().close();
			    	entityManager.set(null);
			    	transactionCounter.set(0);
			    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Fechar entity manager");
			    }
			    
			    if (transaction.get() != null && transaction.get().isActive()) {
					rollbackTransaction();
					throw new RuntimeException("EntityManager sendo fechado com transação ativa.");
			    }
			} catch (RuntimeException ex) {
			    throw new InfraestruturaException(ex);
			}
	    }else {

			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Aguardando o fim de todas as execuções");
	    }
    }
}
