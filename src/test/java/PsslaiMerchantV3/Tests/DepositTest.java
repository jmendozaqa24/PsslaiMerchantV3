package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.CashInDepositPage;
import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class DepositTest extends BaseTest {

	@Test(dataProvider = "getData" , groups = "Regression")
	public void manualDeposit(HashMap<String, String> input) {
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		deposit.clickMakeDepositButton();
		String manualGeneratedRef = deposit.manualDepositFillOut();
		String refNum = deposit.getTransactionRef();
		Assert.assertEquals(manualGeneratedRef, refNum);
		
	}

	@Test(dataProvider = "getData" , dependsOnMethods = "manualDeposit", groups = "Regression")
	public void verifyManualDepositTransaction(HashMap<String, String> input) {
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		deposit.checkTransactionPending();
	}
	
	@Test(dataProvider = "getData" , groups = "Regression")
	public void onlineDeposit(HashMap<String, String> input) {
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		deposit.clickMakeDepositButton();
		deposit.onlineDepositFillOut();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\PsslaiMerchantV3\\Data\\DepositTestData.json");
		return new Object[][] {{data.get(0)}};
	}
	

}
