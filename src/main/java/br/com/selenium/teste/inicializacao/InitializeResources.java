package br.com.selenium.teste.inicializacao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InitializeResources {
	
	
	public WebDriver getWebdriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public void initiateBrowser(WebDriver driver) {
		driver.get("file:\\C:\\Users\\manoh\\Documents\\PROJETOS CURSOS\\Selenium\\Selenium WebDriver\\Campo de treinamento\\componentes.html");
		driver.manage().window().maximize();
	}
}
