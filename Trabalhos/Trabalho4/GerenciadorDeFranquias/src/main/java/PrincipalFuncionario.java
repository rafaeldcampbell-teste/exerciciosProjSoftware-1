import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import servico.FuncionarioAppService;
import servico.LojaAppService;

public class PrincipalFuncionario {

	public static void main(String[] args) {
		int codigo;
		int id_loja;
		String nome;
		String funcao;
		Loja loja;
		Funcionario funcionario;
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");
		
		LojaAppService lojaAppService = (LojaAppService)fabrica.getBean ("lojaAppService");
		FuncionarioAppService funcionarioAppService = (FuncionarioAppService)fabrica.getBean ("funcionarioAppService");
		

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
		    System.out.println('\n' + "1. Cadastrar um funcionário em uma loja");
		    System.out.println("2. Remover um funcionário de uma loja");
		    System.out.println("3. Listar todas os funcionarios de uma loja");
		    System.out.println("4. Voltar");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");
		    
		    switch (opcao) {
			    case 1:{
		    		id_loja = Console.readInt('\n' + "Digite o id da loja onde deseja cadastrar o funcionario: ");
		    		try {
		    			loja = lojaAppService.recuperaUmaLoja(id_loja);
		    		} catch (ObjetoNaoEncontradoException e) {
		    			break;
		    		}
		    		nome = Console.readLine('\n' + "Digite o nome do funcionário: ");
		    		funcao = Console.readLine('\n' + "Digite a função do funcionário: ");
		    		
		    		funcionario = new Funcionario(nome, funcao, loja);
		    		
		    		codigo = funcionarioAppService.inclui(funcionario);
		    		
		    		System.out.println("Funcionario "+codigo+" cadastrada com sucesso!");
		    	}
		    	
		    	case 2:{
		    		codigo = Console.readInt('\n' + "Digite o codigo do funcionário que deseja remover: ");
		    		
					try {
					    funcionario = funcionarioAppService.recuperaUmFuncionario(codigo);
					} catch (ObjetoNaoEncontradoException e) {
					    break;
					}
	
					System.out.println('\n' + "Código = " + funcionario.getCodigo() 
											+ "\nNome = " + funcionario.getNome()
											+ "\nFunção = "+ funcionario.getFuncao()
											+ "\nLoja = " + funcionario.getLoja().getId());
	
					String resp = Console.readLine('\n' + "Confirma a remoção do funcionário?");
	
					if (resp.equals("s")) {
					    try {
						funcionarioAppService.exclui(funcionario.getCodigo());
						System.out.println('\n' + "Funcionario removido com sucesso!");
					    } catch (Exception e) {
					    	break;
					    }
					} else {
					    System.out.println('\n' + "Funcionario não removido.");
					}
					break;
		    	}
		    	
		    	case 3: {
		    		id_loja = Console.readInt('\n' + "Digite o id da loja cujas mesas serão listadas: ");
		    		
		    		try{
		    			funcionarios = funcionarioAppService.recuperaFuncionarios(id_loja);
		    		} catch (Exception e){
		    			break;
		    		}
		    		for(Funcionario f : funcionarios) {
		    			System.out.println('\n' + "Código = " + f.getCodigo() 
												+ "\nNome = " + f.getNome()
												+ "\nFunção = "+ f.getFuncao()
												+ "\nLoja = " + f.getLoja().getId());
		    		}
		    		funcionarios.clear();
		    		break;
		    	}
		    	case 4:{
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
