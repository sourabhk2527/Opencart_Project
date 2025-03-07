package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC03_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass= DataProviders.class, groups = {"Smoke","Regression", "Master"}) //getting data provider from diff package
	public void validate_LoginDTT(String email, String pwd, String exp)
	{
	    logger.info("**** Starting TC03_LoginDDT *****");
		try {
		HomePage home = new HomePage(driver);
		home.accountClick();
		home.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.sendEmail(email);
		lp.sendPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage account = new MyAccountPage(driver);
		boolean msgAccount = account.verifyAccountMessage();
		
		/*Data is valid  -- Login Success -- Test Pass --Logout
		 Data is valid  -- Login Failed --  Test Fail
		 
		 Data is invalid -- login Success -- Test Fail --Logout
		 Data is invalid -- Login Failed -- Test Pass  */
		
		if (exp.equalsIgnoreCase("Valid")) 
		{
			if(msgAccount == true)
			{
				account.clickLogout();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
			
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(msgAccount == true)
			{
				account.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
			
		}
		
		}
		catch (Exception e) {
			Assert.fail();
		}

		logger.info("**** Ending TC03_LoginDDT *****");
		
	}

}
