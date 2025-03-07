package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass{
	
	
	@Test(groups={"Smoke", "Master"})
	public void LoginValidation() {
		
		logger.info("***** Starting TC02_LoginTest *******");
		
		try
		{
			
		HomePage home = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		MyAccountPage account = new MyAccountPage(driver);
		
		home.accountClick();
		home.clickLogin();
		lp.sendEmail(prop.getProperty("email"));
		lp.sendPassword(prop.getProperty("password"));
		lp.clickLogin();
		boolean msgAccount = account.verifyAccountMessage();
		
		Assert.assertTrue(msgAccount);    //Assert.assertEquals(msgAccount, true, "Login Failed...");
		}
		catch (Exception e) {
            Assert.fail();
		}
		logger.info("***** Finished TC02_LoginTest *******");
		
		
		
	}

}
