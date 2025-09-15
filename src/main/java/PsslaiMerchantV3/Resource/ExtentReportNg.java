package PsslaiMerchantV3.Resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportNg {
	
	static ExtentReports extent;

	public static ExtentReports getReportObject() {

		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "//Reports//reports.html");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("PSSLAI Merchant Webtool");
		reporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Environment", "QA-SIT");
		extent.setSystemInfo("Tester", "Joshua Mendoza");
		extent.attachReporter(reporter);
		return extent;

	}

}
