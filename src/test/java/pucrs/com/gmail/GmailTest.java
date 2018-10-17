package pucrs.com.gmail;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GmailTest {
	private WebDriver driver;

	private String mailSender = "<testess2b20182@gmail.com>";
	private String messageBody = "Exemplo de mensagem";
	private String subject = "MeuPrimeiroEmail";

	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();

	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		String initialPage = "https://accounts.google.com/AccountChooser/identifier?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&flowName=GlifWebSignIn&flowEntry=AddSession";
		driver.get(initialPage);
		report = new ExtentReports("ExtentReportResults.html");
	}

	@After
	public void teardown() {
		if (driver != null)
			driver.close();
		report.endTest(test);
		report.flush();
	}

	@Test
	public void SendMailFunctionalTest() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By tgt_id = By.id("identifierId");
		wait.until(presenceOfElementLocated(tgt_id));
		WebElement button = driver.findElement(tgt_id);
		button.sendKeys("testess2b20182@gmail.com\n");

		wait.until(presenceOfElementLocated(By.name("password")));
		wait.until(visibilityOfElementLocated(By.name("password")));
		WebElement passd_local = driver.findElement(By.name("password"));
		passd_local.sendKeys("s2b2018/2");

		WebElement next_btn = driver.findElement(By.id("passwordNext"));
		next_btn.sendKeys("\n");

		wait.until(presenceOfElementLocated(By.className("z0")));
		WebElement compose_btn = driver.findElement(By.className("z0"));
		compose_btn.click();

		wait.until(presenceOfElementLocated(By.name("to")));
		WebElement sender_field = driver.findElement(By.name("to"));
		sender_field.sendKeys("testess2b201822@gmail.com");

		wait.until(presenceOfElementLocated(By.name("subjectbox")));
		WebElement subject_field = driver.findElement(By.name("subjectbox"));
		subject_field.sendKeys(subject);

		wait.until(presenceOfElementLocated(By.cssSelector("[class='Am Al editable LW-avf']")));
		WebElement text_field = driver.findElement(By.cssSelector("[class='Am Al editable LW-avf']"));
		text_field.sendKeys(messageBody);

		wait.until(presenceOfElementLocated(By.cssSelector("[class='T-I J-J5-Ji aoO T-I-atl L3']")));
		WebElement snd_btn = driver.findElement(By.cssSelector("[class='T-I J-J5-Ji aoO T-I-atl L3']"));
		snd_btn.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void emailReceived() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test = report.startTest("Test if Email was received");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By tgt_id = By.id("identifierId");
		wait.until(presenceOfElementLocated(tgt_id));
		WebElement button = driver.findElement(tgt_id);
		button.sendKeys("testess2b201822@gmail.com\n");

		wait.until(presenceOfElementLocated(By.name("password")));
		wait.until(visibilityOfElementLocated(By.name("password")));
		WebElement passd_local = driver.findElement(By.name("password"));
		passd_local.sendKeys("s2b20182");

		WebElement next_btn = driver.findElement(By.id("passwordNext"));
		next_btn.sendKeys("\n");

		wait.until(presenceOfElementLocated(By.id(":39")));
		WebElement select_mail = driver.findElement(By.id(":39"));
		select_mail.click();

		wait.until(presenceOfElementLocated(By.id(":5y")));
		WebElement mail_title = driver.findElement(By.id(":5y"));
		System.out.println(mail_title.getText());

		wait.until(presenceOfElementLocated(By.className("go")));
		WebElement sender_address = driver.findElement(By.className("go"));
		System.out.println(sender_address.getText());

		wait.until(presenceOfElementLocated(By.xpath("//*[@id=\":7i\"]/div[1]")));
		WebElement mail_body = driver.findElement(By.xpath("//*[@id=\":7i\"]/div[1]"));
		System.out.println(mail_body.getText());

		assertEquals(subject, mail_title.getText());
		assertEquals(mailSender, sender_address.getText());
		assertEquals(messageBody, mail_body.getText());

		if (subject.contentEquals(mail_title.getText()))
			test.log(LogStatus.PASS, "Valor confirmado");
		else
			test.log(LogStatus.FAIL, "Valor difere do esperado");

		if (mailSender.contentEquals(sender_address.getText()))
			test.log(LogStatus.PASS, "Valor confirmado");
		else
			test.log(LogStatus.FAIL, "Valor difere do esperado");

		if (messageBody.contentEquals(mail_body.getText()))
			test.log(LogStatus.PASS, "Valor confirmado");
		else
			test.log(LogStatus.FAIL, "Valor difere do esperado");

		
		  wait.until(presenceOfElementLocated(By.
		  xpath("//*[@id=\":5\"]/div[2]/div[1]/div/div[2]/div[3]/div")));
		  wait.until(visibilityOfElementLocated(By.xpath("//*[@id=\":5\"]/div[2]/div[1]/div/div[2]/div[3]/div")));
		  WebElement delete_button = driver.findElement(By.xpath("//*[@id=\":5\"]/div[2]/div[1]/div/div[2]/div[3]/div"));
		  delete_button.click();
		 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
