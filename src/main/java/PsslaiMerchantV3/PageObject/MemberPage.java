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
	@FindBy(xpath = "//input[@id='dt-search-0']") WebElement searchBar;
	@FindBy(xpath = "//tbody/tr/td[1]") WebElement getResultTopOne;
	@FindBy(css=".btn-view-details") WebElement viewDetailsBtn;
	@FindBy(css=".dtr-data") WebElement viewDetailsInsideBtn;
	@FindBy(xpath="//h2") WebElement pageTitle;
	@FindBy(id="advance-search") WebElement advancedSearchBtn;
	@FindBy(id="advanceSearchPanel") WebElement advancedSearchPanel;
	@FindBy(name="fullName") WebElement fullNameInpt;
	@FindBy(name="status") WebElement statusDp;
	@FindBy(name="walletAccount") WebElement walletAccountInpt;
	@FindBy(id="applySearchBtn") WebElement applySearchBtn;
	@FindBy(css=".toast-body") WebElement successToastMsg;
	
	
	public void outsideSearch(String firstName) {
		searchBar.sendKeys(firstName);
		
	}
	
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
	
	public String getAdvancedSearchSuccessMsg() {
		waitForElementToAppear(successToastMsg);
		return successToastMsg.getText();
	}
	
	public void searchMember(String fName) {
		waitForElementToAppear(searchBar);
		searchBar.sendKeys(fName);
	}
	
	public String getSearchResultMember(String fName) throws InterruptedException {
		searchMember(fName);
		Thread.sleep(2000);
		waitForElementToAppear(getResultTopOne);
		return getResultTopOne.getText();
	}
	
	public String clickUser(String fName) throws InterruptedException {
		getSearchResultMember(fName);
		getResultTopOne.click();
		waitForElementToAppear(viewDetailsBtn);
		viewDetailsBtn.click();
		waitForElementToAppear(pageTitle);
		return pageTitle.getText();
	}
	
	public String getAdvancedSearchMemberFullName(String name) {
		waitForElementToAppear(advancedSearchBtn);
		advancedSearchBtn.click();
		waitForElementToAppear(advancedSearchPanel);
		memberName(name);
		applySearchBtn.click();
		waitForElementToDisappear(advancedSearchPanel);
		waitForElementToAppear(getResultTopOne);
		return getResultTopOne.getText();
	}
	
	public void memberName(String name) {
		waitForElementToAppear(fullNameInpt);
		fullNameInpt.sendKeys(name);
	}
	
	public void status(String status) {
		dropDownSelection(statusDp, status);
	}
	
	public void walletAccount(String walletAccountNo) {
		walletAccountInpt.sendKeys(walletAccountNo);
	}
	
	
	
	
	

}
