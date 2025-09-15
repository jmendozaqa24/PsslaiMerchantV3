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
	
	@Test(dataProvider = "getData", groups = "Regression", description="Search Member from Member List")
	public void searchMember(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to Member Menu");
		MemberPage memberPage = dashboardPage.goToMemberMenu();
		logInfo("Perform Member Search");
		String resultMember = memberPage.getSearchResultMember(input.get("firstName"));
		Assert.assertEquals(input.get("fullName"), resultMember, "Name does not match");
		logPass("Successfully searched a member");
	}
	
	@Test(dataProvider = "getData", groups = "Regression", description="View member details")
	public void viewMemberDetails(HashMap<String, String> input) {
		logInfo("Login to PSSLAI Web");
		DashBoardPage dashboardPage = landingPage.login(input.get("userName"), input.get("userPassword"));
		logInfo("Navigate to Member Menu");
		MemberPage memberPage = dashboardPage.goToMemberMenu();
		logInfo("Click Member View Details");
		String pageTitle = memberPage.clickUser(input.get("firstName"));
		Assert.assertEquals("Member Details", pageTitle, "Page title doesn't match");
		logPass("Member View Details is successfully displayed");
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\PsslaiMerchantV3\\Data\\MemberTestData.json");
		return new Object[][] { { data.get(0) } };
	}

}
