package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "identity")
	WebElement usernameField;
	@FindBy(id = "credential")
	WebElement passwordField;
	@FindBy(id = "login-submit-button")
	WebElement submit;
	@FindBy(xpath = "//form[@id='sign-in-form']//p[@class='error']")
	WebElement errorMessage;

	public void goTo() {
		driver.get("https://psslai-sit.traxionpay.com/");
	}

	public DashBoardPage login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submit.click();
		DashBoardPage dashboardPage = new DashBoardPage(driver);
		return dashboardPage;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
