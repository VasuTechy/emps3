package SparkReport;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

public class Basicsparkreport {
	ExtentHtmlReporter Reporter;
	ExtentReports extent;
	WebDriver driver;
	ExtentTest test;
	

	@BeforeSuite
	public void launchbrowser() {
		extent = new ExtentReports();
		Reporter = new ExtentHtmlReporter("ExtentReport.html");
		extent.attachReporter(Reporter);

		System.setProperty("webdriver.chrome.driver", "/home/promantus/eclipse-workspace/emps/exe/chromedriver");

		driver = new ChromeDriver();
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
		test.assignCategory("performance testing");
		test.log(Status.INFO, "prerequisite is homepage");
//		test = extent.createTest("Code Blocks");
//
//		// xml
//		String code = "<root>" + "\n    <Person>" + "\n        <Name>Joe Doe</Name>"
//				+ "\n        <StartDate>2007-01-01</StartDate>" + "\n        <EndDate>2009-01-01</EndDate>"
//				+ "\n        <Location>London</Location>" + "\n    </Person>                    " + "\n    <Person>"
//				+ "\n        <Name>John Smith</Name>" + "\n        <StartDate>2012-06-15</StartDate>"
//				+ "\n        <EndDate>2014-12-31</EndDate>" + "\n        <Location>Cardiff</Location>"
//				+ "\n    </Person>" + "\n</root>";
//		Markup m = MarkupHelper.createCodeBlock(code, CodeLanguage.XML);
//		test.pass(m);
		String text = "Customer Login";
		if (text.contains("Customer login")) {
			test.log(Status.PASS, "successfully clicked on customer login button");
			Thread.sleep(2000);
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
//			File Destinationfile = new File("Homepage.png");
//			org.openqa.selenium.io.FileHandler.copy(sourcefile, Destinationfile);
//			test.addScreenCaptureFromPath("Homepage.png");

		} else {
			test.log(Status.FAIL, "button is not there");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
//			File Destinationfile = new File("Homepage1.png");
//			org.openqa.selenium.io.FileHandler.copy(sourcefile, Destinationfile);
//			test.addScreenCaptureFromPath("Homepage1.png");
		}
		WebElement customerlogin = driver.findElement(By.xpath("//button[text()='Customer login']"));
		customerlogin.click();
		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void loginpage() throws Exception {
		test = extent.createTest("loginpage");
		test.assignAuthor("NAZ");
		test.log(Status.INFO, "prerequisite is customer login page");
//		String text1 = "extent";
//		Markup m = MarkupHelper.createLabel(text1, ExtentColor.BLUE);
//
//		test.pass(m);
	//	String json = "{'foo' : 'bar', 'foos' : ['b','a','r'], 'bar' : {'foo':'bar', 'bar':false,'foobar':1234}}";
		//test.pass(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));

		WebElement username = driver.findElement(By.xpath("(//input[@aria-label='Username'])[1]"));
		username.sendKeys("vasudevan@promantus.com");
		String text = "vasudevan@promantus.com";
		if (text.contains("vasudevan@promantus.com")) {
			ExtentTest username1 = test.createNode("positive usename");
			username1.pass("This section is passed");
			test.log(Status.PASS, "username has matched");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
			File Destinationfile = new File("Loginpage.png");
			org.openqa.selenium.io.FileHandler.copy(sourcefile, Destinationfile);
			test.addScreenCaptureFromPath("Loginpage.png");

		} else

		{
			ExtentTest username2 = test.createNode("invalid username");
			username2.fail("This section is failed");
			test.log(Status.FAIL, "username mismatched");
		}
		Thread.sleep(3000);
		WebElement password = driver.findElement(By.xpath("//input[@placeholder='Enter Password']"));
		password.sendKeys("sddl4");
		String pwd = "sddl4";
		if (pwd.contains("sddl4")) {
			ExtentTest username1 = test.createNode("valid  password");
			username1.pass("This section is passed");
			test.log(Status.PASS, "password has matched");
			
		} else {
			ExtentTest username2 = test.createNode("invalid password");
			username2.fail("This section has failed");
			test.log(Status.FAIL, "password mismatched");
//			TakesScreenshot screenshot = (TakesScreenshot) driver;
//			File outputfile = screenshot.getScreenshotAs(OutputType.FILE);
//			File file = new File("");
//			FileUtils.copyFile(outputfile, file);

		}
		Thread.sleep(3000);
//		WebElement login = driver.findElement(By.xpath("//span[text()='Login']"));
//		login.click();
//		test.log(Status.INFO, "successfully clicked login button");
//		Thread.sleep(5000);
	}

	@Test(priority = 3)
	public void forgetpassword() throws Exception {
		test = extent.createTest("forgetpassword");
		test.assignAuthor("XYZ");
		String[][] data = { { "S.NO", "TESTCASES", "PASSED/FAILED" }, { "1.1", "hOMEPAGE", "PASS" },
				{ "1.2", "LOGINPAGE", "PASS" }, { "1.3", "SHOWPASSWORD", "PASS" },
				{ "1.4", "FORGETPASSWORD", "PASS" } };
		Markup m = MarkupHelper.createTable(data);

		test.pass(m);
		WebElement forget = driver.findElement(By.xpath("//a[text()='Forgot Password?']"));
		forget.click();
		Thread.sleep(2000);
		String text = "Forgot Password?";
		if (text.contains("Forgot Password?")) {

			test.log(Status.PASS, "clicked succesffully ");
		} else {
			test.log(Status.FAIL, "Not clicked succesffully ");

		}
		test.log(Status.INFO, "application in transport manager log");
		WebElement email = driver.findElement(By.xpath("//input[@placeholder='Email']"));
		email.sendKeys("abcd@gmail.com");
		test.log(Status.INFO, "email submitted");
		Thread.sleep(2000);
		WebElement cancel = driver.findElement(By.xpath("//button[text()='Cancel']"));
		cancel.click();
		test.log(Status.INFO, "succesfully clicked on cancel button");
//		WebElement submit = driver.findElement(By.xpath("//button[text()='Submit']"));
//		submit.click();
//		Thread.sleep(2000);
//		test.log(Status.INFO, "clicked submit button successfully");
	}

	@Test(priority = 4)
	public void eye() throws Exception {
		test = extent.createTest("checkshowpassword");
		test.assignAuthor("ABC");
		test.log(Status.INFO, "prerequiste should be transport page");
		test.log(Status.INFO, "moved to eye symbol");

		WebElement eye = driver.findElement(By.xpath("//i[@mattooltip='Show Password']"));
		eye.click();
		test.log(Status.INFO, "successfully clicked on show password");
		Thread.sleep(2000);

	}

	@Test(priority = 5)
	public void hidepassword() throws Exception {
		test = extent.createTest("hidepassword");
		test.assignAuthor("VASU");
		test.log(Status.INFO, "prerequiste should be transport page");
		test.log(Status.INFO, "arrow in eye symbol");
		WebElement hide = driver.findElement(By.xpath("//i[@mattooltip='Hide Password']"));
		hide.click();
		test.log(Status.INFO, "successfully clicked on hide passoword");
		Thread.sleep(2000);
	}

	@AfterSuite
	public void teardown() {
		extent.flush();
	}
}
