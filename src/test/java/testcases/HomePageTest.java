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
import pages.HomePage;
import testutils.Screenshot;

public class HomePageTest extends BaseClass {
	HomePage homePage;
	String itemName;
	ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(
			System.getProperty("user.dir") + "\\Reports\\HomePageReport.html");

	ExtentReports extent = new ExtentReports();
	ExtentTest logger;

	@BeforeClass
	public void beforeClass() {
		//BaseClass.initialization() will launch browser and navigate to the AUT url 
		initialization();
		homePage = new HomePage();
		itemName = xlsReader.getCellData("Sheet1", "Search-Data", 2);
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
		// Two default theme of report
		htmlreporter.config().setTheme(Theme.STANDARD);

	}

	// search an Item(item detail I am getting from excel sheet), and verify the search result
	@Test(priority = 1)
	public void searchItemAndVerifySearchResult() {
		logger = extent.createTest("searchItemAndVerifySearchResult");
		logger.log(Status.INFO, "Anonymus user searching an item...");
		homePage.searchItem(itemName);
		logger.log(Status.INFO, "Verifying the search result...");
		Assert.assertEquals(homePage.verifySearchResult(), "\"" + itemName + "\"");
		logger.log(Status.PASS, MarkupHelper.createLabel("Test Case is Passed", ExtentColor.GREEN));
	}

	// This will run after @test method and it will capture screenshot if the test method is getting failed and add it in report
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
	public void afterClass() {
		 driver.quit();
	}

}
