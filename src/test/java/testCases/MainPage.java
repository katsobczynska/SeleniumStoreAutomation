package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.loginPage;
import pageObjects.mainProductPage;
import resources.base;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends base {
	public WebDriver driver;

	@BeforeTest

	public void initialize() throws IOException {
		driver = initializeDriver();
	}

	@Test(dataProvider = "getValidUser")

	public void login(String Username, String Password) {

		driver.get(prop.getProperty("mainUrl"));
		loginPage lp = new loginPage(driver);
		lp.Username().sendKeys(Username);
		lp.Password().sendKeys(Password);
		lp.Login().click();

	}

	@DataProvider
	public Object[][] getValidUser() {
		Object data[][] = new Object[1][2];
		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";

		return data;
	}

	@Test(dependsOnMethods = "login")

	public void addingAndRemovingProductFromCart() {
		mainProductPage pp = new mainProductPage(driver);
		pp.addToCartButton().click();
		String textFull = pp.addToCartButton().getText();
		System.out.println(textFull);
		Assert.assertEquals(textFull, "REMOVE");
		WebElement flag = pp.flag();
		Assert.assertEquals(true, flag.isDisplayed());
		pp.addToCartButton().click();
		String textEmpty = pp.addToCartButton().getText();
		System.out.println(textEmpty);
		Assert.assertEquals(textEmpty, "ADD TO CART");

	}

	@Test(dependsOnMethods = "login")

	public void sideMenuAbout() {
		driver.get(prop.getProperty("inventoryUrl"));
		mainProductPage pp = new mainProductPage(driver);
		pp.mainMenu().click();
		pp.about().click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://saucelabs.com/");

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
