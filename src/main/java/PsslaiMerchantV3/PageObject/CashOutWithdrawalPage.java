package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class CashOutWithdrawalPage extends AbstractComponents {

	WebDriver driver;

	public CashOutWithdrawalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".btn.btn-primary")
	WebElement withdrawFundsButton;
	@FindBy(css = ".modal-title")
	WebElement modalTitle;
	@FindBy(xpath = "//span[@class='form-selectgroup-title strong mb-1'][text()=\"via InstaPay\"]")
	WebElement instapayType;
	@FindBy(xpath = "//span[@class='form-selectgroup-title strong mb-1'][text()=\"via PesoNet\"]")
	WebElement pesonetType;

	@FindBy(id = "linked-accounts-toggle")
	WebElement linkedAccountToggle;
	@FindBy(id = "withdraw_recipient_bank")
	WebElement withdrawRecipientBank;
	@FindBy(id = "withdraw_recipient_bank_listings")
	WebElement withdrawRecipientBankNLA;
	@FindBy(id = "withdraw_account_name")
	WebElement accountName;
	@FindBy(id = "withdraw_account_number")
	WebElement accountNumber;
	@FindBy(id = "withdraw_amount")
	WebElement withdrawAmount;
	@FindBy(xpath = "//input[@name='validator'][@value='password']")
	WebElement passwordRadioBtn;
	@FindBy(xpath = "//input[@name='validator'][@value='mpin']")
	WebElement mpinRadioBtn;
	@FindBy(xpath = "//input[@name='validation']")
	WebElement validationInput;
	@FindBy(id = "create-withdraw-funds-request-button")
	WebElement submitForm;
	@FindBy(css = ".ajs-message.ajs-success.ajs-visible")
	WebElement successToastMsg;

	public void clickWithdrawFundsButton() {
		waitForElementToAppear(withdrawFundsButton);
		withdrawFundsButton.click();
		waitForElementToAppear(modalTitle);

	}

	public void linkedAccountsToggleOff() {
		linkedAccountToggle.click();
	}

	public boolean linkedAccountsToggle() {
		return linkedAccountToggle.isSelected();
	}

	public void withdrawalFundsFillOutLinkedAccounts(String bankAccount, String amount) {
		dropDownSelection(withdrawRecipientBank, bankAccount);
		withdrawAmount.sendKeys(amount);
		passwordRadioBtn.click();
	}

	public void withdrawalFundsFillOutNoLinkedAccounts(String bankAccount, String accountNameData, String accountNumberData, String amountData) {
		dropDownSelection(withdrawRecipientBankNLA, bankAccount);
		accountName.sendKeys(accountNameData);
		accountNumber.sendKeys(accountNumberData);
		withdrawAmount.sendKeys(amountData);
		passwordRadioBtn.click();
	}

	public void inputValidation(String input) {
		validationInput.sendKeys(input);
	}

	public void submitWithdrawFunds() {
		submitForm.click();
	}

	public boolean getSuccessToastMsg() {
		waitForElementToAppear(successToastMsg);
		return successToastMsg.isDisplayed();

	}

}
