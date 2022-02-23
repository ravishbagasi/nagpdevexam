package amazon_nagp_automation.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon_nagp_automation.testclass.BaseClass;
import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */
public class LoginPage
{
	public static String extentTest;

	public LoginPage()
	{
	}
	
	private static Logger log = LogManager.getLogger(LoginPage.class);

	@FindBy(how = How.XPATH, using = "//a[@data-csa-c-content-id='nav_ya_signin']")
	WebElement buttonSignIn;

	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Sign-In')]")
	WebElement titleSignIn;

	@FindBy(how = How.ID, using = "ap_email")
	WebElement textboxEmail;

	@FindBy(how = How.ID, using = "continue")
	WebElement buttonContinue;

	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'There was a problem')]")
	WebElement errorMsgInvalidEmail;

	/**
	 * Verify invalid login
	 * @param driver
	 * @throws IOException
	 */
	public void verifyInvalidLogin(WebDriver driver) throws IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		wait.until(ExpectedConditions.visibilityOfAllElements(buttonSignIn));
		buttonSignIn.click();
		log.info("Verified and clicked the Sign In button");
		wait.until(ExpectedConditions.visibilityOfAllElements(titleSignIn));
		log.info("Sign In page opened successfully");
		verifyInvalidEmail(wait);
	}

	/**
	 * Provide invalid email for invalid login
	 * @param wait
	 * @throws IOException
	 */
	public void verifyInvalidEmail(WebDriverWait wait) throws IOException
	{
		log.info("enter the email: "+ BaseClass.readProperties("email"));
		textboxEmail.sendKeys(BaseClass.readProperties("email"));
		buttonContinue.click();
		log.info("Click Continue button");
		wait.until(ExpectedConditions.visibilityOf(errorMsgInvalidEmail));
		log.info("Invalid login message verified successfully");
	}
}
