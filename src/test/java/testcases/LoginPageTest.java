package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseClass;
import pages.LoginPage;
import testutils.Screenshot;

public class LoginPageTest extends BaseClass {
	LoginPage loginPage;
	// Create global variable which will be used in all method
	ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\ManishAutomation1.html");
	// Create object of ExtentReports class- This is main class which will create report
	ExtentReports extent = new ExtentReports();
	ExtentTest logger;

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@BeforeTest
	public void ExtentReportConfiguration() throws IOException {
		extent.attachReporter(htmlreporter);
		
		// Customize Report property
		htmlreporter.config().setReportName("Manish Test Report");
		extent.setSystemInfo("Host Name", "Test Host");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "QA Automation");
		htmlreporter.config().setDocumentTitle("Automation Report");
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);

		// Two default theme of report DARK or STANDARD
		htmlreporter.config().setTheme(Theme.STANDARD);
	}

	// validation user is not signed in
	@Test(priority = 1)
	public void verifySignInLabel() {
		logger = extent.createTest("verifySignInLabel");
		Assert.assertTrue(loginPage.validateHelloSignInLabel());
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));
	}

	// signing in
	@Test(priority = 2)
	public void userSignIn() {
		logger = extent.createTest("userSignIn");
		loginPage.clickOnSignIn();
		loginPage.signIn(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(loginPage.userNameLabel(prop.getProperty("user")), "Hello, " + prop.getProperty("user"));
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));
	}

	// signing out
	@Test(priority = 3)
	public void userSignOut() {
		logger = extent.createTest("userSignOut");
		loginPage.clickOnSignIn();
		loginPage.signOut();
		Assert.assertTrue(loginPage.verifySignOut());
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed is passTest", ExtentColor.GREEN));
	}

	// This will run after testcase and it will capture screenshot and add in report
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			String temp = Screenshot.getScreenshot(driver);

			logger.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}

		extent.flush();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
