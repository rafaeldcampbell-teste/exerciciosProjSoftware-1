import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ObjetoNaoEncontradoException;
import excecao.PermissaoNegadaException;
import modelo.Atendimentos;
import modelo.Mesas;
import servico.AtendimentoAppService;
import servico.MesaAppService;

public class PrincipalAtendimento {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws ParseException, ObjetoNaoEncontradoException {
		long id;
		long id_mesa;
		float valorTotalConta;
		Mesas mesa;
		Atendimentos atendimento;
		List<Atendimentos> atendimentos = new ArrayList<Atendimentos>();
		
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		AtendimentoAppService atendimentoAppService = (AtendimentoAppService)fabrica.getBean ("atendimentoAppService");
		MesaAppService mesaAppService = (MesaAppService)fabrica.getBean ("mesaAppService");
		

		boolean continua = true;
		while (continua) {
			System.out.println( "O que você deseja fazer?\n");
		    System.out.println("1. Cadastrar um atendimento em uma mesa");
		    System.out.println("2. Remover um atendimento de uma mesa");
		    System.out.println("3. Listar todos os atendimentos de uma mesa");
		    System.out.println("4. Voltar");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");
		    try {
			    switch (opcao) {
				    case 1:{
			    		id_mesa = Console.readInt('\n' + "Digite o id da mesa onde deseja cadastrar o atendimento: ");
			    		try {
			    			mesa = mesaAppService.recuperaUmaMesa(id_mesa);
			    		} catch (ObjetoNaoEncontradoException e) {
			    			break;
			    		}
			    		String inicio = Console.readLine('\n' + "Digite o início do atendimento (dd/mm/yy HH:mm) : ");
			    		String fim = Console.readLine('\n' + "Digite o fim do atendimento (dd/mm/yy HH:mm) : ");
			    		valorTotalConta = (float) Console.readDouble('\n' + "Digite o valor total da conta: ");
			    		
			    		Calendar calIni = Calendar.getInstance();
			    		Calendar calFim = Calendar.getInstance();
			    		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YY HH:mm");
			    		calIni.setTime(sdf.parse(inicio));
			    		calFim.setTime(sdf.parse(fim));
			    		
			    		atendimento = new Atendimentos(calIni, calFim, valorTotalConta, mesa);
			    		
			    		atendimento = atendimentoAppService.inclui(atendimento);
			    		
			    		System.out.println("Atendimento "+atendimento.getId()+" cadastrada com sucesso!");
			    		break;
			    	}
			    	
			    	case 2:{
			    		id = Console.readInt('\n' + "Digite o codigo do atendimento que deseja remover: ");
			    		
						try {
						    atendimento = atendimentoAppService.recuperaAtendimento(id);
						} catch (ObjetoNaoEncontradoException e) {
						    break;
						}
		
						System.out.println('\n' + "Id = " + atendimento.getId() 
												+ "\nInicio = " + atendimento.getInicioDoAtendimento().toString()
												+ "\nFim = "+ atendimento.getFimDoAtendimento().toString()
												+ "\nValor = " + atendimento.getValorTotalConta());
		
						String resp = Console.readLine('\n' + "Confirma a remoção do atendimento?");
		
						if (resp.equals("s")) {
						    try {
							atendimentoAppService.exclui(atendimento.getId());
							System.out.println('\n' + "Atendimento removido com sucesso!");
						    } catch (Exception e) {
						    	break;
						    }
						} else {
						    System.out.println('\n' + "Atendimento não removido.");
						}
						break;
			    	}
			    	
			    	case 3: {
			    		id_mesa = Console.readInt('\n' + "Digite o id da mesa cujos atendimentos serão listados: ");
			    		
			    		try{
			    			atendimentos = atendimentoAppService.recuperaAtendimentos(id_mesa);
			    		} catch (Exception e){
			    			break;
			    		}
			    		for(Atendimentos a : atendimentos) {
			    			System.out.println('\n' + "Id = " + a.getId() 
													+ "\nInicio = " + a.getInicioDoAtendimento()
													+ "\nFim = "+ a.getFimDoAtendimento()
													+ "\nValor = " + a.getValorTotalConta());
			    		}
			    		atendimentos.clear();
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
		    }catch (PermissaoNegadaException e) {
		    	System.out.println("Você não tem permissão para acessar este método!");
		    }
		}
	}
}
