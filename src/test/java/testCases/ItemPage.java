package testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.individualProductPage;
import pageObjects.loginPage;
import resources.base;

public class ItemPage extends base {
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

	@Test(dependsOnMethods = { "login" })

	public void checkElementsVisibility() {
		individualProductPage ip = new individualProductPage(driver);
		driver.get(prop.getProperty("inventoryUrl"));
		ip.itemLink().click();

		WebElement headerLabel = ip.headerLabel();
		WebElement backButton = ip.backButton();
		WebElement itemImage = ip.itemImage();
		WebElement itemName = ip.itemName();
		WebElement itemDescription = ip.itemDescriprion();
		WebElement itemPrice = ip.itemPrice();
		WebElement addToCart = ip.addToCart();

		Assert.assertEquals(true, headerLabel.isDisplayed());
		Assert.assertEquals(true, backButton.isDisplayed());
		Assert.assertEquals(true, itemImage.isDisplayed());
		Assert.assertEquals(true, itemName.isDisplayed());
		Assert.assertEquals(true, itemDescription.isDisplayed());
		Assert.assertEquals(true, itemPrice.isDisplayed());
		Assert.assertEquals(true, addToCart.isDisplayed());
	}

	@Test(dependsOnMethods = { "login" })

	public void addToCartAndRemove() {
		individualProductPage ip = new individualProductPage(driver);
		driver.get(prop.getProperty("inventoryUrl"));
		ip.itemLink().click();
		ip.addToCart().click();
		WebElement cartFlag = ip.cartFlag();
		Assert.assertEquals(true, cartFlag.isDisplayed());
		String textFull = ip.addToCart().getText();
		String expectedText = "REMOVE";
		Assert.assertTrue(textFull, textFull.contains(expectedText));
		ip.addToCart().click();
		String textEmpty = ip.addToCart().getText();
		String expectedText2 = "ADD TO CART";
		Assert.assertTrue(textEmpty, textEmpty.contains(expectedText2));
		ip.backButton().click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");

	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}

}
