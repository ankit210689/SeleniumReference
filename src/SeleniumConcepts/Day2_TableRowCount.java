package SeleniumConcepts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//Q5. How to count total number of rows in a table?
//Q11. How to capture screenshot?
//Q24. What is the difference between "GET" and "NAVIGATE" to open a webpage in selenium webdriver?
//Q139. How to navigate back and forward on the browser?
//How to save data from a web-table in a local text file?


public class Day2_TableRowCount {


	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://en.wikipedia.org/wiki/Shah_Rukh_Khan_filmography");
		//driver.navigate().to("https://en.wikipedia.org/wiki/Shah_Rukh_Khan_filmography");//Access the URL
		Thread.sleep(3000);
		
		List<WebElement> countRows=driver.findElements(By.tagName("tr"));//Storing all elements of the table in a list 
		int totalRows=countRows.size();//Returns total number of rows in tables on webpage
		System.out.println(totalRows);
		
		
		WebElement td=driver.findElement(By.xpath("//table[@class='wikitable plainrowheaders sortable jquery-tablesorter']"));
		Dimension table=td.getSize();//Returns the dimensions of table
		System.out.println(table);
		
		String fulldata=td.getText();
		//System.out.println(fulldata);//Returns table data in console
		
		
		//Method to copy table data in notepad
		File f=new File("savetext.txt");//Create new text file in src folder
		FileUtils.writeStringToFile(f,fulldata,Charset.defaultCharset());//FileUtils is an apache.io class that copies table into text file
		
		/*List<WebElement> rows=driver.findElements(By.xpath("//table[@class='wikitable plainrowheaders sortable jquery-tablesorter']"));
		int totalRows=rows.size();
		System.out.println(totalRows);*/
		
		//Navigate method implementation. It guides through the history of browser in current webdriver instance
		driver.navigate().back();//Returns back to last visited page on the browser
		driver.navigate().forward();//Opens the next page on the browser/webdriver instance
		driver.navigate().refresh();//Refreshes the current webpage
		
		//Method to take screenshot of the current webpage
		File s=(File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//TakesScreenshot is a built in selelnium class and we are referring getScreenshotAs method of this class to take screenshot
		FileUtils.copyFile(s, new File("C:\\Users\\hp\\eclipse-workspace\\SeleniumProject\\src\\SSFile.png"));//Assigning location for screenshot file
		
		driver.quit();

	}

}
