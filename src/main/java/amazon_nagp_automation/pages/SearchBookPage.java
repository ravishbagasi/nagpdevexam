package amazon_nagp_automation.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import amazon_nagp_automation.testclass.BaseClass;
import amazon_nagp_automation.utility.WaitInterval;

/**
 * 
 * @author ravishbagasi
 *
 */
public class SearchBookPage
{
	public SearchBookPage()
	{
	}
	
	private static Logger log = LogManager.getLogger(SearchBookPage.class);

	@FindBy(how = How.XPATH, using = "//span[@class='a-price-whole']")
	WebElement tagPrice;

	@FindBy(how = How.XPATH, using = "//select[@aria-describedby='searchDropdownDescription']")
	WebElement dropdownSearchList;

	@FindBy(how = How.ID, using = "glow-ingress-line2")
	WebElement linkAddress;

	@FindBy(how = How.ID, using = "twotabsearchtextbox")
	WebElement textboxSearch;

	@FindBy(how = How.ID, using = "nav-search-submit-button")
	WebElement buttonSearch;

	/**
	 * Verify the Search for the user
	 * @param driver
	 * @throws Exception
	 */
	public void verifyBookSearch(WebDriver driver) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, WaitInterval.ThirtySecond.getSeconds());
		searchBook(wait);

		log.info("fetch list of all the items visible on the page");
		List<WebElement> allLinks = driver.findElements(By.xpath(
				"//span[@class='a-price-whole']"));

		log.info("verify price tag for the items");
		Iterator<WebElement> itr = allLinks.iterator();
		while (itr.hasNext())
		{
			//verifying price tag for every item
			if(itr.next().getText() == null)
				throw new Exception("search is Not working");
		}
		log.info("verifed price tag for all the items");
	}

	/**
	 * Search the book or item
	 * @param wait
	 * @throws IOException
	 */
	public void searchBook(WebDriverWait wait) throws IOException
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(linkAddress));

		log.info("select Books from the list of items");
		Select items = new Select(dropdownSearchList);
		items.selectByVisibleText(BaseClass.readProperties("itemToSearch"));
		log.info("Enter the book name in the search box :"+ BaseClass.readProperties("bookName"));
		textboxSearch.sendKeys(BaseClass.readProperties("bookName"));
		buttonSearch.click();
		log.info("Search button clicked");
		wait.until(ExpectedConditions.visibilityOfAllElements(tagPrice));
		log.info("Item List page verified");
	}

}
