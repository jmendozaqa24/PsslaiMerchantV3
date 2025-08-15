package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class CashInDepositPage extends AbstractComponents {

	WebDriver driver;
	String uploadFILE = System.getProperty("user.dir")
			+ "\\src\\main\\java\\PsslaiMerchantV3\\Resource\\Images\\Grocery-Receipt-Example.jpg";
	String manualGeneratedRef;
	String onlineGeneratedRef;
	String getRefNum;

	public CashInDepositPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".btn.btn-primary")
	WebElement makeADepositButton;
	@FindBy(id = "manual_depository_bank")
	WebElement manualBankAccountdropdown;
	@FindBy(id = "manual_amount")
	WebElement manualAmount;
	@FindBy(name = "online_payment_categories")
	WebElement onlineBankAccountdropdown;
	@FindBy(name = "online_payment_methods")
	WebElement onlineMethod;
	@FindBy(id = "online_amount")
	WebElement onlineAmount;
	@FindBy(css = ".modal-title")
	WebElement modalTitle;
	@FindBy(name = "file")
	WebElement fileUpload;
	@FindBy(id = "create-manual-request-button")
	WebElement submitManualDeposit;
	@FindBy(id = "create-online-request-button")
	WebElement submitOnlineDeposit;
	@FindBy(css = ".alertify-notifier.ajs-top.ajs-right")
	WebElement successToastMessage;
	@FindBy(xpath = "//span[@class='form-selectgroup-check'][1]")
	WebElement onlineDepositType;

	@FindBy(id = "online_transaction_fee")
	WebElement onlineTrxFee;
	@FindBy(id = "manual_transaction_fee")
	WebElement manualTrxFee;
	
	@FindBy(id="manual_transaction_reference_number") WebElement getManualGeneratedRefNum;
	@FindBy(id="online_merchant_reference_number") WebElement getOnlineGeneratedRefNum;

	

	public void clickMakeDepositButton() {

		waitForElementToAppear(makeADepositButton);
		makeADepositButton.click();
		waitForElementToAppear(modalTitle);
	}

	public String manualDepositFillOut() {

		manualAmount.clear();
		manualAmount.sendKeys("1000");
		manualGeneratedRef = getManualGeneratedRefNum.getText();
		dropDownSelection(manualBankAccountdropdown, "BDO_UNIBANK");
		uploadFile(driver, fileUpload, uploadFILE);
		submitManualDeposit.click();
		return manualGeneratedRef;

	}

	public void onlineDepositFillOut() {
		
		waitForElementToAppear(onlineDepositType);
		onlineDepositType.click();
		onlineAmount.clear();
		onlineGeneratedRef = getOnlineGeneratedRefNum.getText();
		onlineAmount.sendKeys("1000");
		dropDownSelection(onlineBankAccountdropdown, "2");
		dropDownSelection(onlineMethod, "36823");
		String onlineTransactionFee = onlineTrxFee.getText();
		uploadFile(driver, fileUpload, uploadFILE);
		submitOnlineDeposit.click();


	}
	
	public String getTransactionRef() {
		waitForElementToAppear(successToastMessage);
		getRefNum = successToastMessage.getText();
		String[] splitRefNum = getRefNum.trim().split(" ");
		String referenceNum = splitRefNum[7];
		return referenceNum;
	}
	
	public String getTransactionRefAutoGenOnline() {
		onlineGeneratedRef = getOnlineGeneratedRefNum.getText();
		return onlineGeneratedRef;
	}
	
	public String getTransactionRefAutoGenManual() {
		manualGeneratedRef = getManualGeneratedRefNum.getText();
		return manualGeneratedRef;
	}
	
	

	@FindBy(id = "list-status-select")
	WebElement statusDropdown;
	@FindBy(id = "dt-search-0")
	WebElement searchBar;

	public void checkTransactionPending() {
		
		waitForElementToAppear(statusDropdown);
		dropDownSelection(statusDropdown, "1");
		//searchBar.sendKeys(referenceNum);

	}
	
	

}
