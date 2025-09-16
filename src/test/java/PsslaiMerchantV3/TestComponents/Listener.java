package PsslaiMerchantV3.TestComponents;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import PsslaiMerchantV3.Resource.ExtentReportNg;
import PsslaiMerchantV3.Resource.GChatNotifier;

public class Listener extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportNg.getReportObject();
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // ✅ Thread safe

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getDescription());
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    
		String filePath=null;
		
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    try {
	        extentTest.get().fail("Screenshot of failure",
	                MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
	    } catch (Exception el) {
	        el.printStackTrace();
	    }
	    
	    
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().skip("Test Skipped: " + result.getThrowable());
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    LocalDateTime startDateTime = context.getStartDate().toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDateTime();
	    LocalDateTime endDateTime = context.getEndDate().toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDateTime();

	    String startDate = startDateTime.format(formatter);
	    String endDate = endDateTime.format(formatter);

	    long durationInSeconds = Duration.between(startDateTime, endDateTime).getSeconds();
	    String duration = String.format("%d min %d sec", durationInSeconds / 60, durationInSeconds % 60);

	    int passed = context.getPassedTests().size();
	    int failed = context.getFailedTests().size();
	    int skipped = context.getSkippedTests().size();

	    // ✅ Local or server path of ExtentReport HTML
	    String reportUrl = "http://localhost:8080/reports/extent.html"; 
	    // Or a file share / Google Drive / CI artifact link

	    // Send report with button
	    GChatNotifier.sendExecutionReport(startDate, endDate, duration, passed, failed, skipped, reportUrl);
	}

}
