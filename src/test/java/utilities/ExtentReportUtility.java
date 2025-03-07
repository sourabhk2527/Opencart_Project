package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportUtility implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	 
	public void onStart(ITestContext context) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time Stamp
		repName = "Test-Report-"+ timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);  // Location of Report
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application","Opencart" );
		extent.setSystemInfo("Module","Admin" );
		extent.setSystemInfo("Sub-Module","Customers" );
		extent.setSystemInfo("Username",System.getProperty("user.name") );
		extent.setSystemInfo("Envirnment","QA" );
		
		 String ops = context.getCurrentXmlTest().getParameter("os");  /// os is coming from xml file 
		 extent.setSystemInfo("Operating System", ops);
		 
		 String browser = context.getCurrentXmlTest().getParameter("browser");  /// browser is coming from xml file 
		 extent.setSystemInfo("Operating System", browser);
		 
		 List<String> includedGroups =context.getCurrentXmlTest().getIncludedGroups();
		 if (!includedGroups.isEmpty()) {
			 extent.setSystemInfo("Included Groups", includedGroups.toString());
		 }
		 
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  // to display groups in reports
		test.log(Status.PASS, result.getName()+"got successfully executed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try 
		{
		String imgPath = new BaseClass().captureSreen(result.getName()); 
		test.addScreenCaptureFromPath(imgPath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathOfExtentReport =System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReort = new File(pathOfExtentReport); 	
		
		try {
			Desktop.getDesktop().browse(extentReort.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
