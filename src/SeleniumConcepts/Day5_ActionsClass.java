package SeleniumConcepts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

//Q10. How to perform drag and drop?
//Q18. How to handle internationalization through webdriver?
//Q42. What is actions class in webdriver?
//Q148. iFrames-How to perform action on Frames?


public class Day5_ActionsClass {
	
	public static void dragAndDrop() throws IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		/*WebDriver driver=new EdgeDriver();
		System.setProperty("webdriver.edge.driver","C:\\Users\\hp\\eclipse-workspace\\SeleniumProject\\msedgedriver.exe");*/
		//driver.manage().window().maximize();//It is always advisable to maximize the window before performing drag and drop action.
		
		driver.get("https://jqueryui.com/droppable/");
		
		
		WebElement iframe=driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe);
		//Thread.sleep(3000);
		WebElement draggable=driver.findElement(By.id("draggable"));
		System.out.println(draggable.getText());
		WebElement droppable=driver.findElement(By.id("droppable"));
		System.out.println(droppable.getText());
		
		//Thread.sleep(3000);
		
		Actions dnd=new Actions(driver);
		dnd.dragAndDrop(draggable, droppable).build().perform();
		//Thread.sleep(3000);
		
		//Verification of successful drag and drop
		String textDropped=droppable.getText();
		if (textDropped.equalsIgnoreCase("dropped!")){
			System.out.println("Drag and Drop passed.");
		}
		else {
			System.out.println("Drag and Drop failed.");
		}
		
		
		File dndscreenshot=(File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//TakesScreenshot is a built in selelnium class and we are referring getScreenshotAs method of this class to take screenshot
		FileUtils.copyFile(dndscreenshot, new File("C:\\Users\\hp\\eclipse-workspace\\SeleniumProject\\src\\DNDScreenshot.png"));//Assigning location for screenshot file
		
		
		driver.quit();
	}
	
	public static void resizing() throws InterruptedException {
		
		System.setProperty("webdriver.edge.driver","C:\\Users\\hp\\eclipse-workspace\\SeleniumProject\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://jqueryui.com/resizable/");
		
		//Locating iFrame where Resizable box is residing
		WebElement iframe2=driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe2);
		
		//Locating webelement Resizable box
		WebElement resizeable=driver.findElement(By.id("resizable"));
		
		//Getting and printing dimensions for comparison
		Dimension dimension1=resizeable.getSize();
		System.out.println("Dimensions before resizing: "+dimension1);
		
		//Need to find the element that can be dragged and dropped to resize. Here I have used the right side boundary of the resizing box.
		WebElement toResize=driver.findElement(By.xpath("//div[@class='ui-resizable-handle ui-resizable-e']"));
		
		
		//Actions class to perform resizing
		Actions resize=new Actions(driver);
		resize.dragAndDropBy(toResize, 100, 100).perform();
		
		//Getting dimension of Resizeable box after resizing
		Dimension dimension2=resizeable.getSize();
		System.out.println("Dimensions after resizing: "+dimension2);
		
		//Verification of successful resizing
		if (dimension1!=dimension2) {
			System.out.println("Pass: Resizing successful");
		}
		
		else {
			System.out.println("Fail: Resizing unsuccessful");
		}
		
		driver.quit();	
		
	}
	
	public static void rightClick() throws InterruptedException, AWTException, IOException {
		
		/*System.setProperty("webdriver.edge.driver","C:\\Users\\hp\\eclipse-workspace\\SeleniumProject\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();*/
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://jqueryui.com/resizable/");
		
		//Locating iFrame where Resizable box is residing
		WebElement iframe2=driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe2);
		
		//Locating webelement Resizable box
		WebElement resizeable=driver.findElement(By.id("resizable"));
		
		Actions mouseClick=new Actions(driver);
		mouseClick.contextClick(resizeable).build().perform();
		
		//Robot class in implemented to select "Inspect" option from context click menu options
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Code below didn't work for selecting menu context click menu options
		
		/*mouseClick.sendKeys(Keys.ARROW_DOWN).build().perform();
		mouseClick.sendKeys(Keys.ARROW_DOWN).build().perform();
		mouseClick.sendKeys(Keys.ARROW_DOWN).build().perform();
		mouseClick.sendKeys(Keys.RETURN).build().perform();*/
		Thread.sleep(3000);
		
		driver.quit();
	}
	
	
	public static void slidingElement() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://jqueryui.com/slider/#content");
		
		//Locating iFrame where Resizable box is residing
		WebElement iframe3=driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe3);
		
		//Locating webelement Resizable box
		WebElement slider=driver.findElement(By.id("slider"));
		
		
		Actions slide=new Actions(driver);
		
		slide.click(slider).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).build().perform();
		
		Thread.sleep(3000);
		
		//Trying to get the value of style attribute for comparison but it's not working. Will check again
		System.out.println(slider.getAttribute("style"));
		
		
		driver.quit();
		
		
		
	}
	
	
	public static void internationalisation() throws InterruptedException {//Opening browser in different languages
		
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");
		
		
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--lang=es");//Passing arguments for spanish ie. es to chromeoptions object "options"
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
		String spanishText=driver.findElement(By.className("ktLKi")).getText();
		Thread.sleep(3000);
		
		if (spanishText.contains("Con emisión neutra")) {
			
			System.out.println("Pass");
			
		}
		else {
			System.out.println("Fail");
		}
		
		driver.quit();
		
	}
	

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		
		dragAndDrop();
		resizing();
		rightClick();
		slidingElement();
		internationalisation();
		
		System.out.println("All methods passed!");
		

	}

}
