package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class CashInDepositPage extends AbstractComponents{

	WebDriver driver;
	
	public CashInDepositPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".btn.btn-primary") WebElement makeADepositButton;
	@FindBy(id="manual_depository_bank") WebElement depositBankAccountdropdown;
	
	
	
	
	public void manualDeposit() {
		waitForElementToAppear(makeADepositButton);
		makeADepositButton.click();
		waitForElementToAppear(depositBankAccountdropdown);
		Select dropdown = new Select(depositBankAccountdropdown);
		dropdown.selectByValue("BDO_UNIBANK");
	}
	
	
	

}
