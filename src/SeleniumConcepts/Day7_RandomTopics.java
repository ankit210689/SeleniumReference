package SeleniumConcepts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.junit.Assert;

//Q29. How to handle auto complete box in web driver?
//Q31. How to find broken images in a page using Selenium web driver?
//Q45. How to find an element using contains keyword and without using conventional locator strategies?
//Q52. How to verify the presence of tool tips for a link?
//Q54. What is the use of getOptions() method?
//Q56. What is the use of deselectAll() method?
//Q60. How to type text in a new line inside a textarea?
//Q62. How do you simulate scroll down action?
//Q66. Explain how you can login into any site if it's showing any authentication pop-up for username and password.
//Q67. Explain how to assert text of webpage using selenium.
//Q142. Multiple select list box window
//Q144. Multiple select list box window-DESELECT

@SuppressWarnings("unused")
public class Day7_RandomTopics {
	
	public static void toolTips() {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		String tooltip=driver.findElement(By.className("MV3Tnb")).getAttribute("href");
		System.out.println(tooltip);
		driver.quit();
	}
	
	public static void dropDown() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://chercher.tech/practice/practice-dropdowns-selenium-webdriver");
		WebElement element=driver.findElement(By.id("animals"));
		element.click();
		
		//Select class contains getOptions() method. This method is used to get all options 
		Select s=new Select(element);
		List<WebElement> options=s.getOptions();//Returns the handles for each option
		int size=options.size();
		for (int i=0;i<size;i++) {
			String values=options.get(i).getText();//Returns the associated text with each option
			System.out.print(values+", ");
		}
		System.out.print("are the available options in drop-down\n");
		
		//2 ways to select option from dropdown
		//1--Using actions class to select options from dropdown
		/*Actions selection=new Actions(driver);
		selection.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
		*/
		
		//2--Qu45-Finding the location of element "Big baby cat" and selecting it from the drop-down options
		driver.findElement(By.xpath("//*[contains(text(),'Big Baby Cat')]")).click();
		String selectedValue=element.getAttribute("value");
		
		
		System.out.println(selectedValue+"---> is the selected value");
		
		//Q67-Hard Assertion or Assert
		Assert.assertEquals("big baby cat", selectedValue);//import "org.junit.Assert" package instead of "junit.framework.Assert" package to prevent deprecation
		//Assert.assertEquals("Big Baby Cat", selectedValue);//-->This assert fails due to upper and lower case mismatch in expected vs actual text and halts the execution
		
		
		//Note: Soft Assertion or verify would need testNg class package to import SoftAssert class
		
		
		/*s.deselectAll();//Method to deselect all the options selected from the drop down menu. 
		 *This throws exception in this example as You may only deselect all options of a multi-select checkbox
		 */
		driver.quit();
	}
	
	public static void autoCompleteBox() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("ind");
		
		
		Thread.sleep(3000);
		
		
		List<WebElement> listItems=driver.findElements(By.xpath("//li[@class='sbct']"));//List to store all elements found in auto complete suggestions list
		listItems.get(0).click();//Clicks the first element in auto complete suggestion list
		
		//Explicit wait for "urlContains" partial text
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("https://www.google.com/search?q=ind"));
		
		System.out.println(driver.getTitle());//Gets title of the new window
		driver.quit();
				
	}
	
	public static void textArea() {
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://tutorial.eyehunts.com/html/html-input-text-box-field-value-size-width-multiline-example/");
		
		
		WebElement textField=driver.findElement(By.xpath("//textarea[@id='comment']"));
		
		//Simulating scroll down action
		Actions action=new Actions(driver);
		action.scrollToElement(textField).build().perform();
		
		
		textField.click();
		//"\n" for text in new line within the textbox
		textField.sendKeys("This is my text for line 1.\nThis is my text for line 2.");
		driver.quit();
		
		
	}
	
	public static void multiSelectListBox() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_1");
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='None selected']")).click();
		
		
		//List<WebElement> listElements=driver.findElements(By.xpath("//select"));
		WebElement elementList=driver.findElement(By.id("multi-select-demo"));
				
		Select options=new Select(elementList);
		options.selectByVisibleText("Bootstrap Tips");
		//options.selectByIndex(2);-->selectByIndex didn't work beacuse indexs are not provided to elements on this webpage
		options.selectByVisibleText("Angular JS");
		options.selectByValue("HTML");
		
		List<WebElement> selectedOptions=options.getAllSelectedOptions();
		selectedOptions.forEach(e->System.out.println("Selected element: "+e.getText()+"[value="+e.getAttribute("value")+"]"));
		
		//Various ways to deselect options from a multi-selct listbox window
		options.deselectByValue("angular");
		options.deselectByVisibleText("Bootstrap Tips");
		options.deselectAll();//To deselect all the options at once
		
		driver.quit();
		
	}
	
	public static void authenticationPopup() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		String actual=driver.findElement(By.className("example")).getText();
		if (actual.contains("Basic Auth")) {
			System.out.println("Login through authentication pop-up successful");
		}
		else {
			System.out.println("Login failed");
		}
		driver.quit();
		
	}
	
	public static void brokenImages() throws InterruptedException, AWTException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/broken_images");
		//Thread.sleep(3000);
		List <WebElement> imageLinks=driver.findElements(By.xpath("//div[@class='example']/img"));
		//System.out.print(imageLinks);
		
		
		for (int i=0;i<imageLinks.size()-1;i++){//For loop to iterate through all elements except the last one
			Actions openContextMenu=new Actions(driver);//Actions class to perform contextClick action
			openContextMenu.contextClick(imageLinks.get(i)).build().perform();//Passing value of i to open only the first 2 images
				
			/*Select and clicking options in context click menu is no working through actions.
			openImage.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			So, we will use robot class instead.*/
			
			Robot selectOptions=new Robot();
			selectOptions.keyPress(KeyEvent.VK_DOWN);
			selectOptions.keyRelease(KeyEvent.VK_DOWN);
			selectOptions.keyPress(KeyEvent.VK_DOWN);
			selectOptions.keyRelease(KeyEvent.VK_DOWN);
			selectOptions.keyPress(KeyEvent.VK_ENTER);
			selectOptions.keyRelease(KeyEvent.VK_ENTER);			
			
		}
		
		
		
		Set<String> windowHandles=driver.getWindowHandles();
		
		//Using Iterator class to go through all window handles stored in set "windowHandles"
		Iterator<String>handle=windowHandles.iterator();
		String parentBrowser=handle.next();
		String childBrowser1=handle.next();
		String brokenImage1=driver.switchTo().window(childBrowser1).findElement(By.xpath("//h1")).getText();
		String childBrowser2=handle.next();
		String brokenImage2=driver.switchTo().window(childBrowser2).findElement(By.xpath("//h1")).getText();
		System.out.println(brokenImage1+"\n"+brokenImage2);
		
		driver.quit();
	}
	

	public static void main(String[] args) throws InterruptedException, AWTException {
		
		toolTips();
		dropDown();
		autoCompleteBox();
		textArea();
		multiSelectListBox();
		authenticationPopup();
		brokenImages();
		
		System.out.println("All methods passed!");
		
		
		

	}

}
