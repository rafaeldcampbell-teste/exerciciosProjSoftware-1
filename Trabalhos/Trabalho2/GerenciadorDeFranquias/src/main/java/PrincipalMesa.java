import java.util.ArrayList;
import java.util.List;

import corejava.Console;
import excecao.ObjetoNaoEncontradoException;
import modelo.Funcionario;
import modelo.Loja;
import modelo.Mesa;
import servico.FuncionarioAppService;
import servico.LojaAppService;
import servico.MesaAppService;

public class PrincipalMesa {

	public static void main(String[] args) {
		int id;
		int id_loja;
		int codigo_funcionario;
		int numero;
		Funcionario funcionario;
		Loja loja;
		Mesa mesa;
		List<Mesa> mesas = new ArrayList<Mesa>();
		
		MesaAppService mesaAppService = new MesaAppService();
		LojaAppService lojaAppService = new LojaAppService();
		FuncionarioAppService funcionarioAppService = new FuncionarioAppService();
		
		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
		    System.out.println('\n' + "1. Cadastrar uma mesa de uma loja");
		    System.out.println("2. Remover uma mesa de uma loja");
		    System.out.println("3. Listar todas as mesas de uma loja");
		    System.out.println("4. Listar todas as mesas de um funcionario");
		    System.out.println("5. Voltar");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");
		    
		    switch (opcao) {
		    	case 1:{
		    		id_loja = Console.readInt('\n' + "Digite o id da loja onde deseja cadastrar a mesa: ");
		    		try {
		    			loja = lojaAppService.recuperaUmaLoja(id_loja);
		    		} catch (ObjetoNaoEncontradoException e) {
		    			break;
		    		}
		    		
		    		codigo_funcionario = Console.readInt('\n' + "Digite o código do funcionario que irá atender a mesa: ");
		    		try {
		    			funcionario = funcionarioAppService.recuperaUmFuncionario(codigo_funcionario);
		    		} catch (Exception e) {
		    			break;
		    		}
		    		
		    		numero = Console.readInt('\n' + "Digite o número da mesa: ");
		    		
		    		mesa = new Mesa(loja, funcionario, numero);
		    		
		    		id = mesaAppService.inclui(mesa);
		    		
		    		System.out.println("Mesa "+id+" cadastrada com sucesso!");
		    	}
		    	
		    	case 2:{
		    		id = Console.readInt('\n' + "Digite o número da mesa que deseja remover: ");
		    		
					try {
					    mesa = mesaAppService.recuperaUmaMesa(id);
					} catch (ObjetoNaoEncontradoException e) {
					    break;
					}
	
					System.out.println('\n' + "Id = " + mesa.getId() 
											+ "\nLoja = " + mesa.getLoja().getId()
											+ "\nFuncionario = "+ mesa.getFuncionario().getCodigo());
	
					String resp = Console.readLine('\n' + "Confirma a remoção da mesa?");
	
					if (resp.equals("s")) {
					    try {
						mesaAppService.exclui(mesa.getId());
						System.out.println('\n' + "Mesa removida com sucesso!");
					    } catch (Exception e) {
					    	break;
					    }
					} else {
					    System.out.println('\n' + "Mesa não removida.");
					}
					break;
		    	}
		    	
		    	case 3: {
		    		id_loja = Console.readInt('\n' + "Digite o id da loja cujas mesas serão listadas: ");
		    		
		    		try{
		    			mesas = mesaAppService.recuperaMesasPorLoja(id_loja);
		    		} catch (Exception e){
		    			break;
		    		}
		    		for(Mesa m : mesas) {
		    			System.out.println('\n' + "Id = " + m.getId() 
										   	   	+ "\nLoja = " + m.getLoja().getId()
										   	   	+ "\nFuncionario = "+ m.getFuncionario().getCodigo());
		    		}
		    		mesas.clear();
		    		break;
		    	}
		    	case 4: {
		    		codigo_funcionario = Console.readInt('\n' + "Digite o id do funcionario cujas mesas serão listadas: ");
		    		
		    		try{
		    			mesas = mesaAppService.recuperaMesasPorFuncionario(codigo_funcionario);
		    		} catch (Exception e){
		    			break;
		    		}
		    		for(Mesa m : mesas) {
		    			System.out.println('\n' + "Id = " + m.getId() 
										   	   	+ "\nLoja = " + m.getLoja().getId()
										   	   	+ "\nFuncionario = "+ m.getFuncionario().getCodigo());
		    		}
		    		mesas.clear();
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
