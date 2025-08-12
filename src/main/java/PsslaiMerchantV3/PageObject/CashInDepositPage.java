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
	@FindBy(id="manual_amount") WebElement manualAmount;
	@FindBy(css=".modal-title") WebElement modalTitle;
	@FindBy(name="file") WebElement fileUpload;
	
	
	public void manualDeposit() {
		waitForElementToAppear(makeADepositButton);
		makeADepositButton.click();
		waitForElementToAppear(modalTitle);
		manualAmount.clear();
		manualAmount.sendKeys("1000");
		Select dropdown = new Select(depositBankAccountdropdown);
		dropdown.selectByValue("BDO_UNIBANK");
		//fileUpload.click();
	}
	
	
	

}
