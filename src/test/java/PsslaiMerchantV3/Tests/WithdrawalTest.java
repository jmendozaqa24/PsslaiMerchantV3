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

	@Test(dataProvider = "getData", groups = "Regression", description = "Withdraw via Instapay (linked accounts)")
	public void withdrawalInstapayLinkedAccounts(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashOut-Withdrawal");
		CashOutWithdrawalPage withdrawal = dashboardPage.goToCashOutMenu_Withdrawal();
		logInfo("Starting to perform Withdraw Instapay with Linked Account");
		withdrawal.performWithdrawInspayLinkedAccount(input.get("bankTypeNum"), input.get("amount"), input.get("userPassword"));
		logInfo("Verify Success Toast Message is dislayed");
		Assert.assertTrue(withdrawal.getSuccessToastMsg()); 
		logPass("Withdrawal Fund completed successfully");

	}

	@Test(dataProvider = "getData", groups = "Regression", description = "Perform withdrawal via Instapay")
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
		withdrawal.withdrawalFundsFillOutNoLinkedAccounts(input.get("bankTypeNumNL"), input.get("accountName"),
				input.get("accountNumber"), input.get("amount"));
		withdrawal.inputValidation(input.get("userPassword"));
		logInfo("Submit the form");
		withdrawal.submitWithdrawFunds();
		logInfo("Verify Success Toast Message is dislayed");
		Assert.assertTrue(withdrawal.getSuccessToastMsg());
		logPass("Withdrawal Fund completed successfully");
	}

	@Test(dataProvider = "getData", groups = "Regression", description = "Withdraw via pesonet (linked accounts)")
	public void withdrawalPesonetLinkedAccounts(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashOut-Withdrawal");
		CashOutWithdrawalPage withdrawal = dashboardPage.goToCashOutMenu_Withdrawal();
		logInfo("Click on the Withdraw Funds button");
		withdrawal.clickWithdrawFundsButton();
		withdrawal.pisonetTransactionType();
		logInfo("Verify that the Linked Account toggle is on");
		Assert.assertTrue(withdrawal.linkedAccountsToggle());
		logInfo("Fillout the details on Withdrawal Form");
		withdrawal.withdrawalFundsFillOutLinkedAccounts(input.get("bankTypeNum"), input.get("amount"));
		withdrawal.inputValidation(input.get("userPassword"));
		logInfo("Submit the form");
		withdrawal.submitWithdrawFunds();
		logInfo("Verify Success Toast Message is dislayed");
		Assert.assertTrue(withdrawal.getSuccessToastMsg());
		logPass("Withdrawal Fund completed successfully");

	}

	@Test(dataProvider = "getData", groups = "Regression", description = "Perform withdrawal via Pesonet")
	public void withdrawalPesonet(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashOut-Withdrawal");
		CashOutWithdrawalPage withdrawal = dashboardPage.goToCashOutMenu_Withdrawal();
		logInfo("Click on the Withdraw Funds button");
		withdrawal.clickWithdrawFundsButton();
		withdrawal.pisonetTransactionType();
		withdrawal.linkedAccountsToggleOff();
		logInfo("Verify that the Linked Account toggle is off");
		Assert.assertFalse(withdrawal.linkedAccountsToggle());
		logInfo("Fillout the details on Withdrawal Form");
		withdrawal.withdrawalFundsFillOutNoLinkedAccounts(input.get("bankTypeNumNL"), input.get("accountName"),
				input.get("accountNumber"), input.get("amount"));
		withdrawal.inputValidation(input.get("userPassword"));
		logInfo("Submit the form");
		withdrawal.submitWithdrawFunds();
		logInfo("Verify Success Toast Message is dislayed");
		Assert.assertTrue(withdrawal.getSuccessToastMsg());
		logPass("Withdrawal Fund completed successfully");

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\WithdrawalTestData.json");
		return new Object[][] { { data.get(0) } };
	}

}
