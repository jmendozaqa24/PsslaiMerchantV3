package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "identity")
	WebElement usernameField;
	@FindBy(id = "credential")
	WebElement passwordField;
	@FindBy(id = "login-submit-button")
	WebElement submit;

	public void goTo() {
		driver.get("https://psslai-sit.traxionpay.com/");
	}

	public void login(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submit.click();
	}

}
