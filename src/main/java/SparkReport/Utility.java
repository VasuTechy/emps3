package SparkReport;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Utility {
	ExtentHtmlReporter Reporter;
	ExtentReports extent;
	WebDriver driver;
	ExtentTest test;

	@BeforeSuite
	public void launchbrowser() {
		extent = new ExtentReports();
		Reporter = new ExtentHtmlReporter("ExtentReport.html");
		extent.attachReporter(Reporter);

		System.setProperty("webdriver.chrome.driver", "/home/promantus/eclipse-workspace/ExtentReports/exe/chromedriver");
        ChromeOptions options = new ChromeOptions();
       options.addArguments("--no-sandbox");
       options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920x1080");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(" https://dashboard-emps.g10.pw");
		// Reporter.loadXMLConfig(System.getProperty("ExtentReport.html")+"/reportsconfig.xml");
		extent.setSystemInfo("author", "VASU");
		Reporter.config().setDocumentTitle("Automation test report");
		Reporter.config().setReportName("Smokereport");
		Reporter.config().setTheme(Theme.DARK);
		Reporter.config().setTimeStampFormat("dd-MM-yyyy  hh:mm '('zzz')'");
		extent.setSystemInfo("host", "promantus");
		extent.setSystemInfo("projectname", "EMPS");
		extent.setSystemInfo("OS", "LINUX");

	}

	@Test(priority = 1)
	public void Homepage() throws Exception {
		test = extent.createTest("Homepage");
		test.assignAuthor("Vasu");
		// test.assignCategory("performance testing");
		test.log(Status.INFO, "prerequisite is homepage");
		WebElement customerlogin = driver.findElement(By.xpath("//button[text()='Customer login']"));
		customerlogin.click();
		Thread.sleep(3000);
	//	if (customerlogin.isEnabled()) {
			test.log(Status.PASS, "customer login button are available");
			test.log(Status.INFO, "customer login button is displayed");
		//} else {
		//	test.log(Status.FAIL, "customer login button is not visible");

		}
	//}

	@Test(priority = 2)
	public void loginpage() throws Exception {
		test = extent.createTest("loginpage");
		test.assignAuthor("NAZ");
		test.log(Status.INFO, "prerequisite is customer login page");
		WebElement username = driver.findElement(By.xpath("(//input[@aria-label='Username'])[1]"));
		username.sendKeys("vasudevan@promantus.com");
		Thread.sleep(3000);
		System.out.println(username.getText());

		if (username.getText().equals("vasudevan@promantus.com")) {

			test.log(Status.INFO, "username has verified");
			test.log(Status.PASS, "valid username");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
			File Destinationfile = new File("Loginpage.png");
			org.openqa.selenium.io.FileHandler.copy(sourcefile, Destinationfile);
			test.addScreenCaptureFromPath("Loginpage.png");

		} else {
			test.log(Status.FAIL,  "invalid username");
		}

		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter Password']"));
		password.sendKeys("sddl4");
		System.out.println(password.getText());
		if (password.getText().equals("sddl4")) {

			test.log(Status.PASS, "valid password");
			test.log(Status.INFO, "password has verified");

		} else {
			test.log(Status.FAIL, "invalid password");
		}

	}

	@Test(priority = 3)
	public void forgetpassword() throws Exception {
		test = extent.createTest("forgetpassword");
		test.assignAuthor("XYZ");
		WebElement forget = driver.findElement(By.xpath("//a[text()='Forgot Password?']"));
		forget.click();
		Thread.sleep(2000);
		System.out.println(forget.getText());

		if (forget.getText().equals("Forgot Password?")) {

			test.log(Status.PASS, "forget password clicked successfully");

			test.log(Status.INFO, " forget password are enabled");
		} else {
			test.log(Status.FAIL, "forget password not clicked");
			test.log(Status.INFO, "forget password are disable");
		}

	}

	@Test(priority = 4)
	public void emailsubmittion() throws Exception {
		test = extent.createTest("emailsubmitted");
		test.assignAuthor("ijk");
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
		email.sendKeys("abcd@gmail.com");
		System.out.println(email.getText());

		test.log(Status.PASS, "email successfully submitted");
		WebElement cancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
		cancel.click();
		Thread.sleep(2000);
		test.log(Status.PASS, "succesfully clicked on cancel button");

	}

	@Test(priority = 5)
	public void eye() throws Exception {
		test = extent.createTest("check showpassword");
		test.assignAuthor("ABC");
		test.log(Status.INFO, "prerequiste should be transport page");
		test.log(Status.PASS, "moved to eye symbol");
		WebElement eye = driver.findElement(By.xpath("//i[@mattooltip='Show Password']"));
		eye.click();
		test.log(Status.PASS, "successfully clicked on show password");
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void hidepassword() throws Exception {
		test = extent.createTest("hidepassword");
		test.assignAuthor("VASU");
		test.log(Status.INFO, "prerequiste should be transport page");
		test.log(Status.PASS, "arrow in eye symbol");
		WebElement hide = driver.findElement(By.xpath("//i[@mattooltip='Hide Password']"));
		hide.click();
		test.log(Status.PASS, "successfully clicked on hide passoword");
		Thread.sleep(2000);
	}

	@AfterSuite
	public void teardown() {
		extent.flush();
	}

}