package PsslaiMerchantV3.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import PsslaiMerchantV3.PageObject.DashBoardPage;
import PsslaiMerchantV3.PageObject.MemberPage;
import PsslaiMerchantV3.TestComponents.BaseTest;

public class MemberTest extends BaseTest{
	
	@Test
	public void viewMemberList() {
		DashBoardPage dashboardPage = landingPage.login("psslai-sit@mailinator.com", "Traxion123!");
		MemberPage memberPage = dashboardPage.goToMemberMenu();
		Assert.assertEquals("Members", memberPage.verifyMemberPageTitle());
		
		
	}

}
