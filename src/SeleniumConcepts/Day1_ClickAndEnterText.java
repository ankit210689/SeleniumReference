package SeleniumConcepts;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Q3. How to click on Element?
//Q4. How to enter text into page element?
//Q6. How to get page title?
//Q7. How to get page source of webpage?
//Q8. How to get current URL
//Q12. How to maximize window?
//Q16. How to clear content of a text box in Selenium?
//Q81. How to tell WebDriver to use the correct driver?-->By using System.setProperty(<Key for the browser>,<Path to browser specific webdriver exe file in local machine>)


public class Day1_ClickAndEnterText {
	@Test
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();//Maximizes the window
		driver.get("https://www.google.com");
		driver.findElement(By.linkText("Sign in")).click();//Clicking a webelement

		//Applying Implicit Wait
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		WebElement el=driver.findElement(By.name("identifier"));
		el.click();
		el.sendKeys("shsdjhdkh@gmail.com");//Send text to a webelement
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String wh=driver.getWindowHandle();//Returns window handle
		String title=driver.getTitle();//Returns page title
		String url=driver.getCurrentUrl();//Returns current URL
		//String ps=driver.getPageSource();//Returns page source

		//Applying Explicit Wait
		//WebDriverWait wait=new WebDriverWait(driver,10)<---This has been depricated in Selenium 4.0. This issue can be suppressed by changing the above statement in the following ways
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='o6cuMc']")));

		String text =driver.findElement(By.className("o6cuMc")).getText();
		el.clear();//Clears "Email or Phone" text box

		System.out.println("Window handle: "+wh+"\nTitle: "+title+ "\nLinktext: "+text+ "\nCurrent URL: "+url);
		//System.out.println("Page Source: "+ps);

		if (text.equalsIgnoreCase("Couldn’t find your Google Account")){
				System.out.println("Test Passed");
		}
		else {
			System.out.println("Test Failed");
		}
		
		driver.quit();
	}

}
