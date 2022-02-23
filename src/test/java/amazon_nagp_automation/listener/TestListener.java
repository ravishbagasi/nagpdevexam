package amazon_nagp_automation.listener;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import amazon_nagp_automation.testclass.BaseClass;
import amazon_nagp_automation.utility.Utility;

public class TestListener extends BaseClass implements ITestListener
{
	public void onTestStart(ITestResult result)
	{

		try
		{
			init();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Invoked each time a test fails.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	public void onTestFailure(ITestResult result)
	{
		System.out.println("Failed Test " + result.getMethod().getMethodName());
		failed(result.getMethod().getMethodName());
	}

	/**
	 * Invoked before running all the test methods belonging to the classes inside
	 * the &lt;test&gt; tag and calling all their Configuration methods.
	 *
	 * @param context The test context
	 */
	public void onStart(ITestContext context)
	{
		String dirPath = System.getProperty("user.dir");

		Utility.moveData(dirPath + File.separator + "Current test results",
				dirPath + File.separator + "Archived test results");

		System.out.println("Current test results Folder has cleaned");
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * &lt;test&gt; tag have run and all their Configuration methods have been
	 * called.
	 *
	 * @param context The test context
	 */
	public void onFinish(ITestContext context)
	{
		System.out.println("onFinish");
	}

}
