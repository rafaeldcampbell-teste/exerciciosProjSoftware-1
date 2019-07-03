import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ObjetoNaoEncontradoException;
import login.LoginService;
import util.SingletonPerfis;

public class Principal {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ParseException, ObjetoNaoEncontradoException {
		
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		LoginService login = (LoginService)fabrica.getBean ("loginService");
		String conta, senha;
		System.out.println("Faça o login para continuar!");
		boolean resp;
		do {
			conta = Console.readLine("\nDigite sua conta: ");
			senha = Console.readLine("\nDigite sua senha: ");
			resp = login.validaUsuario(conta, senha);
			if(!resp) System.out.println("\nUsuário Invalido!");
		}while(!resp);
		
		SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		singletonPerfis.setPerfis(login.getPerfis().toArray(new String[login.getPerfis().size()]));
		
		if(resp) {
			boolean continua = true;
			while (continua) {
				System.out.println('\n' + "Qual elemento você deseja operar?");
			    System.out.println('\n' + "1. Lojas");
			    System.out.println("2. Funcionarios");
			    System.out.println("3. Mesas");
			    System.out.println("4. Atendimentos");
			    System.out.println("5. Sair");
	
			    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");
			    
			    switch (opcao) {
				    case 1:{
				    	PrincipalLoja.main(args);
				    	break;
				    }
				    case 2:{
				    	PrincipalFuncionario.main(args);
				    	break;
				    }
				    case 3:{
				    	PrincipalMesa.main(args);
				    	break;
				    }
				    case 4:{
				    	PrincipalAtendimento.main(args);
				    	break;
				    }
				    case 5:{
				    	continua = false;
				    	break;
				    }
				    default:{
				    	System.out.println("=========> Opção inválida");
				    }
				    
			    }
			}
		}
	}
}
