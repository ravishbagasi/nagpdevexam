package amazon_nagp_automation.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */

public class NewReleasesPage
{
	public NewReleasesPage()
	{

	}

	private static Logger log = LogManager.getLogger(NewReleasesPage.class);

	@FindBy(how = How.XPATH, using = "//span[@class='hm-icon-label']")
	WebElement dropdownAll;

	@FindBy(how = How.XPATH, using = "//ul[@class='hmenu hmenu-visible']//a[contains(text(),'New Releases')]")
	WebElement linkNewReleases;

	@FindBy(how = How.ID, using = "imageBlock")
	WebElement imgItem;

	@FindBy(how = How.XPATH, using = "//div[@class='a-section a-spacing-mini']")
	WebElement sectionNewReleasesItems;

	@FindBy(how = How.ID, using = "nav-cart-count")
	WebElement lableCartCount;

	/**
	 * Open the New Release Page
	 * @param driver
	 */
	public void openNewReleasePage(WebDriver driver)
	{
		log.info("Opening the New Release Page");
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		wait.until(ExpectedConditions.visibilityOfAllElements(dropdownAll));
		log.info("Clicking the All Dropdown");
		dropdownAll.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(linkNewReleases));
		log.info("Clicking the New Releases link");
		linkNewReleases.click();
	}

	/**
	 * Add New Item(s) into the cart
	 * @param driver
	 * @throws Exception
	 */
	public void addNewReleaseItemsInCart(WebDriver driver) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		log.info("Fetch the New Releases Page URL");
		String url = driver.getCurrentUrl();
		List<WebElement> items = driver.findElements(By.xpath(
				"//div[@class='a-section a-spacing-mini']"));

		int itemsCount = items.size();
		log.info("The total no. of items present in New Releases Page is : " + itemsCount);
		AddToCartPage addToCart = PageFactory.initElements(driver, AddToCartPage.class);

		int avoidedItemsNo = 0;
		for (int i = 1; i <= itemsCount; i++)
		{
			log.info("Add item in add to cart");
			driver.findElement(By.xpath("(//div[@class='a-section a-spacing-mini']/img)[" + i + "]")).click();
			wait.until(ExpectedConditions.visibilityOfAllElements(imgItem));

			if (driver.findElements(By.xpath("//input[@id='add-to-cart-button']")).size() < 1) // If New Release item
																								// doesn't has the add
																								// to cart button then ignore that item
			{
				avoidedItemsNo++;
				driver.navigate().to(url);
				continue;
			} else
			{
				addToCart.addToCartItem(wait);
				log.info("Navigate back to the New Release Page");
				driver.navigate().to(url);
			}
		}

		assertEquals(itemsCount - avoidedItemsNo, Integer.parseInt(lableCartCount.getText()));
		log.info("All the items of New Releases Page are added into cart");
	}

}
