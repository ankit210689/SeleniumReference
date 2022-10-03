package SeleniumConcepts;

import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Q9. How to double click on an element?
//Q25. Difference between Implicit and Explicit wait.
//Q46. Name the Selenium function/s used for retrieving the attribute or value.
//Q49. How do you send ENTER/TAB keys in webdriver?--->Actions class will be referenced
//Q59. What is the use of contextClick()?



public class Day4_AllLocatorsAndContextClick {
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		String url="http://automationpractice.com/index.php";
		driver.manage().window().maximize();
		driver.get(url);
		//Thread.sleep(5000);-->Hard coded wait for 5000 milliseconds, irrespective of the webelement presence
		
		//Fluent Wait-->Polling interval can be changed when compared to explicit wait where polling interval is fixed ie. 500ms
		
		
		//Explicit wait-or just wait is a one-time used for a particular web-element. It is more extensible in the sense that we can use conditions to define it.
		//WebDriverWait wait=new WebDriverWait(driver,30);--->This constructor has been depricated in selenium 4.0
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));//Instead we will use this constructor
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='sf-with-ul' and text()='Women']")));
		
		
		WebElement Category=driver.findElement(By.xpath("//a[@class='sf-with-ul' and text()='Women']"));
		
		//Selenium functions used for retrieving attribute or value
		String AssociatedText=Category.getText();
		String AttributeClass=Category.getAttribute("Class");
		System.out.println("getText() method returns: "+AssociatedText);
		System.out.println("getAttribute('Class') method returns: "+AttributeClass);
		
		Category.click();
		
		//Implicit Wait-->Halts the webdriver for the given amount of time until the desired element is located on the webpage.
		//It will be internally applied time-out that will be used for all consecutive web-element searches. It only looks for element presence and can't be forced in doing anything else.
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Right Click (contextClick()) implementation
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@title='Blouse']")))
		.contextClick().build().perform();//For right click
		
		//Selecting an option in context click menu option-*Not working*
		a.sendKeys(Keys.ESCAPE).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN)
		.build().perform();
		
		//a.sendKeys(Keys.RETURN)--->For sending Enter key
		//a.sendKeys(Keys.TAB)--->For sending TAB key
		//a.doubleClick("WebElement Locator").build().perform or
		//a.doubleClick().build().perform()----> For double clicking an element
		
		Thread.sleep(5000);
		
		driver.quit();
		

	}

}
