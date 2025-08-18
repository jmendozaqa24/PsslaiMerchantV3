package PsslaiMerchantV3.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PsslaiMerchantV3.PageObject.BranchPage;
import PsslaiMerchantV3.PageObject.CashInDepositPage;
import PsslaiMerchantV3.PageObject.CashOutDisbursementPage;
import PsslaiMerchantV3.PageObject.CashOutWithdrawalPage;
import PsslaiMerchantV3.PageObject.MemberPage;
import PsslaiMerchantV3.PageObject.SettingsPage;
import PsslaiMerchantV3.PageObject.TransactionPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="members-link-side-bar") WebElement memberMenu;
	@FindBy(id="branches-link-side-bar") WebElement branchMenu;
	@FindBy(id="deposits-link-side-bar") WebElement cashInDepositMenu;
	@FindBy(id="withdrawals-link-side-bar") WebElement withdrawalMenu;
	@FindBy(id="disbursements-link-side-bar") WebElement disbursementMenu;
	@FindBy(id="transactions-link-side-bar") WebElement transactionMenu;
	@FindBy(id="settings-link-side-bar") WebElement settingsMenu;
	
	public void waitForElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void uploadFile(WebDriver driver, WebElement locator, String fileUpload) {
	    WebElement element = locator;
	    element.clear();
	    element.sendKeys(fileUpload);
	}
	
	public MemberPage goToMemberMenu() {
		waitForElementToAppear(memberMenu);
		memberMenu.click();
		MemberPage memberPage = new MemberPage(driver);
		return memberPage;
	}
	
	public BranchPage goToBranchMenu() {
		waitForElementToAppear(branchMenu);
		branchMenu.click();
		BranchPage branchPage = new BranchPage(driver);
		return branchPage;
	}
	
	public CashInDepositPage goToCashInMenu_Deposit() {
		waitForElementToAppear(cashInDepositMenu);
		cashInDepositMenu.click();
		CashInDepositPage cashInDepositPage = new CashInDepositPage(driver);
		return cashInDepositPage;
		
	}
	public CashOutWithdrawalPage goToCashOutMenu_Withdrawal() {
		waitForElementToAppear(withdrawalMenu);
		withdrawalMenu.click();
		CashOutWithdrawalPage cashOutWithdrawalPage = new CashOutWithdrawalPage(driver);
		return cashOutWithdrawalPage;
		
	}
	public CashOutDisbursementPage goToCashOutMenu_Disbursement() {
		waitForElementToAppear(disbursementMenu);
		disbursementMenu.click();
		CashOutDisbursementPage cashOutDisbursementPage = new CashOutDisbursementPage(driver);
		return cashOutDisbursementPage;
		
	}
	public TransactionPage goToTransactionMenu() {
		waitForElementToAppear(transactionMenu);
		transactionMenu.click();
		TransactionPage transactionPage = new TransactionPage(driver);
		return transactionPage;
		
	}
	public SettingsPage goToSettingsMenu() {
		waitForElementToAppear(settingsMenu);
		settingsMenu.click();
		SettingsPage settingsPage = new SettingsPage(driver);
		return settingsPage;
	}
	
	public String getSuccessMessageText(WebElement successToastMsg) {
		waitForElementToAppear(successToastMsg);
		return successToastMsg.getText();
	}
	
	public void dropDownSelection(WebElement dropDownElement, String value) {
		Select dropDown = new Select(dropDownElement);
		dropDown.selectByValue(value);
	}
	
	
	
	
}
