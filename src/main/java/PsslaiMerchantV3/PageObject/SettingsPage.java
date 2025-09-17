package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class SettingsPage extends AbstractComponents {

	WebDriver driver;

	public SettingsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[@class='list-group list-group-transparent'][1]//a[1]")
	WebElement myAccountMenu;
	@FindBy(xpath = "//div[@class='list-group list-group-transparent'][1]//a[2]")
	WebElement merchantDetailsMenu;
	@FindBy(xpath = "//div[@class='list-group list-group-transparent'][1]//a[2]")
	WebElement bankAccountsMenu;
	@FindBy(xpath = "//div[@class='list-group list-group-transparent'][1]//a[4]")
	WebElement apiSettingsMenu;
	@FindBy(xpath = "//div[@class='list-group list-group-transparent'][2]//a")
	WebElement timeOutSettingsMenu;
	
	@FindBy(css=".card-body") WebElement cardBodySection;
	@FindBy(css=".mb-4") WebElement cardTitle;

	
	
	public void viewMyAccount() {
		clickBtn(myAccountMenu);
		

	}

	public void viewMerchantDetails() {
		clickBtn(merchantDetailsMenu);
	}

	public void viewBankAccount() {
		clickBtn(bankAccountsMenu);

	}

	public void apiSettings() {
		clickBtn(apiSettingsMenu);

	}

	public void viewTimeOutSettings() {
		clickBtn(timeOutSettingsMenu);
	}
	
	public String getTitleCard() {
		waitForElementToAppear(cardTitle);
		return cardTitle.getText();
	}

}
