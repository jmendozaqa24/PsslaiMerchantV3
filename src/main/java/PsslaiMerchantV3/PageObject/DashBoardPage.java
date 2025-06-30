package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class DashBoardPage extends AbstractComponents {

	WebDriver driver;

	public DashBoardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "logo-link-side-bar")
	WebElement PsslaiLogo;
	
	
	public boolean dashboardLogodisplay() {
		waitForElementToAppear(PsslaiLogo);
		return PsslaiLogo.isDisplayed();
	}

}
