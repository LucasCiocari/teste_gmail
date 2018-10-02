package pucrs.com.gmail;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GmailTest {
	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();

		String initialPage = "https://accounts.google.com/AccountChooser/identifier?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AddSession";
		driver.get(initialPage);
	}

	@After
	public void teardown() {
		if (driver != null)
			driver.close();
	}

	@Test
	public void SendMailFunctionalTest() {		
		WebDriverWait wait = new WebDriverWait(driver,10);
		By tgt_id = By.id("identifierId");
		wait.until(presenceOfElementLocated(tgt_id));
		WebElement button  = driver.findElement(tgt_id);
		button.sendKeys("testess2b20182@gmail.com\n");
	
		wait.until(presenceOfElementLocated(By.name("password")));
		WebElement passd_local  = driver.findElement(By.name("password"));
		passd_local.sendKeys("s2b2018/2");

		WebElement next_btn  = driver.findElement(By.id("passwordNext"));
		next_btn.sendKeys("\n");
		
		wait.until(presenceOfElementLocated(By.className("z0")));
		WebElement compose_btn  = driver.findElement(By.className("z0"));
		compose_btn.click();
		
		wait.until(presenceOfElementLocated(By.name("to")));
		WebElement sender_field  = driver.findElement(By.name("to"));
		sender_field.sendKeys("testess2b20182@gmail.com");
		
		wait.until(presenceOfElementLocated(By.name("subjectbox")));
		WebElement subject_field  = driver.findElement(By.name("subjectbox"));
		subject_field.sendKeys("MeuPrimeiroEmail");
		
		wait.until(presenceOfElementLocated(By.cssSelector("[class='Am Al editable LW-avf']")));
		WebElement text_field = driver.findElement(By.cssSelector("[class='Am Al editable LW-avf']"));
		text_field.sendKeys("Exemplo de mensagem");
		
		wait.until(presenceOfElementLocated(By.cssSelector("[class='T-I J-J5-Ji aoO T-I-atl L3']")));
		WebElement snd_btn = driver.findElement(By.cssSelector("[class='T-I J-J5-Ji aoO T-I-atl L3']"));
		snd_btn.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
