package PsslaiMerchantV3.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PsslaiMerchantV3.AbstractComponents.AbstractComponents;

public class MemberPage extends AbstractComponents{
	
	WebDriver driver;
	
	public MemberPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".page-title")WebElement memberPageTitle;
	
	
	public String verifyMemberPageTitle() {
		String memberTitle = memberPageTitle.getText();
		return memberTitle;
	}

}
