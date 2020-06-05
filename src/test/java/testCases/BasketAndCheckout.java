package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.basketPage;
import pageObjects.checkoutPage;
import pageObjects.loginPage;
import pageObjects.mainProductPage;
import resources.base;

public class BasketAndCheckout extends base {

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

	public void CancelCheckout() {
		mainProductPage pp = new mainProductPage(driver);
		pp.addToCartButton().click();
		pp.cart().click();
		basketPage bp = new basketPage(driver);
		checkoutPage cp = new checkoutPage(driver);

		bp.checkout().click();
		cp.firstName().sendKeys("John");
		cp.lastName().sendKeys("Morris");
		cp.postalCode().sendKeys("89020");
		cp.continueButton().click();

		cp.cancelButton2().click();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
		WebElement flag = pp.flag();
		Assert.assertEquals(true, flag.isDisplayed());
		pp.cart().click();
		bp.removeButton().click();

	}

	@Test(dependsOnMethods = "login")

	public void checkElementsVisibility() {
		driver.get(prop.getProperty("inventoryUrl"));
		mainProductPage pp = new mainProductPage(driver);
		checkoutPage cp = new checkoutPage(driver);
		pp.addToCartButton().click();
		pp.addToCartButton2().click();
		pp.cart().click();

		basketPage bp = new basketPage(driver);
		WebElement subheader = bp.subheader();
		WebElement quantityLabel = bp.quantityLabel();
		WebElement descriptionLabel = bp.cartDescriptionLabel();
		WebElement remove = bp.removeButton();
		WebElement continueShopping = bp.continueShopping();
		WebElement checkout = bp.checkout();

		Assert.assertEquals(true, subheader.isDisplayed());
		Assert.assertEquals(true, quantityLabel.isDisplayed());
		Assert.assertEquals(true, descriptionLabel.isDisplayed());
		Assert.assertEquals(true, remove.isDisplayed());
		Assert.assertEquals(true, continueShopping.isDisplayed());
		Assert.assertEquals(true, checkout.isDisplayed());

		bp.checkout().click();
		WebElement firstName = cp.firstName();
		WebElement lastName = cp.lastName();
		WebElement postalCode = cp.postalCode();
		WebElement continueButton = cp.continueButton();
		WebElement cancelButton = cp.cancelButton();

		Assert.assertEquals(true, firstName.isDisplayed());
		Assert.assertEquals(true, lastName.isDisplayed());
		Assert.assertEquals(true, postalCode.isDisplayed());
		Assert.assertEquals(true, continueButton.isDisplayed());
		Assert.assertEquals(true, cancelButton.isDisplayed());

		cp.firstName().sendKeys("John");
		cp.lastName().sendKeys("Morris");
		cp.postalCode().sendKeys("89210");
		cp.continueButton().click();

		WebElement productQuantity = cp.productQuantity();
		WebElement itemName = cp.itemName();
		WebElement itemDescription = cp.itemDescription();
		WebElement itemPrice = cp.itemPrice();
		WebElement subTotalPrice = cp.subTotalPrice();
		WebElement taxLabel = cp.taxLabel();
		WebElement totalLabel = cp.totalLabel();
		WebElement cancelButton2 = cp.cancelButton2();
		WebElement finishButton = cp.finishButtton();

		Assert.assertEquals(true, productQuantity.isDisplayed());
		Assert.assertEquals(true, itemName.isDisplayed());
		Assert.assertEquals(true, itemDescription.isDisplayed());
		Assert.assertEquals(true, itemPrice.isDisplayed());
		Assert.assertEquals(true, subTotalPrice.isDisplayed());
		Assert.assertEquals(true, taxLabel.isDisplayed());
		Assert.assertEquals(true, totalLabel.isDisplayed());
		Assert.assertEquals(true, cancelButton2.isDisplayed());
		Assert.assertEquals(true, finishButton.isDisplayed());

	}

	/*
		*/
	@Test(dependsOnMethods = { "login" })

	public void CheckoutFullPositiveScenario() {
		driver.get(prop.getProperty("inventoryUrl"));
		mainProductPage pp = new mainProductPage(driver);
		pp.addToCartButton().click();
		pp.addToCartButton2().click();
		pp.cart().click();
		basketPage bp = new basketPage(driver);
		checkoutPage cp = new checkoutPage(driver);

		bp.checkout().click();
		cp.firstName().sendKeys("John");
		cp.lastName().sendKeys("Morris");
		cp.postalCode().sendKeys("89020");
		cp.continueButton().click();

		List<WebElement> list = driver.findElements(By.xpath("(//div[@class='inventory_item_price'])"));
		Iterator<WebElement> iterator = list.iterator();

		List<String> values = new ArrayList<String>();
		while (iterator.hasNext()) {
			WebElement element = iterator.next();
			values.add(element.getText());
		}

		System.out.println(values.toString());

		String firstPrice = values.get(0);
		firstPrice = firstPrice.replaceAll("[$]", " ");
		float Price1 = Float.parseFloat(firstPrice);
		System.out.println(Price1);

		String secondPrice = values.get(1);
		secondPrice = secondPrice.replaceAll("[$]", " ");
		float Price2 = Float.parseFloat(secondPrice);
		System.out.println(Price2);

		float totalPrice = Price1 + Price2;
		System.out.println(totalPrice);

		String subTotal = cp.subTotalPrice().getText();
		subTotal = subTotal.replaceAll("[$]", " ");
		subTotal = subTotal.replaceAll("[A-Z]", " ");
		subTotal = subTotal.replaceAll("[a-z]", " ");
		subTotal = subTotal.replaceAll("[:]", " ");

		float toPay = Float.parseFloat(subTotal);
		System.out.println(toPay);

		Assert.assertEquals(totalPrice, toPay, 0.0001);

		cp.finishButtton().click();
		WebElement complete = cp.completeHeader();
		Assert.assertEquals(true, complete.isDisplayed());
	}

	@Test(dependsOnMethods = { "login" })
	public void checkoutNegativeScenarioAddressEmptyFirstName() {
		driver.get(prop.getProperty("addressUrl"));
		checkoutPage cp = new checkoutPage(driver);
		cp.lastName().sendKeys("Morris");
		cp.postalCode().sendKeys("89020");
		cp.continueButton().click();
		String expectedError = "Error: First Name is required";
		String displayedError = cp.addressError().getText();
		Assert.assertTrue(displayedError, displayedError.contains(expectedError));

	}

	@Test(dependsOnMethods = { "login" })
	public void checkoutNegativeScenarioAddressEmptyLastName() {
		driver.get(prop.getProperty("addressUrl"));
		checkoutPage cp = new checkoutPage(driver);
		cp.firstName().sendKeys("John");
		cp.postalCode().sendKeys("89020");
		cp.continueButton().click();
		String expectedError = "Error: Last Name is required";
		String displayedError = cp.addressError().getText();
		Assert.assertTrue(displayedError, displayedError.contains(expectedError));

	}

	@Test(dependsOnMethods = { "login" })
	public void checkoutNegativeScenarioAddressEmptyPostCode() {
		driver.get(prop.getProperty("addressUrl"));
		checkoutPage cp = new checkoutPage(driver);
		cp.firstName().sendKeys("John");
		cp.lastName().sendKeys("Morris");
		cp.continueButton().click();
		String expectedError = "Error: Postal Code is required";
		String displayedError = cp.addressError().getText();
		Assert.assertTrue(displayedError, displayedError.contains(expectedError));

	}

	@Test(dependsOnMethods = { "login" })
	public void checkoutNegativeScenarioAddressAllFieldsLeftEmpty() {
		driver.get(prop.getProperty("addressUrl"));
		checkoutPage cp = new checkoutPage(driver);

		cp.continueButton().click();
		String expectedError = "Error: First Name is required";
		String displayedError = cp.addressError().getText();
		Assert.assertTrue(displayedError, displayedError.contains(expectedError));

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

}
