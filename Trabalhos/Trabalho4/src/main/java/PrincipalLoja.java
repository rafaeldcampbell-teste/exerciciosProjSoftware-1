import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ObjetoNaoEncontradoException;
import modelo.Lojas;
import servico.LojaAppService;

public class PrincipalLoja {

	public static void main(String[] args) {
		int id;
		String endereco;
		Lojas loja;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		LojaAppService lojaAppService = (LojaAppService)fabrica.getBean ("lojaAppService");
		
		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
		    System.out.println('\n' + "1. Cadastrar uma loja");
		    System.out.println("2. Remover uma loja");
		    System.out.println("3. Listar todas as lojas");
		    System.out.println("4. Voltar");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
		    
		    switch (opcao) {
			    case 1:{
			    	endereco = Console.readLine('\n' + "Informe o endereço da Loja: ");
			    	loja = new Lojas(endereco);
			    	id = lojaAppService.inclui(loja);
			    	System.out.println("Loja "+id+" cadastrada com sucesso!");
			    	break;
			    }
			    
			    
			    case 2:{
			    	id = Console.readInt('\n' + "Digite o número da loja que deseja remover: ");
	
					try {
					    loja = lojaAppService.recuperaUmaLoja(id);
					} catch (ObjetoNaoEncontradoException e) {
					    break;
					}
	
					System.out.println('\n' + "Id = " + loja.getId() + "\nEndereço = " + loja.getEndereco());
	
					String resp = Console.readLine('\n' + "Confirma a remoção da loja?");
	
					if (resp.equals("s")) {
					    try {
						lojaAppService.exclui(loja.getId());
						System.out.println('\n' + "Loja removida com sucesso!");
					    } catch (ObjetoNaoEncontradoException e) {
					    	break;
					    }
					} else {
					    System.out.println('\n' + "Produto não removido.");
					}
					break;
			    }
			    
			    case 3 :{
			    	List<Lojas> lojas = lojaAppService.recuperaLojas();

					if (lojas.size() != 0) {
					    for (Lojas l : lojas) {
					    	System.out.println('\n' + "Id = " + l.getId() 
						                        + "\nEndereco = " + l.getEndereco());
					    }
					} else {
					    System.out.println('\n' + "Não há lojas cadastradas.");
					}
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
