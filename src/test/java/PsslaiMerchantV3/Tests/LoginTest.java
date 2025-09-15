package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "getData", groups = "Regression", description = "Login with valid credentials")
	public void loginValidCreds(HashMap<String, String> input) {
		logInfo("Starting login with valid credentials");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		Assert.assertTrue(dashboardPage.dashboardLogodisplay(), "The screen should be navigated to dashboard");
		logPass("User successfully logged in and navigated to Dashboard");
	}
	
	@Test(dataProvider ="getData", groups = {"Regression","Negative"}, description = "Login with invalid credencials")
	public void loginwithInvalidCreds(HashMap<String, String> input) {
		logInfo("Starting login with invalid credentials");
		landingPage.login(input.get("invalidUserName"), input.get("invalidPassword"));
		Assert.assertEquals(landingPage.getErrorMessage(), "Authentication error. Invalid credentials. Please try again.");
		logPass("Error Message successfully displayed");
	}
	
	@Test(dataProvider = "getData", groups = {"Regression","Negative"}, description = "Login with incorrect password")
	public void loginwithInvalidPassword(HashMap<String, String> input) {
		logInfo("Starting login with invalid password");
		landingPage.login(input.get("userName"), input.get("invalidPassword"));
		Assert.assertEquals(landingPage.getErrorMessage(), "Authentication error. Invalid credentials. Please try again.");
		logPass("Error Message successfully displayed");
	}
	
	@Test(dataProvider = "getData", groups = {"Regression","Negative"}, description = "Login without credencials")
	public void loginwithoutcredencials(HashMap<String, String> input) {
		logInfo("Starting login without credencials");
		landingPage.login(input.get("emptyusername"), input.get("emptypassword"));
		Assert.assertEquals(landingPage.getErrorMessage(), "Missing or invalid input. Try again.");
		logPass("Error Message successfully displayed");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\logincreds.json");
		return new Object[][] { { data.get(0) } };
	}
}
