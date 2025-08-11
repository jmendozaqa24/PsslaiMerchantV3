package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class MemberPage extends AbstractComponents{
	
	WebDriver driver;
	
	public MemberPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".page-header")WebElement memberPageTitle;
	@FindBy(id= "download-list-btn") WebElement memberDownload;
	@FindBy(css=".toast-body")WebElement successToastMessage;
	
	
	public String verifyMemberPageTitle() {
		waitForElementToAppear(memberPageTitle);
		String memberTitle = memberPageTitle.getText();
		return memberTitle;
	}
	
	public void downloadMemberList() {
		waitForElementToAppear(memberDownload);
		memberDownload.click();
	}
	
	public String getSuccessMsg() {
		waitForElementToAppear(successToastMessage);
		return getSuccessMessageText(successToastMessage);
	}
	
	

}
