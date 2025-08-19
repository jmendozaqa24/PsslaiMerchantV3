package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.CashOutWithdrawalPage;
import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class WithdrawalTest extends BaseTest {

	@Test(dataProvider = "getData", groups = "Regression")
	public void withdrawalInstapayLinkedAccounts(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashOut-Withdrawal");
		CashOutWithdrawalPage withdrawal = dashboardPage.goToCashOutMenu_Withdrawal();
		logInfo("Click on the Withdraw Funds button");
		withdrawal.clickWithdrawFundsButton();
		logInfo("Verify that the Linked Account toggle is on");
		Assert.assertTrue(withdrawal.linkedAccountsToggle());
		logInfo("Fillout the details on Withdrawal Form");
		withdrawal.withdrawalFundsFillOutLinkedAccounts("26", "1000.00");
		withdrawal.inputValidation(input.get("userPassword"));
		logInfo("Submit the form");
		withdrawal.submitWithdrawFunds();
		logInfo("Verify Success Toast Message is dislayed");
		Assert.assertTrue(withdrawal.getSuccessToastMsg());
		logPass("Withdrawal Fund completed successfully");

	}
	
	@Test(dataProvider="getData", groups = "Regression")
	public void withdrawalInstapay(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashOut-Withdrawal");
		CashOutWithdrawalPage withdrawal = dashboardPage.goToCashOutMenu_Withdrawal();
		logInfo("Click on the Withdraw Funds button");
		withdrawal.clickWithdrawFundsButton();
		withdrawal.linkedAccountsToggleOff();
		logInfo("Verify that the Linked Account toggle is off");
		Assert.assertFalse(withdrawal.linkedAccountsToggle());
		logInfo("Fillout the details on Withdrawal Form");
		withdrawal.withdrawalFundsFillOutNoLinkedAccounts("2061", "QA Test BDO", "003920087172", "1000.00");
		withdrawal.inputValidation(input.get("userPassword"));
		logInfo("Submit the form");
		withdrawal.submitWithdrawFunds();
		logInfo("Verify Success Toast Message is dislayed");
		Assert.assertTrue(withdrawal.getSuccessToastMsg());
		logPass("Withdrawal Fund completed successfully");
	}
	
	@Test(dataProvider = "getData", groups = "Regression")
	public void withdrawalPesonetLinkedAccounts(HashMap<String, String> input) {

	}

	public void withdrawalPesonet() {

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\DepositTestData.json");
		return new Object[][] { { data.get(0) } };
	}

}
