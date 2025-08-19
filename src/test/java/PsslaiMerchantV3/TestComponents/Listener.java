package PsslaiMerchantV3.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PsslaiMerchantV3.Resource.ExtentReportNg;

public class Listener extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportNg.getReportObject();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // ✅ Thread safe

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // ✅ store per-thread test object
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//extentTest.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String filePath = getScreenShot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
