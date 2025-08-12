package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class LoginTest extends BaseTest{

	@Test(dataProvider = "getData")
	public void loginValidCreds(HashMap<String,String> input) {
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		Assert.assertTrue(dashboardPage.dashboardLogodisplay());
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\PsslaiMerchantV3\\Data\\logincreds.json");
		return new Object[][] {{data.get(0)}};
	}
}
