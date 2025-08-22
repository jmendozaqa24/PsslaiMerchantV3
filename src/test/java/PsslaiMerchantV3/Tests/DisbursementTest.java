package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.CashOutDisbursementPage;
import PsslaiMerchantV3.PageObject.CashOutWithdrawalPage;
import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class DisbursementTest extends BaseTest {

	@Test(dataProvider="getData", groups = "Regression", description = "Perform Bulkdisbursement Transaction")
	public void bulkDisbursementTransaction(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to CashOut-Disbursement");
		CashOutDisbursementPage disbursement = dashboardPage.goToCashOutMenu_Disbursement();
		logInfo("Click on the Create Bulk Disbursement button");
		disbursement.clickBuldDisbursementButton();
		logInfo("Verify the bulk disbursement fee description");
		disbursement.getDisbursementFeeDscrptn();
		Assert.assertEquals(disbursement.getDisbursementFeeDscrptn(),input.get("bulkDisbursementFeeDscrptn"));
		logInfo("Fill-out the details on Bulk Disbursement form");
		disbursement.bulkDisbursementFillOut();
		disbursement.inputValidation(input.get("userPassword"));
		logInfo("Submit the form");
		disbursement.submitForm();
		logPass("Bulk Disbursement completed successfully!");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\BulkDisbursementTestData.json");
		return new Object[][] { { data.get(0) } };
	}

}
