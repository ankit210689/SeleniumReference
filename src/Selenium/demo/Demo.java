package Selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.google.com/");
		driver.manage().window().maximize();
		driver.findElement(By.className("gb_Ue")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("xpath=//div[@id='yDmH0d']/c-wiz/div/div/c-wiz/div/div/ul/li[7]/a/div/span")).click();
		driver.findElement(By.className("gb_d")).click();
		Thread.sleep(2000);
		String actual=driver.getTitle();
		String expected="Gmail: Private and secure email at no cost | Google Workspace";
		driver.close();
		//System.out.println(actual);---->TO check actual result
		if (actual.equalsIgnoreCase(expected))
				{
			System.out.println("Test Passed!");
				}
		else
			System.out.println("Test Failed!");


//Not able to locate elements in Google Apps menu. Need to research more on that.
	}

	}

