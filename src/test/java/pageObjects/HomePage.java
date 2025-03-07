package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


	public HomePage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement accountlnk;

	@FindBy(linkText = "Register")
	WebElement registerlnk;
	
	@FindBy(linkText ="Login")
	WebElement loginlnk;

	public void accountClick() {
		accountlnk.click();

	}
	
	public void clickRegister() {
		registerlnk.click();
		
	}
	
	public void clickLogin() {
		loginlnk.click();
	}

}
