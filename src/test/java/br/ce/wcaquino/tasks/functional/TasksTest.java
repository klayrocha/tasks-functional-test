package br.ce.wcaquino.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	private WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botão "Add Todo"
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");

			// Clicar no botão save
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", message);

		} finally {
			// Fechar o browser
			driver.quit();
		}
	}

	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botão "Add Todo"
			driver.findElement(By.id("addTodo")).click();

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2021");

			// Clicar no botão save
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", message);

		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botão "Add Todo"
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Clicar no botão save
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", message);

		} finally {
			// Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();

		try {
			// Clicar no botão "Add Todo"
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2019");

			// Clicar no botão save
			driver.findElement(By.id("saveButton")).click();

			// Validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", message);

		} finally {
			// Fechar o browser
			driver.quit();
		}
	}

}
