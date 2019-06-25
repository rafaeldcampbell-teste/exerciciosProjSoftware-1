import java.text.ParseException;

import corejava.Console;
import excecao.ObjetoNaoEncontradoException;

public class Principal {

	public static void main(String[] args) throws ParseException, ObjetoNaoEncontradoException {
		
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
