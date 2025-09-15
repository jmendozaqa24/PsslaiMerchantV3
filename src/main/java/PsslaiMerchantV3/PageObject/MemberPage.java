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
	
	public void searchMember(String fName) {
		waitForElementToAppear(searchBar);
		searchBar.sendKeys(fName);
	}
	
	public String getSearchResultMember(String fName) {
		searchMember(fName);
		waitForElementToAppear(getResultTopOne);
		return getResultTopOne.getText();
	}
	
	public String clickUser(String fName) {
		getSearchResultMember(fName);
		getResultTopOne.click();
		waitForElementToAppear(viewDetailsBtn);
		viewDetailsBtn.click();
		waitForElementToAppear(pageTitle);
		return pageTitle.getText();
	}
	
	
	
	
	
	
	

}
