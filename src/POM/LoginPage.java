/**
 *
 */
package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author ankit
 *
 *
 *This class will store all the locator and methods of login page
 */
public class LoginPage {

	WebDriver driver;

	By username=By.name("email");
	By password=By.name("password");
	//By recaptcha=By.xpath("//div/span[@dir='ltr']");
	By loginbutton=By.linkText("Login");

	public LoginPage(WebDriver driver) //Creating constructor, which is tasked to initialize WebDriver

	{
		this.driver=driver;//Calling this constructor in verifylogin class will initialize the webdriver method
	}

	public void typeUserName()
	{
	driver.findElement(username).sendKeys("user@phptravels.com");
	}

	public void typePassword()
	{
	driver.findElement(password).sendKeys("demouser");
	}

	public void clickonloginbutton()
	{
	driver.findElement(loginbutton).click();
	}


}
