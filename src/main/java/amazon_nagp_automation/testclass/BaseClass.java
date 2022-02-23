package amazon_nagp_automation.testclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;

/**
 * 
 * @author ravishbagasi
 *
 */
public class BaseClass
{
	public static WebDriver driver;
	static String dirPath = System.getProperty("user.dir");

	// private static Logger logger = (Logger)
	// LogManager.getLogger(BaseClass.class);

	/**
	 * Initialize the chrome and open the site url
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Parameters("browser")
	public static void init() throws InterruptedException, IOException
	{
		String browser = readProperties("browser");

		System.out.println(browser);

		if (browser.equalsIgnoreCase("Chrome"))
		{
			// set path to Chrome driver
			System.setProperty("webdriver.chrome.driver",
					dirPath + File.separator + "drivers" + File.separator + "chromedriver.exe");
			// create Chrome instance
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("Firefox"))
		{
			// set path to Firefox driver
			System.setProperty("webdriver.gecko.driver",
					dirPath + File.separator + "drivers" + File.separator + "geckodriver.exe");
			// create firefox instance
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("IE"))
		{
			// set path to IE driver
			System.setProperty("webdriver.ie.driver",
					dirPath + File.separator + "drivers" + File.separator + "IEDriverServer.exe");
			// create IE instance
			driver = new InternetExplorerDriver();
		}
		driver.get(readProperties("url"));
		Thread.sleep(2000);
	}

	/**
	 * Read Properties from property file
	 * 
	 * @param property
	 * @return
	 * @throws IOException
	 */
	public static String readProperties(String property) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(dirPath + File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator + "config.properties");
		prop.load(ip);
		property = prop.getProperty(property);
		return property;

	}

	/**
	 * Take screenshot when test case got failed
	 * 
	 * @param testMethodName
	 */
	public void failed(String testMethodName)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm");
		Date date = new Date();

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try
		{
			// formatter.format(date)
			FileUtils.copyFile(srcFile, new File(dirPath + File.separator + "Current test results" + File.separator
					+ testMethodName + formatter.format(date) + ".png"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
