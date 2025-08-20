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

	@Test(dataProvider = "getData", groups = "Regression")
	public void manualDeposit(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashIn-Deposit");
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		logInfo("Click on the Make Deposit Button");
		deposit.clickMakeDepositButton();
		logInfo("Fill-out the details in Manual Deposit Form");
		String manualGeneratedRef = deposit.manualDepositFillOut("1000.00", "BDO_UNIBANK");
		logInfo("Submit the form");
		deposit.submitManualForm();
		String ref = deposit.getTransactionRef();
		Assert.assertEquals(manualGeneratedRef, ref);
		System.out.println("Transaction Ref: " + ref);
		logPass("Manual deposit completed successfully! Transaction Ref: " + ref);

	}

	@Test(dataProvider = "getData", dependsOnMethods = "manualDeposit", groups = "Regression")
	public void verifyManualDepositTransaction(HashMap<String, String> input) throws InterruptedException {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashIn-Deposit");
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		logInfo("Verify transaction");
		deposit.selectPendingStatus();
		Assert.assertTrue(deposit.selectPendingStatus());
		deposit.searchTransaction();
		Assert.assertEquals(deposit.getResult(), CashInDepositPage.refNum);
		logPass("Transaction is reflected in Pending status");
	}

	@Test(dataProvider = "getData", groups = "Regression")
	public void onlineDeposit(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashIn-Deposit");
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		logInfo("Click on the Make Deposit Button");
		deposit.clickMakeDepositButton();
		logInfo("Fill-out the details in Online Deposit Form");
		deposit.onlineDepositFillOut("1000.00", "2", "36823");
		logInfo("Submit the form");
		deposit.submitOnlineForm();
		logInfo("Transact using Test Bank");
		deposit.transactBogusBank("a", "a");
		Assert.assertEquals(deposit.getSuccessMessage(), "Payment Successful!");
		logPass("Online deposit completed successfully");
	}
	
	@Test(dataProvider="getData", dependsOnMethods="onlineDeposit", groups ="Regression")
	public void verifyOnlineDepositTransaction(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashIn-Deposit");
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		logInfo("Verify transaction");
		deposit.selectPendingStatus();
		Assert.assertTrue(deposit.selectPendingStatus());
		deposit.searchTransaction();
		Assert.assertEquals(deposit.getResult(), CashInDepositPage.refNum);
		logPass("Transaction is reflected in Pending status");
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\DepositTestData.json");
		return new Object[][] { { data.get(0) } };
	}

}
