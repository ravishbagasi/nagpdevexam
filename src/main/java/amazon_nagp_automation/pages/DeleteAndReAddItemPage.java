package amazon_nagp_automation.pages;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

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

public class DeleteAndReAddItemPage
{
	public DeleteAndReAddItemPage()
	{

	}

	private static Logger log = LogManager.getLogger(DeleteAndReAddItemPage.class);
	
	@FindBy(how = How.ID, using = "hlb-view-cart")
	WebElement buttonCart;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Delete']")
	WebElement buttonDelete;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Your Amazon Basket')]")
	WebElement textEmptyCart;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@id,'rhfAsinRow')]")
	WebElement sectionDeletedOrder;
	
	/**
	 * Delete and Re-Order the Item		
	 * @param driver
	 * @throws IOException
	 */
	public void deleteItem(WebDriver driver) throws IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		buttonCart.click();
		log.info("Open the cart page");
		wait.until(ExpectedConditions.visibilityOfAllElements(buttonDelete));
		log.info("Cart page opened successfully");
		buttonDelete.click();
		log.info("Delete item button clicked");
		wait.until(ExpectedConditions.visibilityOfAllElements(textEmptyCart));
		log.info("verify cart is empty or not after deleting the item");
		assertEquals("Your Amazon Basket is empty", textEmptyCart.getText().trim());
		Utility util = new Utility();
		wait.until(ExpectedConditions.visibilityOfAllElements(sectionDeletedOrder));
		log.info("Scroll to the Re-Order button");
		util.scrollToElement(driver, sectionDeletedOrder);
		sectionDeletedOrder.click();
		log.info("Re-Order button clicked");
	}


	
}
