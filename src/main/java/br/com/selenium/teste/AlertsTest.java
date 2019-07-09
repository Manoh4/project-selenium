package br.com.selenium.teste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.selenium.teste.inicializacao.InitializeResources;

public class AlertsTest {
	
	private static InitializeResources resource = new InitializeResources();
	private static WebDriver driver;

	@Before
	public void getURL() {
		driver = new InitializeResources().getWebdriver();
		resource.initiateBrowser(driver);
	}

	@Test
	@Ignore
	public void checkAlertMessage() {
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Alert Simples", texto);
		
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
		Assert.assertEquals(texto, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}

	@Test
//	@Ignore
	public void confirmAlertMessage() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Confirm Simples", texto);		
		
		alert.accept();
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		Assert.assertEquals(texto, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	
	@Test
	@Ignore
	public void dismissAlertMessage() {
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Confirm Simples", texto);		
		
		alert.dismiss();
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		Assert.assertEquals(texto, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	
	@Test
//	@Ignore
	public void typeAlertMessage() {
		driver.findElement(By.id("prompt")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		
		Assert.assertEquals("Digite um numero", texto);		
		
		alert.sendKeys("Ola");
		alert.accept();
		alert.accept();
		alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		Assert.assertEquals(texto, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}
	

	@After
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}
}
