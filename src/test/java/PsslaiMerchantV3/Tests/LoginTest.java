package PsslaiMerchantV3.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class LoginTest extends BaseTest{

	@Test
	public void loginValidCreds() {
		DashBoardPage dashboardPage = landingPage.login("psslai-sit@mailinator.com", "Traxion123!");
		Assert.assertTrue(dashboardPage.dashboardLogodisplay());
	}
}
