package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-firstname")
	WebElement txtfirstName;

	@FindBy(id = "input-lastname")
	WebElement txtlastName;

	@FindBy(id = "input-email")
	WebElement txtemail;

	@FindBy(id = "input-telephone")
	WebElement txttelephone;

	@FindBy(id = "input-password")
	WebElement txtpassword;

	@FindBy(id = "input-confirm")
	WebElement txtconpassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPrivacy;

	@FindBy(css = ".btn.btn-primary")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void enterPersonalDetails(String firstname, String lastname, String email, String telephone, String password,
			String conPassword) {
		txtfirstName.sendKeys(firstname);
		txtlastName.sendKeys(lastname);
		txtemail.sendKeys(email);
		txttelephone.sendKeys(telephone);
		txtpassword.sendKeys(password);
		txtconpassword.sendKeys(conPassword);
	}

	public void setPrivacyPolicy() {
		chkPrivacy.click();

	}

	public boolean checkPrivcybox() {
		return chkPrivacy.isSelected();
	}
	public void clickContinue() {
		btnContinue.click();

		// btnContinue.submit();

		// Actions act = new Actions(driver);
		// act.moveToElement(btnContinue).click().perform();

		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click();", btnContinue);

		// btnContinue.sendKeys(Keys.RETURN);

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
