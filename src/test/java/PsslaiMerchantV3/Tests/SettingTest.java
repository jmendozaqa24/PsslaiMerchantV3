package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.PageObject.SettingsPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class SettingTest extends BaseTest {

	@Test(dataProvider = "getData", groups = "Regression", description = "View My Account Details")
	public void viewMyAccount(HashMap<String, String> input) {
		logInfo("Starting login with valid credentials");
		DashBoardPage dashBoardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to Settings");
		SettingsPage settingsPage = dashBoardPage.goToSettingsMenu();
		logInfo("Click on the My Account");
		settingsPage.viewMyAccount();
		Assert.assertEquals(settingsPage.getTitleCard(), "My Account", "Expected Card Title doesn't match" );
		logPass("My Account Details successfully displayed");
	}
	
	@Test(dataProvider = "getData", groups = "Regression", description = "View Merchant Details")
	public void viewMerchantDetails(HashMap<String, String> input) {
		DashBoardPage dashBoardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		SettingsPage settingsPage = dashBoardPage.goToSettingsMenu();
		settingsPage.viewMerchantDetails();
		Assert.assertEquals(settingsPage.getTitleCard(), "Merchant Details", "Expected Card Title doesn't match" );
		
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\logincreds.json");
		return new Object[][] { { data.get(0) } };
	}
}
