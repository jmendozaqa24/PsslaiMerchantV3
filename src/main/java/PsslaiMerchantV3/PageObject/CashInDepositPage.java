package PsslaiMerchantV3.PageObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class CashInDepositPage extends AbstractComponents {

	WebDriver driver;
	String uploadFILE = System.getProperty("user.dir")
			+ "\\src\\main\\java\\PsslaiMerchantV3\\Resource\\Images\\Grocery-Receipt-Example.jpg";
	String manualGeneratedRef;
	String onlineGeneratedRef;
	String refNum;

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
	@FindBy(xpath = "//option[@value='36823']")
	WebElement onlineMethodValue;
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
	@FindBy(xpath = "//span[@class='form-selectgroup-title strong mb-1'][text()='Online Deposit']")
	WebElement onlineDepositType;

	@FindBy(id = "online_transaction_fee")
	WebElement onlineTrxFee;
	@FindBy(id = "manual_transaction_fee")
	WebElement manualTrxFee;

	@FindBy(id = "manual_transaction_reference_number")
	WebElement getManualGeneratedRefNum;
	@FindBy(id = "online_merchant_reference_number")
	WebElement getOnlineGeneratedRefNum;

	public void clickMakeDepositButton() {

		waitForElementToAppear(makeADepositButton);
		makeADepositButton.click();
		waitForElementToAppear(modalTitle);
	}

	public String manualDepositFillOut() {

		manualAmount.clear();
		manualAmount.sendKeys("1000");
		manualGeneratedRef = getManualGeneratedRefNum.getAttribute("value");
		dropDownSelection(manualBankAccountdropdown, "BDO_UNIBANK");
		uploadFile(driver, fileUpload, uploadFILE);
		return manualGeneratedRef;

	}

	public void submitManualForm() {
		submitManualDeposit.click();
	}

	public void submitOnlineForm() {
		submitOnlineDeposit.click();
	}

	public String getTransactionRef() {
		waitForElementToAppear(successToastMessage);
		String getRefNum = successToastMessage.getText();
		// System.out.println("Toast Raw Text: " + getRefNum);

		refNum = "";
		Matcher matcher = Pattern.compile("Reference Number\\s+([A-Za-z0-9]+)").matcher(getRefNum);
		if (matcher.find()) {
			refNum = matcher.group(1);
		}
		// System.out.println("Extracted Ref: " + refNum);
		return refNum;
	}

	public void onlineDepositFillOut() {

		waitForElementToAppear(onlineDepositType);
		onlineDepositType.click();
		onlineAmount.clear();
		onlineGeneratedRef = getOnlineGeneratedRefNum.getText();
		onlineAmount.sendKeys("1000.00");
		dropDownSelection(onlineBankAccountdropdown, "2");
		waitForElementToAppear(onlineMethodValue);
		dropDownSelection(onlineMethod, "36823");
		// String onlineTransactionFee = onlineTrxFee.getText();

	}

	@FindBy(xpath = "//iframe[@title='Response']")
	WebElement dragonPayFrame;
	@FindBy(id = "ContentPlaceHolder1_userIdTextBox")
	WebElement bogusBankUser;
	@FindBy(id = "ContentPlaceHolder1_passwdTextBox")
	WebElement bogusBankPass;
	@FindBy(id = "ContentPlaceHolder1_getAccountsButton")
	WebElement continueButton;
	@FindBy(id = "ContentPlaceHolder1_transferButton")
	WebElement payButton;
	@FindBy(xpath="//p[@class='payment'][text()=\"Payment Successful!\"]") WebElement successMessage;

	public void transactBogusBank(String username, String userPass) {
		waitForElementToAppear(dragonPayFrame);
		driver.switchTo().frame(dragonPayFrame);
		waitForElementToAppear(bogusBankUser);
		bogusBankUser.sendKeys(username);
		bogusBankPass.sendKeys(userPass);
		continueButton.click();
		waitForElementToAppear(payButton);
		payButton.click();

	}
	
	public String getSuccessMessage() {
		waitForElementToAppear(successMessage);
		return successMessage.getText();
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
		// searchBar.sendKeys(referenceNumbertoVerify());

	}

}
