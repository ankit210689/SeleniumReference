package SeleniumConcepts;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//Q13. How to automate check boxes?
//Q14. How to automate radio button?
//Q15. How to submit a form using selenium?
//Q23. What is the difference between driver.close() and driver.quit() methods?
//Q35. How to switch between multiple windows?
//Q55. What is the difference between getWindowHandles() and getWindowHandle()?
//Q137. Check if an element exists on the webpage.
//Q141. Wait for Element to be available (Explicit wait)
//Q149. TABS/New window-When browser opens in a new window or in a new tab
//Q150. TABS/New window-2-When 2 browsers are opened and webdriver needs to shift control from parent to child window.
//Q151. TABS/New window-3-When second browser (child window) is closed and webdriver needs to shift control from child window to parent window.

@SuppressWarnings("unused")
public class Day6_CheckboxesDropdownRadioButtonForms {
	
	public static void checkboxes() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/html/html_forms.asp");
		
		WebElement cb =driver.findElement(By.id("vehicle1"));
		
		//In case there is an "element click intercepted exception" we can use following solutions:
		
		
		//1. Explicit wait for check-box to be clickable. Also, answering Q.141.
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(cb));
		
		//2. In case explicit wait doesn't work, we can try javascript executor. However it was not required in this case.
		/*JavascriptExecutor executor=(JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", cb);*/
		
		cb.click();
		
		//Verification condition returns true or false
		System.out.println(cb.isSelected());
		
		driver.quit();
	}
	
	public static void radioButton() {
		
		System.setProperty("webdriver.chrome.driver","C:\\\\Selenium Data\\\\chromedriver_win32\\\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/html/html_forms.asp");
		WebElement rb=driver.findElement(By.id("javascript"));
		
		/*WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(rb));*/
		rb.click();
		
		System.out.println(rb.isSelected());
		
		driver.quit();
	}
	
	public static void formsSubmission() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\\\Selenium Data\\\\chromedriver_win32\\\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.w3schools.com/html/html_forms.asp");
		WebElement fName=driver.findElement(By.id("fname"));
		fName.clear();
		fName.sendKeys("Ankit");
		
		//Just added for Q.137 to check if an element exists on the webpage
		boolean availability=driver.findElements(By.id("lname")).size()!=0;
		System.out.println(availability);
		
		
		WebElement lName=driver.findElement(By.id("lname"));
		lName.clear();
		lName.sendKeys("Pandey");
		System.out.println(fName.getAttribute("value")+lName.getAttribute("value"));
		
		//2 ways to submit a form using selenium
		lName.sendKeys(Keys.RETURN);
		//lName.submit();
		
		Thread.sleep(3000);
		
		String url="";
		
		//Getting address for current window and storing it as string
		String handle=driver.getWindowHandle();
		System.out.println(handle);
		
		
		
		//Getting address for both browser windows and storing string values in collection "set"
		Set<String> handles=driver.getWindowHandles();
		
		/*Window handles are changing consistently. So, we can't hard code window handle
		//driver.switchTo().window("CDwindow-E4994D6E9725E41E41215287ED078B15").getTitle();
		Instead we will use for loop or Iterator object*/
		
		//Logic#1
		//Both Window handles passed as string parameter for window attribute in for loop 
		//Switching between multiple windows
		/*for (String str:handles) {
			
			url=driver.switchTo().window(str).getCurrentUrl();
			System.out.println(url);
		}*/
		
		//Logic#2
		//Q149--Another way to perform the for loop operation above is using Iterator object
		//An iterator is an object that can be used to loop through collections like arraylists and hashset.
		/*Iterator<String> it=handles.iterator();
		
		while(it.hasNext()) {
			String str=it.next();
			url=driver.switchTo().window(str).getCurrentUrl();
		}*/
		
		//Logic#3
		//Q150--Iterating through windowhandles and assigning names to each handle. Those names will be used as parameter for "window()" while switching later
		Iterator<String>it=handles.iterator();
		String parentBrowser=it.next();
		String childBrowser=it.next();
		url=driver.switchTo().window(childBrowser).getCurrentUrl();
		
		//Verification condition
		
		if (url.contains("Ankit")) {
			System.out.println("Pass: Submission successful");
		}
		else {
			System.out.println("Fail: Submission unsuccessful");
		}
		
		//Q151-When childBrowser is closed and webdriver needs to shift control from childBrowser to parentBrowser
		driver.close();//closes childbrowser
		String p_url=driver.switchTo().window(parentBrowser).getCurrentUrl();//switches control to parent browser to get it's url
		System.out.println(p_url);
			
		driver.quit();
		
	}

	public static void main(String[] args) throws InterruptedException {
		
		checkboxes();
		radioButton();
		formsSubmission();
		
		System.out.println("All methods passed!");
		

	}

}
