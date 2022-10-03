package Selenium.demo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class fetchGoogleNewsHeadlines {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");

		// TODO Auto-generated method stub

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		String url="https://news.google.com/topstories?hl=en-US&gl=US&ceid=US:en";
		driver.get(url);

		Thread.sleep(5000);//Wait 5 seconds for page to load completely

		//JavaScriptExecutor
		JavascriptExecutor js=(JavascriptExecutor) driver;

		//Scroll down to bottom of the page
		js.executeScript("window.scrollBy(0,document.body.scrollheight)");

		//Find all headline elements using relative xpath for all elements and save in list "headlines"
		List<WebElement> headlines = driver.findElements(By.xpath("//h3/a[@class='DY5T1d RZIKme']"));

		//print the size of list from findelements()
		System.out.println(headlines.size()+" Headlines found");

		//getText for all the webelements in list "headlines" and print each headline text
		for (WebElement webElement:headlines) {
			String name= webElement.getText();
			System.out.println(name);
		}

		driver.quit();


	}

}
