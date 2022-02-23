package amazon_nagp_automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon_nagp_automation.utility.Utility;
import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */
public class AmazonPrimePage
{
	public AmazonPrimePage()
	{

	}
	
	private static Logger log = LogManager.getLogger(AmazonPrimePage.class);

	@FindBy(how = How.ID, using = "nav-link-prime")
	WebElement linkAmazonPrime;

	@FindBy(how = How.XPATH, using = "//a[@href='/prime?ref=in-pr-tryprime-unrec-multi-benefit']")
	WebElement bannerAmazonPrime;

	@FindBy(how = How.ID, using = "a-autoid-0")
	WebElement buttonJoinAmazonPrime;

	/**
	 * Verify the Login for Amazon Prime
	 * @param driver
	 */
	public void verifyAmazonPrimeLogin(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		wait.until(ExpectedConditions.visibilityOfAllElements(linkAmazonPrime));
		log.info("Amazon Prime link verified");
		Utility util = new Utility();
		util.hoverToElement(driver, linkAmazonPrime);
		log.info("Mouseover on the Prime link");
		wait.until(ExpectedConditions.visibilityOfAllElements(bannerAmazonPrime));
		bannerAmazonPrime.click();
		log.info("Amazon Prime Page opened");
		wait.until(ExpectedConditions.visibilityOfAllElements(buttonJoinAmazonPrime));
		buttonJoinAmazonPrime.click();
		log.info("Verified Amazon Prime join");
	}

}
