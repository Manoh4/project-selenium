package br.com.selenium.teste;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.selenium.teste.inicializacao.InitializeResources;

public class FrameAndWindowTest {
	
	private static InitializeResources resource = new InitializeResources();
	private static WebDriver driver;

	@Before
	public void getURL() {
		driver = new InitializeResources().getWebdriver();
		resource.initiateBrowser(driver);
	}

	@Test
//	@Ignore
	public void register() {
		WebDriver frame = driver.switchTo().frame("frame1");

		frame.findElement(By.id("frameButton")).click();
		Alert alert = frame.switchTo().alert();
		Assert.assertEquals("Frame OK!", alert.getText());
		alert.accept();

	}

	@After
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

}
