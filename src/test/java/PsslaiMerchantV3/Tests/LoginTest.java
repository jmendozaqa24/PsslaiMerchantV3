package PsslaiMerchantV3.Tests;

import org.testng.annotations.Test;

import PsslaiMerchantV3.TestComponents.BaseTest;

public class LoginTest extends BaseTest{

	@Test
	public void loginValidCreds() {
		landingPage.login("psslai-sit@mailinator.com", "Traxion123!");
	}
}
