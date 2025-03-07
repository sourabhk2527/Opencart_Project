package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;



import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void ValidateRegisteration() {
		logger.info("***** Starting TC01_AccountRegistrationTest *******");
		try {
			
		HomePage home = new HomePage(driver);
		AccountRegistrationPage account = new AccountRegistrationPage(driver);

		home.accountClick();
		logger.info("****** Clicked on Account link ******");
		home.clickRegister();
		logger.info("****** Clicked on Register l ink ******");
		String password = randomAlphaNumric();

		account.enterPersonalDetails(randomString().toUpperCase(), randomString().toUpperCase(),
				randomString() + "@gmail.com", randomNumber(), password, password);
		logger.info("****** Filled Personal Details ******");

		account.setPrivacyPolicy();
		logger.info("****** Checked Policy Checkbox ******");
		boolean checkbox = account.checkPrivcybox();
		account.clickContinue();
		logger.info("****** Validating exepected message ******");
		String confmsg = account.getConfirmationMsg();
		Assert.assertTrue(checkbox);
		
		if (confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug Log");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!");
		}
		catch (Exception e) {
			Assert.fail();
			
		}
		logger.info("***** Finished TC01_AccountRegistrationTest *******");
	}

}
