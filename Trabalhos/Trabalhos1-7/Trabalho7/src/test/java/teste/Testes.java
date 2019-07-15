package teste;

import java.util.GregorianCalendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import excecao.LojaComEndereçoSemRua;
import modelo.Lojas;
import servico.LojaAppService;

@ContextConfiguration(locations = "/applicationContext.xml")
public class Testes extends AbstractTransactionalJUnit4SpringContextTests {
	
	
	
	@Test(expected=LojaComEndereçoSemRua.class)
    public void incluiLojaComEnderecoSemRua() throws LojaComEndereçoSemRua {
		System.out.println(">>>>>>>>>>>>> Começou a execução do teste 1");

		ApplicationContext fabrica = new ClassPathXmlApplicationContext("/applicationContext.xml");
		LojaAppService lojaAppService = (LojaAppService)fabrica.getBean ("lojaAppService");

		//verifica se ele vai aceitar uma loja com endereço que não contem rua
		Lojas loja = new Lojas("avenida");
    	lojaAppService.inclui(loja);
    }
	
	@Test(expected=DataIntegrityViolationException.class)
    public void removeMesaComAtendimentos()  {
		System.out.println(">>>>>>>>>>>>> Começou a execução do teste 2");
		jdbcTemplate.update(
			    "INSERT INTO LOJAS(ENDERECO) " + 
			    "VALUES (?)",
			    "Rua x - numero y");
		jdbcTemplate.update(
			    "INSERT INTO FUNCIONARIOS(NOME, FUNCAO, LOJAS_ID) " + 
			    "VALUES (?, ?, ?)",
			    "Funcionario", "Garçom", 1);
		jdbcTemplate.update(
			    "INSERT INTO MESAS(ID, NUMERO, FUNCIONARIOS_ID, LOJAS_ID) " + 
			    "VALUES (?, ?, ?, ?)",
			    1, 1, 1, 1);
		jdbcTemplate.update(
			    "INSERT INTO ATENDIMENTOS(INICIODOATENDIMENTO, FIMDOATENDIMENTO, VALORTOTALCONTA, MESAS_ID) " + 
			    "VALUES (?, ?, ?, ?)",
			    new GregorianCalendar(), new GregorianCalendar(), 50.0, 1);
		//tenta remover uma mesa que tem atendimentos
		jdbcTemplate.update("DELETE FROM MESAS");
    }


}