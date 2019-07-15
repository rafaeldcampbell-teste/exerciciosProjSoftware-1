import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import corejava.Console;
import excecao.ObjetoNaoEncontradoException;
import modelo.Atendimentos;
import modelo.Mesas;
import servico.AtendimentoAppService;
import servico.MesaAppService;

public class PrincipalAtendimento {

	public static void main(String[] args) throws ParseException, ObjetoNaoEncontradoException {
		int id;
		int id_mesa;
		float valorTotalConta;
		Mesas mesa;
		Atendimentos atendimento;
		List<Atendimentos> atendimentos = new ArrayList<Atendimentos>();
		
		AtendimentoAppService atendimentoAppService = new AtendimentoAppService();
		MesaAppService mesaAppService = new MesaAppService();
		

		boolean continua = true;
		while (continua) {
			System.out.println( "O que você deseja fazer?\n");
		    System.out.println("1. Cadastrar um atendimento em uma mesa");
		    System.out.println("2. Remover um atendimento de uma mesa");
		    System.out.println("3. Listar todos os atendimentos de uma mesa");
		    System.out.println("4. Somar valor em um atendimento de uma mesa");
		    System.out.println("5. Voltar");

		    int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");
		    
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
		    		
		    		id = atendimentoAppService.inclui(atendimento);
		    		
		    		System.out.println("Atendimento "+id+" cadastrada com sucesso!");
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
		    		id = Console.readInt('\n' + "Digite o codigo do atendimento a qual deseja somar um valor: ");
		    		
					try {
					    atendimento = atendimentoAppService.recuperaAtendimento(id);
					} catch (ObjetoNaoEncontradoException e) {
					    break;
					}
	
					System.out.println('\n' + "Id = " + atendimento.getId() 
											+ "\nInicio = " + atendimento.getInicioDoAtendimento()
											+ "\nFim = "+ atendimento.getFimDoAtendimento()
											+ "\nValor = " + atendimento.getValorTotalConta());
	
					float valor = (float) Console.readDouble('\n' + "Qual valor deseja somar? ");
					
					atendimentoAppService.atualizaValor(atendimento.getId(), valor);
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
