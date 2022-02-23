package amazon_nagp_automation.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility
{
	/**
	 * Select value in dropdown by element text
	 * 
	 * @param locator
	 * @param text
	 */
	public void selectValueInDropdownByText(WebElement ele, String text)
	{
		Select jobType = new Select(ele);
		jobType.selectByVisibleText(text);
	}

	/**
	 * Focus on web element through javascript
	 * 
	 * @param driver
	 * @param ele
	 */
	public void focusOnElement(WebDriver driver, WebElement ele)
	{
		String mouseOverScript = "if(document.createEvent)"
				+ "{var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} "
				+ "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, ele);
	}

	/**
	 * This will scroll the page till the element is found
	 * @param driver
	 * @param ele
	 */
	public void scrollToElement(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	/*
	 * This will mouse over to the element
	 * @param driver
	 * @param ele
	 */
	public void hoverToElement(WebDriver driver, WebElement ele)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
	}

	/**
	 * Clean the dir
	 * 
	 * @param dir
	 */
	public static void cleanupDirectory(File dir)
	{
		for (File file : dir.listFiles())
		{
			if (file.isDirectory())
				cleanupDirectory(file);
			file.delete();
		}
	}

	/**
	 * Move the data from a dir to another dir 
	 * @param src
	 * @param destination
	 */
	public static void moveData(String src, String destination)
	{
		File source = new File(src);
		File dest = new File(destination);
		try
		{
			FileUtils.copyDirectory(source, dest);
			cleanupDirectory(source);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Switch between the window
	 * @param driver
	 */
	public static void swithToWindow(WebDriver driver)
	{
		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		// switch to the user provided tab
		driver.switchTo().window(tab.get(1));
	}

	/**
	 * Find the file in a dir
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public String findFile(String dir, String fileName)
	{
		String name = null;
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles)
		{
			if (file.isFile())
			{
				if (file.getName().contains(fileName))
				{
					// name = file.getName().substring(0, file.getName().indexOf(".xlsx"));
					name = file.getName();
					break;
				}
			}
		}
		return name;
	}
}
