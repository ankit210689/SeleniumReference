package Selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GmailLogin {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com");
		driver.findElement(By.className("gb_d")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@data-action=\"sign in\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("ab.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();//xpath created using Ctrl+F in chrome F12 mode
		Thread.sleep(2000);
		String actual_result=driver.findElement(By.className("o6cuMc")).getText();
		System.out.println(actual_result);
		String expected_result="Couldn’t find your Google Account";
		driver.close();

		if(actual_result.equalsIgnoreCase(expected_result))
		{
			System.out.println("Test Passed!");
		}
		else
			System.out.println("Test Failed!");
	}

}
