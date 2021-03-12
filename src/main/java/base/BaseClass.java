package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import testutils.Xls_Reader;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Xls_Reader xlsReader;

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream ifs = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\config\\config.properties");
			prop.load(ifs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("Edge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\EdgeDriver.exe");
			driver = new EdgeDriver();
		}

		js = (JavascriptExecutor) driver;
		xlsReader = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\main\\resources\\testdata\\searchdata.xlsx");

		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}
}
