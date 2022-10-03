package SeleniumConcepts;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

//Q17. How to get element attribute using Selenium?
//Q27. How to mouse hover on an element?
//Q28. How to switch back from a frame?
//Q30. How to refresh a page without using context click? (sendKeys.Keys, navigate.refresh(), navigate.to(), get(), sendKeys() methods)
//Q34. Part1: How to handle alerts in Selenium?
//Q36. How to switch between frames?
//Q37. What is the default timeout for selenium ide and webdriver?
//Q50. How to build/identify xpath of a webelement on your browser?<--Developer tools and Selenium ide in chrome and firepath in firefox
//Q53. How to over the mouse on an element?
//Q61. How to get the number of frames on a page?
//Q65. What attribute you should consider throughout the script in frame if no frame id as well as no frame name is found?
//Q104. Write and example of if-else statement.
//Q138. How to refresh a browser page?
//Q146. iFrames-How to handle frames in webdriver?
//Q147. iFrames-How to switch to a particular frame by index?

public class Day3_FrameSwitchingAndWindowHandles {
	

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\\\Selenium Data\\\\chromedriver_win32\\\\chromedriver.exe");
		
		//To open the browser in incognito mode
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver=new ChromeDriver(options);
		
		
		//DesiredCapabilities capabilities=DesiredCapabilities.
		//capabilities.setCapability("chrome.switches",Arrays.asList("--incognito"));
		
		driver.get("https://demo.guru99.com/V1/index.php");
		Thread.sleep(3000);
		
		String Title=driver.getTitle();
		System.out.println(Title);
		

		//Getting number of frames on a webpage
		List <WebElement> framesList=driver.findElements (By.xpath("//iframe"));//Answer to Qu65.
		int totalFrames=framesList.size();
		System.out.println(totalFrames);
		
		driver.switchTo().frame("ccpa-consent-notice");//Switching frames by a name or id
		//driver.switchTo().frame(0);<---By index
		//driver.switchTo().frame(previouslyFoundWebElement);<---A previously found webelement
		//driver.switchTo().frame(nameOrId)<---Syntax for a name or id
		driver.findElement(By.xpath("//button[@id='close']")).click();
		//driver.navigate().refresh();
		//driver.switchTo().parentFrame();
		//driver.switchTo().defaultContent();
		//driver.findElement(By.name("uid")).sendKeys(Keys.F5);
		driver.findElement(By.name("uid")).sendKeys("ankit210689@icloud.com");
		
		//Getting Element attribute value for attribute type
		WebElement el=driver.findElement(By.name("password"));
		String attributeValue=el.getAttribute("type");
		System.out.println(attributeValue);
		
		driver.findElement(By.name("btnLogin")).click();
		//Thread.sleep(3000);
		
		//Handling alerts
		String element=driver.switchTo().alert().getText();
		System.out.println(element);//Alert text printed
		driver.switchTo().alert().accept();//-->To accept the alert
		//driver.switchTo().alert().dismiss();--->To dismiss the alert
		
		//Performing mouse hover to a webelement and clicking it
		Actions action=new Actions(driver);
		WebElement im=driver.findElement(By.xpath("//img[@src='/logo.png']"));
		action.moveToElement(im).moveToElement(driver.findElement(By.xpath("//a[text()='Demo Site']"))).click().build().perform();
		Thread.sleep(3000);
		
		List<WebElement> Frames2=driver.findElements(By.xpath("//iframe"));
		int TotalFrames2=Frames2.size();
		System.out.println("Total frames on this page= "+TotalFrames2);
		
		if (TotalFrames2>0) {
			
			driver.navigate().back();
			driver.navigate().forward();
			String Title2=driver.getTitle();
			System.out.println(Title2);
		}
		
		else {
			
			String Title2=driver.getTitle();
			System.out.println(Title2);
			
		}
		
		/*try {
			driver.switchTo().frame(11).close();
			//driver.switchTo().activeElement().findElement(By.xpath("//div/span[text()='Close']")).click();
			
		} 
		
		catch (org.openqa.selenium.NoSuchFrameException e) {
			driver.switchTo().parentFrame();
		}*/
		
		
		//driver.manage().timeouts();//Default timeout for selenium ide and webdriver is 30 seconds
		driver.quit();
		
			
		}
		
		

	}


