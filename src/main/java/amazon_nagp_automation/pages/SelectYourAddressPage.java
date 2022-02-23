package amazon_nagp_automation.pages;

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
public class SelectYourAddressPage
{
	public SelectYourAddressPage()
	{
	}
	
	private static Logger log = LogManager.getLogger(SelectYourAddressPage.class);

	@FindBy(how = How.ID, using = "glow-ingress-line2")
	WebElement linkAddress;

	@FindBy(how = How.XPATH, using = "//h4[contains(text(),'Choose your location')]")
	WebElement titlePopup;

	@FindBy(how = How.ID, using = "GLUXZipUpdateInput")
	WebElement textboxPincode;
	
	@FindBy(how = How.ID, using = "GLUXZipUpdate")
	WebElement buttonApply;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Please enter a valid pincode')]")
	WebElement errorMsgInvalidPincode;
	
	/**
	 * Select the address
	 * @param driver
	 * @throws Exception
	 */
	public void verifySelectYourAddress(WebDriver driver) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		wait.until(ExpectedConditions.visibilityOfAllElements(linkAddress));
		linkAddress.click();
		log.info("Verified at clicked the address link");

		driver.switchTo().frame(1);
		log.info("Switched to the new window");
		
		wait.until(ExpectedConditions.visibilityOfAllElements(titlePopup));
		log.info("verified the address page");
		
		textboxPincode.sendKeys("111111");
		buttonApply.click();
		log.info("Invalid Pincode entered :" + 111111+ " and clicked the apply button");
		wait.until(ExpectedConditions.visibilityOfAllElements(errorMsgInvalidPincode));
		log.info("verified the invalid Pincode error message");
		
		textboxPincode.clear();
		textboxPincode.sendKeys(BaseClass.readProperties("pincode"));
		buttonApply.click();
		log.info("Valid Pincode entered :" + BaseClass.readProperties("pincode")+ " and clicked the apply button");
		
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();
		log.info("Switch back to the home page");
		
		if(linkAddress.getText().contains("New Delhi 110023‌"))
			log.info("Address has been selected");
		else
			throw new Exception("Address Didn't match");	
	}

}
