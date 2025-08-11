package PsslaiMerchantV3.Tests;

import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.CashInDepositPage;
import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class DepositTest extends BaseTest {

	@Test
	public void manualDeposit() {
		DashBoardPage dashboardPage = landingPage.login("psslai-sit@mailinator.com", "Traxion123!");
		CashInDepositPage deposit = dashboardPage.goToCashInMenu_Deposit();
		deposit.manualDeposit();
		
	}


}
