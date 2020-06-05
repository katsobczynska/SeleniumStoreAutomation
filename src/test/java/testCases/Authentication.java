package testCases;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObjects.loginPage;
import pageObjects.mainProductPage;
import resources.base;

public class Authentication extends base {
	public WebDriver driver;

	@BeforeTest

	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "getValidUser")
	public void PositiveScenario1LoginWithValidUsernameAndPassword(String Username, String Password)
			throws IOException {

		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys(Username);
		lp.Password().sendKeys(Password);
		lp.Login().click();

		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");

	}

	@Test
	public void PositiveScenario2LogOut() {
		mainProductPage pp = new mainProductPage(driver);
		loginPage lp = new loginPage(driver);
		pp.mainMenu().click();
		pp.logout().click();

		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/index.html");
		WebElement expectUsername = lp.Username();
		Assert.assertEquals(true, expectUsername.isDisplayed());
	}

	@DataProvider
	public Object[][] getValidUser() {
		Object[][] data = new Object[1][2];

		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";

		return data;
	}

	@Test(dataProvider = "getInvalidUsername")
	public void NegativeScenario1LoginWithInvalidUsername(String Username, String Password) {
		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys(Username);
		lp.Password().sendKeys(Password);
		lp.Login().click();
		String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
		String message = lp.Error().getText();
		System.out.println(message);

		Assert.assertTrue(message, message.contains(expectedMessage));

	}

	@DataProvider
	public Object[][] getInvalidUsername()

	{
		Object[][] data = new Object[1][2];
		data[0][0] = "invalidusername";
		data[0][1] = "secret_sauce";

		return data;

	}

	@Test(dataProvider = "getInvalidPassword")
	public void NegativeScenario2LoginWithInvalidPassword(String Username, String Password) {
		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys(Username);
		lp.Password().sendKeys(Password);
		lp.Login().click();
		String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
		String message = lp.Error().getText();
		System.out.println(message);
		Assert.assertTrue(message, message.contains(expectedMessage));

	}

	@DataProvider
	public Object[][] getInvalidPassword()

	{
		Object[][] data = new Object[1][2];
		data[0][0] = "standard_user";
		data[0][1] = "invalidpassword";

		return data;

	}

	@Test(dataProvider = "getInvalidUsernameAndPassword")
	public void NegativeScenario3LoginWithInvalidUsernameAndPassword(String Username, String Password) {
		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys(Username);
		lp.Password().sendKeys(Password);
		lp.Login().click();
		String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
		String message = lp.Error().getText();
		System.out.println(message);
		Assert.assertTrue(message, message.contains(expectedMessage));
	}

	@DataProvider
	public Object[][] getInvalidUsernameAndPassword() {
		Object[][] data = new Object[1][2];
		data[0][0] = "invaliduser";
		data[0][1] = "invalidpassword";

		return data;
	}

	@Test
	public void NegativeScenario4LeaveUsernameBlank() {
		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Password().sendKeys("secret_sauce");
		lp.Login().click();
		String expectedMessage = "Epic sadface: Username is required";
		String message = lp.Error().getText();
		System.out.println(message);
		Assert.assertTrue(message, message.contains(expectedMessage));
	}

	@Test
	public void NegativeScenario5LeavePasswordBlank() {
		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys("standard_user");
		lp.Login().click();
		String expectedMessage = "Epic sadface: Password is required";
		String message = lp.Error().getText();
		System.out.println(message);
		Assert.assertTrue(message, message.contains(expectedMessage));

	}

	@Test
	public void NegativeScenario6LeaveBothBlank() {

		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Login().click();
		String expectedMessage = "Epic sadface: Username is required";
		String message = lp.Error().getText();
		System.out.println(message);
		Assert.assertTrue(message, message.contains(expectedMessage));

	}

	@Test(dataProvider = "getBlockedUser")
	public void NegativeScenario7LockedUser(String Username, String Password) {

		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys(Username);
		lp.Password().sendKeys(Password);
		lp.Login().click();
		String expectedMessage = "Epic sadface: Sorry, this user has been locked out";
		String message = lp.Error().getText();
		System.out.println(message);
		Assert.assertTrue(message, message.contains(expectedMessage));

	}

	@DataProvider
	public Object[][] getBlockedUser() {
		Object[][] data = new Object[1][2];
		data[0][0] = "locked_out_user";
		data[0][1] = "secret_sauce";

		return data;

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
