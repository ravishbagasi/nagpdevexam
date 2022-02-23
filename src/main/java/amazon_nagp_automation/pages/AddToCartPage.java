package amazon_nagp_automation.pages;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon_nagp_automation.utility.Utility;
import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */
public class AddToCartPage
{
	public AddToCartPage()
	{
	}
	
	private static Logger log = LogManager.getLogger(AddToCartPage.class);
	
	@FindBy(how = How.XPATH, using = "//div[@data-index=1]//span[@class='a-price-whole']")
	static WebElement productPrice;

	@FindBy(how = How.ID, using = "add-to-cart-button")
	static WebElement buttonAddToCart;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Added to Cart')]")
	WebElement textAddedToCart;

	@FindBy(how = How.ID, using = "hlb-ptc-btn-native")
	static WebElement buttonProceed;

	/**
	 * Add and verify the item into the cart 
	 * @param driver
	 * @throws IOException
	 */
	public void addToCart(WebDriver driver) throws IOException
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		
		log.info("Adding Item in the cart");
		SearchBookPage search = PageFactory.initElements(driver, SearchBookPage.class);
		search.searchBook(wait);
		log.info("Click and Open the Item");
		productPrice.click();

		log.info("Switch to Item page");
		Utility.swithToWindow(driver);

		addToCartItem(wait);
	}

	/**
	 * Add the item into the cart
	 * @param wait
	 */
	public void addToCartItem(WebDriverWait wait)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(buttonAddToCart));
		buttonAddToCart.click();
		log.info("Add To Cart button verified and clicked");
		wait.until(ExpectedConditions.visibilityOfAllElements(textAddedToCart));
		assertEquals("Added to Cart", textAddedToCart.getText().trim());
		log.info("Item added into cart successfully");
	}

	/**
	 * Click the Procced To Buy button
	 * @param wait
	 */
	public void proceedToBuy(WebDriverWait wait)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(buttonProceed));
		buttonProceed.click();
		log.info("Proceed to Buy button verified and clicked");
	}
}

