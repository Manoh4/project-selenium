package br.com.selenium.teste;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.selenium.teste.campotreinamento.TelaCampoTreinamento;
import br.com.selenium.teste.dsl.DSL;
import br.com.selenium.teste.inicializacao.InitializeResources;

public class RegisterTest {
	

	private static InitializeResources resource = new InitializeResources();
	private static WebDriver driver;
	private DSL dsl;
	private TelaCampoTreinamento page;

	@Before
	public void getURL() {
		driver = new InitializeResources().getWebdriver();
		resource.initiateBrowser(driver);
	}

	@Test
//	@Ignore
	public void register() {
		String nome = "Emanuel";
		driver.findElement(By.id("elementosForm:nome")).sendKeys(nome);
		
		String sobrenome = "Preto Cardoso";
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys(sobrenome);
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		String sexo = driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value");
		
		driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		List<WebElement> comidas = driver.findElement(By.id("elementosForm:comidaFavorita")).findElements(By.id("value"));
		
		WebElement combobox = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(combobox);
		combo.selectByVisibleText("Superior");
		WebElement escolaridade = combo.getFirstSelectedOption();
		
		
		WebElement combobox2 = driver.findElement(By.id("elementosForm:esportes"));
		Select combo2 = new Select(combobox2);
		combo2.selectByVisibleText("Corrida");
		combo2.selectByVisibleText("Karate");
		List<WebElement> esportes = combo2.getAllSelectedOptions();
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Devo comer mais vegetais e frutas");;
		String sugestoes = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		
//		List<WebElement> resultados = driver.findElement(By.id("resultado")).findElements(By.tagName("div"));
//		List<String> esperado = Arrays.asList(nome, 
//											  sobrenome,
//											  sexo.equals("M") ? "Masculino" : "Feminino",
//											  comidas.get(1).toString(),
//											  escolaridade.getText(),
//											  esportes.get(1).toString(),
//											  sugestoes	
//											 );
//		int contador = 0;
//		for (WebElement item : resultados) {
//			String texto = item.findElement(By.tagName("span")).getText();
//			Assert.assertTrue("Esperado:" + esperado.get(contador) + "  -  Obtido: " + item.getText(),texto.contains(esperado.get(contador).toString()));
//			System.out.println("Esperado: "+esperado.get(contador) +   "   - Obtido: " + texto);
//			contador++;
//		}
		
		
	}
	
	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Wagner");
		page.setSobrenome("Costa");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolaridade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Wagner"));
		Assert.assertEquals("Sobrenome: Costa", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsportesCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio(){
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio(){
		page.setNome("Nome qualquer");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso(){
		page.setNome("Nome qualquer");
		page.setSobrenome("Sobrenome qualquer");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}

	
	
	@After
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}


}
