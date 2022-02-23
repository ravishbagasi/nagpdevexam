package amazon_nagp_automation.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import amazon_nagp_automation.testclass.BaseClass;

public class ExtentReport extends BaseClass
{
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	static String dirPath = System.getProperty("user.dir") + File.separator + "Current test results";

	/**
	 * Setting and initialize the extent report
	 */
	@BeforeSuite
	public void setUpReport()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH.mm");
		Date date = new Date();
		
		htmlReporter = new ExtentHtmlReporter(dirPath + File.separator + "ExtentReport"+ formatter.format(date) + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Host Name", "Ravish");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Ravish");

		htmlReporter.config().setDocumentTitle("Amazon NAGP");
		htmlReporter.config().setReportName("Amazon NAGP");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	/**
	 * Gert the Result of the Test Case and put it in the report
	 * @param result
	 * @throws IOException
	 */
	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			Utility util = new Utility();
			String fileName = util.findFile(dirPath, result.getName());

			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.log(Status.FAIL,
					"Test Caase Failed due to: " + test.addScreenCaptureFromPath(dirPath + File.separator + fileName));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else
		{
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}
}
