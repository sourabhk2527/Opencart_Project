package utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		
		String path = ".\\testData\\DemoDataTest.xlsx";   //Taking file from testData folder
		
		ExcelUtility xlutil = new ExcelUtility(path);   // creating an object for xlUtility
		
		int totolrows=xlutil.getRowCount("Sheet1");
		int totolcols=xlutil.getCellCount("Sheet1",1);
		
		String logindata [][]= new String[totolrows][totolcols];  //created for two dimension array which can stores rows and colms
		
		for(int i=1; i<=totolrows; i++)       // Reading data from xl storing in two dimensional array
		{
			for(int j=0; j<totolcols; j++) 
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
		 return logindata;
				
	}

}
