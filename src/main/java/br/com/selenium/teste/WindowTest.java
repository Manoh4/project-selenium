package br.com.selenium.teste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.selenium.teste.inicializacao.InitializeResources;

public class WindowTest {
	
	private static InitializeResources resource = new InitializeResources();
	private static WebDriver driver;

	@Before
	public void getURL() {
		driver = new InitializeResources().getWebdriver();
		resource.initiateBrowser(driver);
	}

	@Test
//	@Ignore
	public void switchWindows() {
		driver.findElement(By.id("buttonPopUpHard")).click();
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Oi oi oi");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("Oi oi oi");
		
	}
	
	@After
	public void closeBrowser() {
//		driver.close();
//		driver.quit();
	}



}
