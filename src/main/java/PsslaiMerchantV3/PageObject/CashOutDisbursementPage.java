package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class CashOutDisbursementPage extends AbstractComponents {

	WebDriver driver;
	String file = System.getProperty("user.dir")
			+ "\\src\\main\\java\\PsslaiMerchantV3\\Resource\\Wallet_Disbursements_TraxionPay_20250820084251.xlsx";

	public CashOutDisbursementPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".btn.btn-primary")
	WebElement bulkDisbursementbtn;
	@FindBy(css = ".modal-title")
	WebElement modalTitle;
	@FindBy(xpath = "//span[@class='form-selectgroup-title strong mb-1'][text()=\"Wallet Transfers (TraxionPay)\"]")
	WebElement walletTransferType;
	@FindBy(xpath = "//span[@class='form-selectgroup-title strong mb-1'][text()=\"Interbank Transfers (InstaPay)\"]")
	WebElement interbankTransferType;
	@FindBy(css = ".lh-lg")
	WebElement disbursementFeeDescription;
	@FindBy(name = "deposit_slip_file")
	WebElement uploadFileBtn;
	@FindBy(xpath = "//input[@name='validator'][@value='password']")
	WebElement passwordRadioBtn;
	@FindBy(xpath = "//input[@name='validator'][@value='mpin']")
	WebElement mpinRadioBtn;
	@FindBy(xpath = "//input[@name='validation']")
	WebElement validationInput;
	@FindBy(id = "create-disbursement-request-button")
	WebElement createDisbursementBtn;

	public void clickBuldDisbursementButton() {
		waitForElementToAppear(bulkDisbursementbtn);
		bulkDisbursementbtn.click();
		waitForElementToAppear(modalTitle);
	}

	public String getDisbursementFeeDscrptn() {
		return disbursementFeeDescription.getText();
	}

	public void bulkDisbursementFillOut() {
		uploadFile(driver, uploadFileBtn, file);
		passwordRadioBtn.click();
	}

	public void inputValidation(String input) {
		validationInput.sendKeys(input);
	}
	
	public void submitForm() {
		createDisbursementBtn.click();
	}

}
