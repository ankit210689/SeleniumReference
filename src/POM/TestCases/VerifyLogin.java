/**
 *
 */
package POM.TestCases;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import POM.LoginPage;

/**
 * @author ankit
 *
 */
public class VerifyLogin {

	@Test
	public void verifyvalidlogin()
	{
		//setting property for chromedriver and passing name and it's location as arguments
		System.setProperty("webdriver.chrome.driver","C:\\Selenium Data\\chromedriver_win32\\chromedriver.exe");

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://phptravels.net/login");

		//now we will call Page "LoginPage"

		LoginPage login=new LoginPage(driver);//Constructor "driver" is called

		login.typeUserName();

		login.typePassword();

		login.clickonloginbutton();

		driver.quit();


	}

}
