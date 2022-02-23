package amazon_nagp_automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon_nagp_automation.testclass.BaseClass;
import amazon_nagp_automation.utility.Utility;
import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */
public class ChangeLanguagePage
{
	public ChangeLanguagePage()
	{

	}

	private static Logger log = LogManager.getLogger(ChangeLanguagePage.class);

	@FindBy(how = How.XPATH, using = "//span[@class='icp-nav-flag icp-nav-flag-in']")
	WebElement iconCountryFlag;

	@FindBy(how = How.XPATH, using = "//div[@id='nav-flyout-icp' and (contains(@style,'display: block'))]")
	WebElement tooltipLanguageChange;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Language Settings')]")
	WebElement breadcrumbChangeLanguageSetting;

	@FindBy(how = How.ID, using = "icp-btn-save")
	WebElement buttonSave;

	@FindBy(how = How.XPATH, using = "//div[@class='a-carousel-col a-carousel-right celwidget']")
	WebElement bannerHomePage;

	@FindBy(how = How.XPATH, using = "//div[@id='nav-flyout-icp']//i[@class='icp-radio icp-radio-active']//ancestor::span[@class='nav-text']")
	WebElement labelSelectedLanguage;

	/**
	 * Verify the Language Change for the user
	 * @param driver
	 * @throws Exception
	 */
	public void changeLanguage(WebDriver driver) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		wait.until(ExpectedConditions.visibilityOfAllElements(iconCountryFlag));
		log.info("Verified language change country flag icon");
		Utility util = new Utility();
		util.hoverToElement(driver, iconCountryFlag);
		log.info("Mouseover on the country flag icon");
		wait.until(ExpectedConditions.visibilityOfAllElements(tooltipLanguageChange));
		iconCountryFlag.click();
		log.info("country flag icon clicked");
		wait.until(ExpectedConditions.visibilityOfAllElements(breadcrumbChangeLanguageSetting));
		log.info("Verified language change page");

		// select the desired language
		WebElement language = driver.findElement(
				By.xpath("//div[@class='a-column a-span7']//span[contains(text(),'"
						+ BaseClass.readProperties("language") + "')]"));
		language.click();
		log.info("Desired language radio button clicked");

		buttonSave.click();
		log.info("Save button clicked");

		wait.until(ExpectedConditions.visibilityOfAllElements(bannerHomePage));
		log.info("Verified home page");

		util.hoverToElement(driver, iconCountryFlag);
		log.info("Mouseover on the country flag icon");
		wait.until(ExpectedConditions.visibilityOfAllElements(tooltipLanguageChange));
		Thread.sleep(2000);
		if (!labelSelectedLanguage.getText().contains(BaseClass.readProperties("language")))
			throw new Exception("Desired Selected not Seleted");

		log.info("Desired language has selected successfully");

	}

}
