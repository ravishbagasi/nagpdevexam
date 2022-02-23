package amazon_nagp_automation.testclass;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import amazon_nagp_automation.pages.AddToCartPage;
import amazon_nagp_automation.pages.AmazonPrimePage;
import amazon_nagp_automation.pages.ChangeLanguagePage;
import amazon_nagp_automation.pages.DeleteAndReAddItemPage;
import amazon_nagp_automation.pages.LoginPage;
import amazon_nagp_automation.pages.NewReleasesPage;
import amazon_nagp_automation.pages.SearchBookPage;
import amazon_nagp_automation.pages.SelectYourAddressPage;
import amazon_nagp_automation.utility.ExtentReport;
import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */
public class SmokeTest extends ExtentReport
{
	private static Logger log = LogManager.getLogger(SmokeTest.class);
	public static String extentTest;

	@Test(description = "Verify LoginPage")
	public void verifyInvalidLogin() throws IOException
	{
		test = extent.createTest("verifyInvalidLogin");
		test.info("Verifying the Login page");
		log.info("Invalid Login verified successfully");
	}

	@Test(description = "Verify Select Your Address")
	public void verifySelectYourAddress() throws Exception
	{
		test = extent.createTest("verifySelectYourAddress");
		test.info("Verifying the Select Address page");
		log.info("Select User Address verified successfully");
	}

	/**
	 * Close the Browser window
	 */
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		log.info("Browser closed successfully");
	}

	/**
	 * Writes test information from the started reporters 
	 */
	@AfterSuite
	public void flushReport()
	{
		extent.flush();
		log.info("extent report created successfully");
	}

}
