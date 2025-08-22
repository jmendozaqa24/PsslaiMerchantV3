package PsslaiMerchantV3.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.PageObject.MemberPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class MemberTest extends BaseTest {

	@Test(dataProvider="getData", groups = "Regression", description = "View member list in UI")
	public void viewMemberList(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to Member Menu");
		MemberPage memberPage = dashboardPage.goToMemberMenu();
		Assert.assertEquals("Members", memberPage.verifyMemberPageTitle());
		logPass("User successfully navigated to Member Menu");
	}

	@Test(dataProvider="getData", groups = "Regression", description = "Download member list successfully")
	public void downloadMemberList(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to Member Menu");
		MemberPage memberPage = dashboardPage.goToMemberMenu();
		logInfo("Download List of Members");
		memberPage.downloadMemberList();
		Assert.assertEquals(memberPage.getSuccessMsg(), input.get("successMsg"));
		logPass("Downloaded Successfully");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\MemberTestData.json");
		return new Object[][] { { data.get(0) } };
	}

}
